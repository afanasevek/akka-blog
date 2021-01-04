package ru.afanasev.blog.routes

import akka.event.Logging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.afanasev.blog.Application.context
import ru.afanasev.blog.dao._
import spray.json.DefaultJsonProtocol
import com.emarsys.jwt._
import com.emarsys.jwt.akka.http.{JwtAuthentication, JwtConfig}
import com.typesafe.config.{Config, ConfigFactory}

import scala.beans.BeanProperty

final case class PostRequest(timestamp: Long, active: Boolean, title: String, tags: List[String], text: String)
case class CustomTokenData(value: Any, value1: Any)
trait UserJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat5(PostRequest)
  implicit val test = jsonFormat2(CustomTokenData)
}

@Component
class UserRoute extends Directives with UserJsonSupport with JwtAuthentication {
 val JwtAuthentication: JwtAuthentication
  @BeanProperty
  val userRoute: Route = {
    path("user") {
      put {
        entity(as[PostRequest]) { post =>
          val customTokenData = CustomTokenData("hello", "world")
          complete(generateToken(customTokenData))
        }
      }
    }
  }
  override val jwtConfig: JwtConfig = new JwtConfig(ConfigFactory.load())
}

