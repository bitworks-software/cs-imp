package com.bwsw.cloudstack.imp.action

/**
  * Created by Ivan Kudryavtsev on 01.08.17.
  */
trait ActionMatcher {
  def generate(eventSource: Map[String, String]): List[Action]
}
