package propernames

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object startServer extends App {
// sbt ./runMain ProperNameFinderApp3.startServer

	val config = 
		if (args.length == 1) { 
			ConfigFactory.load.getConfig(args(0))
		} else {
			ConfigFactory.load.getConfig("server1")
		}

        val system = ActorSystem("ProperNamesApp", config) 
}
