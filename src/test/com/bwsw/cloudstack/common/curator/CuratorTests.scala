package com.bwsw.cloudstack.common.curator

import com.bwsw.cloudstack.imp.dao.zookeeper.ZookeeperTestServer
import com.google.common.io.Files
import org.apache.curator.framework.{CuratorFramework, CuratorFrameworkFactory}
import org.apache.curator.retry.ExponentialBackoffRetry
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

/**
  * Created by Ivan Kudryavtsev on 31.07.17.
  */
class CuratorTests extends FlatSpec with Matchers with BeforeAndAfterAll {
  System.getProperty("java.io.tmpdir", "./target/")
  val ZOOKEEPER_PORT = 21810
  var zk: ZookeeperTestServer = null
  implicit var curator: CuratorFramework = null

  override def beforeAll() = {
    zk = new ZookeeperTestServer(ZOOKEEPER_PORT, Files.createTempDir().toString)
    curator = CuratorFrameworkFactory.builder()
      .retryPolicy(new ExponentialBackoffRetry(1000, 3))
      .namespace("tests")
      .connectString(s"127.0.0.1:$ZOOKEEPER_PORT").build()
    curator.start()
  }

  override def afterAll() = {
    curator.close()
    zk.stop
  }

}