package com.bwsw.cloudstack.imp.action

import com.bwsw.cloudstack.imp.event.Event
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by Ivan Kudryavtsev on 01.08.17.
  */
class ActionMatcherTests extends FlatSpec with Matchers {
  it should "generate events properly" in {
    val f = ActionMatcherGenerator.getTrivialActionMatcher

    val actionList = f.generate(new Event)
    actionList.isInstanceOf[List[Action]] shouldBe true
    actionList.isEmpty shouldBe false
  }
}

object ActionMatcherGenerator {
  def getTrivialActionMatcher(): ActionMatcher = {
    (event: Event) => {
      List(new Action)
    }
  }
}