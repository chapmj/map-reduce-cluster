package ProperNameFinderApp

import akka.actor.{Actor, ActorSystem}
import com.typesafe.config.ConfigFactory
import scala.collection.mutable.HashMap

// Started 2019-04-20 10:30

class ProperNameReducerActor extends Actor {
        var remainingMappers = ConfigFactory.load.getInt("number-mappers")
        
        var reduceMap = HashMap.empty[String, List[String]] 

        def receive = 
        {
              case Name(name, title) =>
                  // update hashmap
                  reduceMap.get(name) match {
                    case Some(_) => 
                      reduceMap += (name -> (title :: reduceMap(name)))
                    case None => 
                      reduceMap += (name -> List(title))
                  }
                  //println(s"received $name $title")
                        

                case Flush => 
                        remainingMappers -= 1
                        if (remainingMappers == 0) {
                                printNamesBooks()
                                context.parent ! Done
                        }
                
        }

        def printNamesBooks() {
                for (personName <- reduceMap.keys) {
                  
                        var line = "Name: " + personName + " Books: "
                  
                        for (bookName <- reduceMap(personName)) {
                                line += s"${bookName}, "
                        }

                        if (line.endsWith(", ")) {
                                line = line.substring(0, line.length - 2)
                        }

                        println(line)
                }

        }
}
