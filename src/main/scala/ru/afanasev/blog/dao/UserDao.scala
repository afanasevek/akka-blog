package ru.afanasev.blog.dao

import org.springframework.stereotype.Component
import ru.afanasev.blog.dao.User.column
import scalikejdbc._



@Component
class UserDao {

  def save(userId: Long, isModerator: Boolean, name: String, email: String, password: String, photo: Option[String]): Unit = {
    DB localTx { implicit session =>
      withSQL {
        insert.into(User).namedValues(
          column.userId -> userId,
          column.isModerator -> isModerator,
          column.regTime -> sqls.currentTimestamp,
          column.name -> name,
          column.email -> email,
          column.password -> password,
          column.photo -> photo)
      }.update.apply()
    }
  }

  def findUserById(id: Long): Option[User] ={
    val u = User.syntax("u")
    DB readOnly { implicit session =>
      withSQL {
        select.from(User as u).where.eq(u.userId, id)
      }.map(User(u.resultName)).single.apply()
    }
  }
}
case class User(userId: Long, isModerator: Boolean, regTime: String, name: String, email: String, password: String, photo: Option[String])
object User extends SQLSyntaxSupport[User] {
  override def tableName: String = "users"

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
}
