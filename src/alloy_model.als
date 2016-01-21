open util/ordering[Tick]
sig Tick {}

sig Server {}

abstract sig Software {
	installOn: Tick -> lone Server,
	dependOn: set Software,
	connectTo: set Software
}

one sig DB, Apache, PHP, Web extends Software {}

/**
get this from ecore
*/
fact {
	no DB.dependOn
	no Apache.dependOn
	PHP.dependOn = Apache
	Web.dependOn = PHP

	no DB.connectTo
	no Apache.connectTo
	no PHP.connectTo
	Web.connectTo = DB
}

pred init {
	no first.(Software.installOn)
}

pred install(software: Software, server: Server, tick: Tick) {
	//have not installed before 
	no software.installOn[tick.prev]
	software.installOn[tick] = server

	//all its dependency should be installed before
	all s : software.dependOn | one s.installOn[tick.prev]
	all s : software.connectTo | one s.installOn[tick.prev]

	//others should just remain
	all s : Software - software | s.installOn[tick] = s.installOn[tick.prev] 
}

pred end {
	all s : Software | one s.installOn[last]
	all disj s1, s2 : Software | s1 in s2.dependOn implies s1.installOn[last] = s2.installOn[last]
}

pred running {
	init
	all t : Tick - first | 
		some soft : Software | 
			some server : Server | 
				install[soft, server, t]
	end
}

assert r {
	running implies one Software.installOn[last]
}

/**
number of signature is |software| + 1
*/
run running for exactly 2 Server, 5 Tick
check r for exactly 2 Server, 5 Tick
run running for 2 Server, 6 Tick
run running for 2 Server, 7 Tick
run running for 2 Server, 8 Tick

