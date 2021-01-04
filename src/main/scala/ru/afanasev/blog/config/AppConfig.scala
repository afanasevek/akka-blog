package ru.afanasev.blog.config

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import ru.afanasev.blog.dao.{PostDao, UserDao}
import ru.afanasev.blog.routes.{MainRoute, PostRoute, UserRoute}
import ru.afanasev.blog.service.PostService

import scala.concurrent.Future

@Configuration
class AppConfig {

  @Autowired
  val mainRoute:MainRoute = null

  @Bean
  def startActorSystem(): Future[Http.ServerBinding] ={
    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(mainRoute.getRoute())
    bindingFuture
  }
  @Bean
  def getUserDao():UserDao = {
    new UserDao
  }
  @Bean
  def getPostDao ():PostDao = {
    new PostDao
  }
  @Bean
  def getUserRoute(): UserRoute ={
    new UserRoute
  }
  @Bean
  def getPostRoute():PostRoute={
    new PostRoute
  }
  @Bean
  def getMainRoute():MainRoute={
    new MainRoute
  }
  @Bean
  def getPostService():PostService = {
    new PostService
  }

}
