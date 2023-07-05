package com.sko.books.rest
package serializers

import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.ext.JodaTimeSerializers
import org.json4s.native.Serialization
import org.json4s.{DefaultFormats, Formats, native}

import java.text.SimpleDateFormat

trait JsonSupport extends Json4sSupport {

  implicit val serialization: Serialization.type = native.Serialization

  implicit def json4sFormats: Formats = customDateFormat ++ JodaTimeSerializers.all

  private val customDateFormat = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("dd/MMM/yyyy")
  }

}
