package com.bwsw.cloudstack.imp.action

import com.bwsw.cloudstack.imp.event.Event

/**
  * Created by Ivan Kudryavtsev on 01.08.17.
  */
trait ActionMatcher {
  def generate(event: Event): List[Action]
}
