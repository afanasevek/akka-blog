package ru.afanasev.blog.dao

import org.springframework.stereotype.Component
import scalikejdbc._



@Component
class UserDao {

  def apply(g: ResultName[User])(rs: WrappedResultSet) = {
    new User(
      userId = rs.get(g.userId),
      isModerator = rs.get(g.isModerator),
      regTime = rs.get(g.regTime),
      name = rs.get(g.name),
      email = rs.get(g.email),
      password = rs.get(g.password),
      photo = Option(rs.get(g.photo)))
  }
  def print(): String ={
    "hello from context"
  }
}
case class User(userId: String, isModerator: Boolean, regTime: String, name: String, email: String, password: String, photo: Option[String])
object User extends SQLSyntaxSupport[User] {
  override def tableName: String = "users"
}
