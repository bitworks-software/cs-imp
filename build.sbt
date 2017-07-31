
name := "cs-imp"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.2"

pomExtra :=
  <scm>
    <url>git@github.com:bwsw/cs-imp.git</url>
    <connection>scm:git@github.com:bwsw/cs-imp.git</connection>
  </scm>
    <developers>
      <developer>
        <id>bitworks</id>
        <name>Bitworks Software, Ltd.</name>
        <url>http://bitworks.software/</url>
      </developer>
    </developers>


fork in run := true
fork in Test := true
licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/bwsw/cs-imp"))
pomIncludeRepository := { _ => false }
scalacOptions += "-feature"
scalacOptions += "-deprecation"
parallelExecution in Test := false
organization := "com.bwsw"
publishMavenStyle := true
pomIncludeRepository := { _ => false }

isSnapshot := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

libraryDependencies += "org.json4s"   %% "json4s-jackson" % "3.5.2"
libraryDependencies += "com.typesafe" % "config" % "1.3.1"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.24"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.24"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % "test"
