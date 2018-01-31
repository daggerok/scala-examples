package daggerok

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.persistence.PersistentActor
import akka.routing.FromConfig

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
    // val system = ActorSystem("ap")
    val system = ActorSystem.create("ap")
    val config = FromConfig.props(Props[CounterActor])
    val persistentActor: ActorRef = system.actorOf(config, name = "counter")
    //// TODO
    // persistentActor ! IncrementCommand
  }
}
