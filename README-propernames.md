
Marc Chapman
CSC536 - Distributed Systems - HW3

Github notes
------------
Implementation of distributed Map Reduce using Akka Remoting.

Homework question 1:
--------------------

To run:

Start a local instance of the MapReduce system.  All actors are local.
	cd properNameFinderApp
	sbt run

Homework question 2:
--------------------

A basic MapReduce system implemented in Akka. Client sends data to servers
defined in a server list. Once reducers complete their jobs, the results are
displayed on the console of the Client.

To run:

Start a server listening on port 2552.
	cd propernames
	sbt "propernames/runMain propernames.startServer server1"


Start a server listening on port 2553.

	sbt "propernames/runMain propernames.startServer server2"


Start a client. Must be started after Servers have started.  All servers are
are configured to run on localhost. (This was also tested with a private server
running as Server.)

	sbt "propernames/runMain propernames.startClient client" 

