package com.bwsw.cloudstack.imp.dao.zookeeper

import com.bwsw.cloudstack.common.curator.CuratorTests

class UserTests extends CuratorTests {
  val KEY = "key"
  val ID = "79233334444"
  val USERNAME = "user1"

  it should "create user object" in {
    val u = User(ID, USERNAME)
  }

  it should "serialize user object and deserialize it back" in {
    val u = User(ID , USERNAME)
    u.properties(KEY) = "value"
    User.save(u)

    val u1 = User.load(u.id)

    u1 shouldBe u
    u1.properties(KEY) shouldBe u.properties(KEY)
  }


}
