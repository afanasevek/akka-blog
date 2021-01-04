package ru.afanasev.blog.routes

import akka.http.scaladsl.server.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import akka.http.scaladsl.server.Directives._
import scala.beans.BeanProperty



@Component
class MainRoute {

  @BeanProperty
  @Autowired
  val userRoute: UserRoute = null
  @BeanProperty
  @Autowired
  val postRoute: PostRoute = null

  def getRoute():Route={
    userRoute.userRoute ~ postRoute.postRoute
  }
}
