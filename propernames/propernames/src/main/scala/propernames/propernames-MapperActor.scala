package propernames

import akka.actor.{Actor, ActorRef, Props}
import akka.routing.Broadcast
import com.typesafe.config.ConfigFactory
import scala.collection.mutable.HashMap
import scala.io.Source 
import scala.util.matching.Regex

class Mapper(reducers: ActorRef) extends Actor {

	println("MAPPER STARTED")
        def receive = 
	{
		case OnlineBook(name, url) => 
                process(OnlineBook(name, url))
		println("MAPPER COMPLETED PROCESSING BOOK")

          	case Flush =>
		reducers ! Broadcast(Flush)

		case _ => println("PATTERN ERROR")

        }

        def process(onlineBook:OnlineBook) =
	{
		def getProperNames(words:Array[String]) : List[String] = 
		{ 
                	var names:List[String] = List()
                    	val namePattern = "[A-Z]([a-z]|(['][A-Z]))[a-z]*".r
                    	val nameOpts = 
				for (word <- words) 
				yield namePattern.findFirstMatchIn(word)
  
                	for (name <- nameOpts) {
                        	name match {
					case Some(_) => 
					names = name.get.toString :: names

					case None => 
					None
                        	}
			}

                    names
                }

                var nameHashes = HashMap[String, Any]()

                val contents = Source.fromURL(onlineBook.url).mkString

                val properNames = getProperNames(contents.split(" "))
                properNames.foreach(sendNameConsistent)

                def sendNameConsistent(name:String) =
                {
                        if (!(nameHashes contains name)) {
                                reducers ! Name(name,onlineBook.title)
                                nameHashes += (name -> Dummy)
                        }
                }
        }
}
