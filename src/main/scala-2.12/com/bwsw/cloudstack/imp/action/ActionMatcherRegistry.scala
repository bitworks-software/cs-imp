package com.bwsw.cloudstack.imp.action

import com.bwsw.cloudstack.imp.event.Event

import scala.collection.mutable

/**
  * Created by Ivan Kudryavtsev on 01.08.17.
  */
class ActionMatcherRegistry {
  val registry = mutable.ListBuffer[ActionMatcher]()
  def register(eventFactory: ActionMatcher) = {
    registry.append(eventFactory)
    this
  }

  def generate(event: Event): List[Action] = generateInt(registry.toList, event)
  private def generateInt(registry: Seq[ActionMatcher], event: Event): List[Action] = {
    registry match {
      case Nil => Nil
      case h :: t => h.generate(event) ++ generateInt(t, event)
    }

  }
}
