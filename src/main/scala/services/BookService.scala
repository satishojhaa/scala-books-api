package com.sko.books.rest
package services

import models.{Book, BookUpdate}

import scala.concurrent.{ExecutionContext, Future}

class BookService(implicit val executionContext: ExecutionContext) {

  var books = Vector.empty[Book]

  def createBook(book: Book): Future[Option[Int]] = Future {
    books.find(_.id == book.id) match {
      case Some(q) => None // Conflict! id is already taken
      case None =>
        books = books :+ book
        Some(book.id)
    }
  }

  def getBook(id: Int): Future[Option[Book]] = Future {
    books.find(_.id == id)
  }

  def updateBook(id: Int, update: BookUpdate): Future[Option[Book]] = {

    def updateEntity(book: Book): Book = {
      val title = update.title.getOrElse(book.title)
      val author = update.author.getOrElse(book.author)
      val publishedDate = update.published.getOrElse(book.published)
      Book(id, title, author, publishedDate)
    }

    getBook(id).flatMap {
      case None => Future {
        None
      } // No book found, nothing to update
      case Some(book) =>
        val updatedBook = updateEntity(book)
        deleteBook(id).flatMap { _ =>
          createBook(updatedBook).map(_ => Some(updatedBook))
        }
    }
  }

  def deleteBook(id: Int): Future[Unit] = Future {
    books = books.filterNot(_.id == id)
  }


}
