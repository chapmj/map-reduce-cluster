package ProperNameFinderApp

import akka.actor.{ActorSystem, Props }

/* Books
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg580.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg968.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg807.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg1400.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg98.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg20795.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg1023.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg883.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg821.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg730.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg700.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg766.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg967.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg699.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg19337.txt
http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg963.txt
 */


object ProperNameFinderApp extends App {
        val system = ActorSystem("ProperNamesApp") 
        val master = system.actorOf(Props[MasterActor], name = "master")

        master ! OnlineBook("pg580", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg580.txt")

        master ! OnlineBook("pg968", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg968.txt")
        master ! OnlineBook("pg807", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg807.txt")
        master ! OnlineBook("pg1400", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg1400.txt")
        master ! OnlineBook("pg98", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg98.txt")
        master ! OnlineBook("pg20795", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg20795.txt")
        master ! OnlineBook("pg1023", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg1023.txt")
        master ! OnlineBook("pg883", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg883.txt")
        master ! OnlineBook("pg821", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg821.txt")
        master ! OnlineBook("pg730", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg730.txt")
        master ! OnlineBook("pg700", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg700.txt")
        master ! OnlineBook("pg766", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg766.txt")
        master ! OnlineBook("pg967", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg967.txt")
        master ! OnlineBook("pg699", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg699.txt")
        master ! OnlineBook("pg19337", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg19337.txt")
        master ! OnlineBook("pg963", "http://reed.cs.depaul.edu/lperkovic/csc536/homeworks/gutenberg/pg963.txt")

        master ! Flush
}
