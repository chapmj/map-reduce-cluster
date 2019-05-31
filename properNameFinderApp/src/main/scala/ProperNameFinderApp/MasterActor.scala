package ProperNameFinderApp

import akka.actor.{Actor, ActorRef, Props}
import akka.routing.{Broadcast, RoundRobinPool}
import com.typesafe.config.ConfigFactory

class MasterActor extends Actor {
        // Load configuration options from file
        val numberMappers = ConfigFactory.load.getInt("number-mappers")
        val numberReducers = ConfigFactory.load.getInt("number-reducers")

        // Keep track of how many reducers are busy
        var pending = numberReducers

        // Create list of reducers
        var properNameReducers = List[ActorRef]()
        for (i <- 0 until numberReducers) 
        {
                properNameReducers = context.actorOf(
                        Props[ProperNameReducerActor], name = "reduce" + i) ::
                        properNameReducers 
        }

        // Create pool of mappers
        val mapActors = context.actorOf(
                        RoundRobinPool(numberMappers).props(
                        Props(classOf[ProperNameMapperActor], properNameReducers)))

        // Inbox
        def receive = 
        {
                case OnlineBook(name, url) => 
                        mapActors ! OnlineBook(name, url)

                case Flush =>
                        mapActors ! Broadcast(Flush)

                case Done => 
                        pending -= 1
                        if (pending == 0) context.system.terminate
        }
}
