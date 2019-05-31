package ProperNameFinderApp

import akka.actor.{Actor, ActorRef, ActorSystem, Props }
import scala.collection.mutable.HashMap
import scala.io.Source 
import scala.util.matching.Regex

// Started 2019-04-20 10:30
// Finished phase 1 at 18:18
// Started phase 2 2019-04-20 21:24

class ProperNameMapperActor(reduceActors: List[ActorRef]) extends Actor {
        val numReducers = reduceActors.size
        val DUMMY = true 

        def receive = {
          case OnlineBook(name, url) => 
                  println("Received book")
                  process(OnlineBook(name, url))

          case Flush =>
                  for(i <- 0 until numReducers) {
                          reduceActors(i) ! Flush
                  }
        }

        def process(onlineBook:OnlineBook) =
        {
                def getProperNames(words:Array[String]) : List[String] = { 
                    var names:List[String] = List()

                    val namePattern = "[A-Z]([a-z]|(['][A-Z]))[a-z]*".r
                    val nameOpts = for (word <- words) yield namePattern.findFirstMatchIn(word)
  
                    for (name <- nameOpts) {
                        name match {
                                case Some(_) => names = name.get.toString :: names
                                case None => None
                        }
                    }
                    names
                }

                var nameHashes = HashMap[String, Boolean]()

                val contents = Source.fromURL(onlineBook.url).mkString
                println("Finsihed downloading book")
                //println(contents)

                val properNames = getProperNames(contents.split(" "))
                println("finished making proper names")
                properNames.foreach(sendName)

                def sendName(name:String) =
                {
                        if (!(nameHashes contains name)) {
                                //println(s"Sending $name to reducer")
                                val index = Math.abs((name.hashCode()) % numReducers)
                                reduceActors(index) ! Name(name,onlineBook.title)
                                nameHashes += (name -> DUMMY)
                        }
                }
        }

}

