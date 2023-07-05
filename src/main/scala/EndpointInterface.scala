package com.sko.books.rest

import routing.{BookHandler, HomeHandler}
import services.BookService

import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext

trait EndpointInterface extends Endpoints {

  implicit def executionContext: ExecutionContext

  lazy val bookService = new BookService

  val routes: Route = homeRoute ~ bookRoutes

}

trait Endpoints extends HomeHandler with BookHandler
