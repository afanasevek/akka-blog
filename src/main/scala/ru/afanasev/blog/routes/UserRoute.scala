package ru.afanasev.blog.routes

import akka.event.Logging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.afanasev.blog.Application.context
import ru.afanasev.blog.dao._
import spray.json.DefaultJsonProtocol

import scala.beans.BeanProperty

final case class PostRequest(timestamp: Long, active: Boolean, title: String, tags: List[String], text: String)
trait UserJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat5(PostRequest)
}

@Component
class UserRoute extends Directives with UserJsonSupport {

  @BeanProperty
  val userRoute: Route = {
    path("user") {
      put {
        entity(as[PostRequest]) { post =>
          println(post)
          complete("hello")
        }
      }
    }
  }
}

