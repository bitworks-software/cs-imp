package com.bwsw.cloudstack.imp.action

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

  def generate(eventSource: Map[String, String]): List[Action] = generateInt(registry.toList, eventSource)
  private def generateInt(registry: Seq[ActionMatcher], eventSource: Map[String, String]): List[Action] = {
    registry match {
      case Nil => Nil
      case h :: t => h.generate(eventSource) ++ generateInt(t, eventSource)
    }

  }
}
