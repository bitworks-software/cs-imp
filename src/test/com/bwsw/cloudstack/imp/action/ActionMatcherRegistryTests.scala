package com.bwsw.cloudstack.imp.action

import com.bwsw.cloudstack.imp.event.Event
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by Ivan Kudryavtsev on 01.08.17.
  */
class ActionMatcherRegistryTests extends FlatSpec with Matchers {
  it should "gather events from different factories" in {
    val f1 = ActionMatcherGenerator.getTrivialActionMatcher()
    val f2 = ActionMatcherGenerator.getTrivialActionMatcher()

    val registry = new ActionMatcherRegistry
    registry
      .register(f1)
      .register(f2)

    val actionList = registry.generate(new Event)

    actionList.isInstanceOf[List[Action]] shouldBe true
    actionList.size shouldBe 2

  }
}
