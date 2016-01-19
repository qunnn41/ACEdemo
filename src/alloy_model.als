open util/ordering[Tick]
sig Tick {}

sig Server {}

abstract sig Software {
	installOn: Tick -> Server,
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
	no Software.installOn[first]
}

pred install(software: Software, server: Server, tick: Tick) {
	//have not installed before 
	no software.installOn[tick.prev]
	software.installOn[tick] = server

	//all its dependency should be installed before
	all s : software.dependOn | some s.installOn[tick]
	all s : software.connectTo | some s.installOn[tick]

	//others should just remain
	all s : Software - software | s.installOn[tick] = s.installOn[tick.prev] 
}

pred end {
	all s : Software | some s.installOn[last]
	all disj s1, s2 : Software | s1 in s2.dependOn implies s1.installOn[last] = s2.installOn[last]
}

pred running {
	init
	all t : Tick - first | 
		one soft : Software | 
			one server : Server | 
				install[soft, server, t]
	end
}

/**
number of signature is |software| + 1
*/
run running for 2 Server, 5 Tick

