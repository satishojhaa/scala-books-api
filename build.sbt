ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "booksapi",
    idePackagePrefix := Some("com.sko.books.rest"),
    organization := "com.sko",

    libraryDependencies ++= {
      val AkkaVersion = "2.6.17"
      val AkkaHttpVersion = "10.2.8"
      val Json4sVersion = "4.1.0-M3"
      Seq(
        "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
        "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
        "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
        "com.typesafe.akka" %% "akka-http2-support" % AkkaHttpVersion,
        "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
        "ch.qos.logback" % "logback-classic" % "1.2.3",
        "org.json4s" %% "json4s-native" % Json4sVersion,
        "org.json4s" %% "json4s-ext" % Json4sVersion,
        "de.heikoseeberger" %% "akka-http-json4s" % "1.40.0-RC3"
      )
    }
  )

// Assembly settings
mainClass in Global := Some("com.sko.books.rest.Main")