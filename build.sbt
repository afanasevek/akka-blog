name := "blogV3"

version := "0.1"

scalaVersion := "2.13.4"

val akkaVersion = "2.6.10"
val akkaHttpVersion = "10.2.2"
val postgresVersion = "42.2.18"
val scalaTestVersion = "3.3.0-SNAP3"
val scalikejdbcVersion = "3.5.0"
val logbackClassicVersion = "1.3.0-alpha5"
val springVersion = "5.3.2"
val sttpVersion = "3.0.0-RC13"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-config" % scalikejdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-joda-time" % scalikejdbcVersion,
  "org.postgresql" % "postgresql" % postgresVersion,
  "ch.qos.logback" % "logback-classic" % logbackClassicVersion,
  "org.springframework" % "spring-core" % springVersion,
  "org.springframework" % "spring-context" % springVersion,

  "com.softwaremill.sttp.client3" %% "core" % sttpVersion % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
)