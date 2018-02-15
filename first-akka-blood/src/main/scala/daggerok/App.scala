package daggerok

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.persistence.PersistentActor
import com.typesafe.config.ConfigFactory

case object IncrementCommand

case object IncrementedEvent

class CounterActor extends PersistentActor {
  override def persistenceId: String = "counter"

  var state = 0

  var receiveCommand: Receive = {
    case IncrementCommand => persist(IncrementedEvent) { event =>
      println("async processing start...")
      state += 1
      println(s"saved: ${state}")
      println(s"sending event: ${event}")
    }
  }

  var receiveRecover: Receive = {
    case IncrementedEvent => {
      println(s"reconstructing a state: ${state}")
      state += 1
      println(s"state reconstructed: ${state}")
    }
  }
}

object App {

  def main(args: Array[String]) {
    println(s"\n\n${args.length}\n\n")
    val config = ConfigFactory.load()
    val system = ActorSystem("ap")
//    val system = ActorSystem("ap", config.getConfig("myapp"))
//    val system = ActorSystem("ap", config.getConfig("myapp").withFallback(config))
    //val system = ActorSystem.create("ap")
    val persistentActor: ActorRef = system.actorOf(Props(classOf[CounterActor]), "counter")
    val amount = if (args.length > 0) args(1).toInt else 1
    for ( _ <- 0 to amount) {
      persistentActor ! IncrementCommand
    }
  }
}
