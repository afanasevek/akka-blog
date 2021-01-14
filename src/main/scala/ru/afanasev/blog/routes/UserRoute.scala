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
import com.emarsys.jwt.akka.http._

import scala.beans.BeanProperty

final case class PostRequest(timestamp: Long, active: Boolean, title: String, tags: List[String], text: String)

final case class CustomTokenData(value: String, value1: String)

trait UserJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat5(PostRequest)
  implicit val test = jsonFormat2(CustomTokenData)
}

@Component
class UserRoute extends Directives with UserJsonSupport {
  @BeanProperty
  @Autowired
  val jwtAuth: JwtAuthentication = null

  @BeanProperty
  val userRoute: Route = {
    path("user") {
      get {
        val testing = CustomTokenData("hello", "world")
        complete(jwtAuth.generateToken(testing))
      }
    }
  }

  object UserMapper {
    def userToUserDto(user: User): UserDto = {
      UserDto(
        user.userId,
        user.name
      )
    }
  }

}

