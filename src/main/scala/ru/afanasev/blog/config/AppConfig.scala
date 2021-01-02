package ru.afanasev.blog.config

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import ru.afanasev.blog.dao.UserDao
import ru.afanasev.blog.routes.UserRoute

import scala.concurrent.Future

@Configuration
class AppConfig {

  @Autowired
  val userRoute:UserRoute = null

  @Bean
  def startActorSystem(): Future[Http.ServerBinding] ={
    implicit val system: ActorSystem = ActorSystem("simple-http")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val log = Logging(system, getClass)
    val port = 8080
    val bindingFuture = Http().bindAndHandle(userRoute.userRoute, "localhost", port)
    log.info(s"Server started at the port $port")
    bindingFuture
  }
  @Bean
  def getUserDao():UserDao = {
    new UserDao
  }
  @Bean
  def getUserRoute(): UserRoute ={
    new UserRoute
  }

}
