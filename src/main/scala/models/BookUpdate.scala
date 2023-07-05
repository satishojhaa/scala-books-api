package com.sko.books.rest
package models

import java.util.Date

case class BookUpdate(title: Option[String], author: Option[String], published: Option[Date])
