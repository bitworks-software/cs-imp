/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.bwsw.cloudstack.imp.dao.zookeeper

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import org.apache.curator.framework.CuratorFramework
import scala.collection.mutable

/**
  * Created by Ivan Kudryavtsev on 31.07.17.
  */
case class User private(id: String, userId: String, properties: mutable.Map[String, String])(implicit curatorClient: CuratorFramework) extends Serializable

object User {
  def apply(id: String, userId: String)(implicit curatorClient: CuratorFramework): User = new User(id, userId, mutable.Map.empty)(curatorClient)

  def load(id: String)(implicit curatorClient: CuratorFramework): User = {
    val byteStream = new ByteArrayInputStream(curatorClient.getData.forPath(s"/$id"))
    val inputStream = new ObjectInputStream(byteStream)
    inputStream.readObject().asInstanceOf[User]
  }

  def save(user: User)(implicit curatorClient: CuratorFramework) = {
    val byteStream = new ByteArrayOutputStream()
    val outputStream = new ObjectOutputStream(byteStream)
    outputStream.writeObject(user)
    outputStream.flush()
    curatorClient.create().orSetData().forPath(s"/${user.id}", byteStream.toByteArray)
  }
}