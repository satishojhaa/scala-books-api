package com.sko.books.rest
package routing

import akka.http.scaladsl.server.Route

trait HomeHandler extends BaseHandler {
  def homeRoute: Route = pathPrefix("home"){
    get{
      headerValueByName("User-Agent") { header =>
        complete(s""" Hey there you are using "User-Agent" : "$header"!!""")
      }
    }
  }
}
