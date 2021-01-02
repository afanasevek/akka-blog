package ru.afanasev.blog

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.afanasev.blog.config.AppConfig
import ru.afanasev.blog.dao.UserDao
import ru.afanasev.blog.routes.UserRoute
import scalikejdbc.config.DBs
import scalikejdbc.{DB, SQL}

import scala.io.Source

object Application extends App {
  DBs.setupAll
  initDb
  val context: AnnotationConfigApplicationContext = new AnnotationConfigApplicationContext()
  context.register(classOf[AppConfig])
  context.refresh()

  def initDb(): Unit = {
    val scripts = Source.fromResource("init.sql")
      .getLines()
      .mkString
      .split(";")
    DB.localTx {
      implicit session =>
        scripts.map(script => {
          SQL(script).execute().apply()
        })
    }
  }
}
