package com.sko.books.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object Main extends App with EndpointInterface {
  private val config = ConfigFactory.load()
  private val host = config.getString("http.host")
  private val port = config.getInt("http.port")

  implicit val system: ActorSystem = ActorSystem("book-management-service")


  implicit val executionContext: ExecutionContext = system.dispatcher
  implicit val timeout: Timeout = Timeout(10 seconds)

  private val api = routes

  Http().newServerAt( interface = host, port = port).bind(api)
}