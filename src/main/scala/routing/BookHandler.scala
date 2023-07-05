package com.sko.books.rest
package routing

import models.{Book, BookUpdate}
import services.BookService

import akka.http.scaladsl.server.Route

trait BookHandler extends BaseHandler {

  val bookService: BookService

  def bookRoutes: Route = pathPrefix("books") {
    pathEnd {
      post {
        entity(as[Book]) { question =>
          completeWithLocationHeader(
            resourceId = bookService.createBook(question),
            ifDefinedStatus = 201, ifEmptyStatus = 409)
        }
      }
    } ~
      path(Segment) { id =>
        val bId = id.toIntOption
        val bookId = bId match {
          case Some(value) => value
          case _ => 0
        }
        get {
          complete(bookService.getBook(bookId))
        } ~
          put {
            entity(as[BookUpdate]) { update =>
              complete(bookService.updateBook(bookId, update))
            }
          } ~
          delete {
            complete(bookService.deleteBook(bookId))
          }
      }

  }
}
