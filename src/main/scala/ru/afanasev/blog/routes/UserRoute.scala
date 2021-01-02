package ru.afanasev.blog.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.afanasev.blog.dao.UserDao
import scala.beans.BeanProperty

@Component
class UserRoute {

    @BeanProperty
    @Autowired
    val userDao: UserDao = null

    @BeanProperty
    val userRoute: Route = {
      path("user") {
        get {
          complete(userDao.print())
        }
      }
    }
}

