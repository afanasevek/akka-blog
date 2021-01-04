package ru.afanasev.blog.dao

import org.springframework.stereotype.Component
import ru.afanasev.blog.dao.ModerationStatus.ModerationStatus
import scalikejdbc._
@Component
class PostDao {
  def findAllPostsOrderByTimeDesc() ={
    val p = Post.syntax("o")
    DB localTx{ implicit session =>
      withSQL {
        select.from(Post as p)
      }.update.apply()
    }
  }
}
case class Post(
                 postId: Long,
                 isActive: Boolean,
                 moderationStatus: String,
                 moderationId: Long,
                 userId: Long,
                 time: String,
                 title: String,
                 text: String,
                 viewCount: Int)
object Post extends SQLSyntaxSupport[Post] {
  override def tableName: String = "posts"

  def apply(g: ResultName[Post])(rs: WrappedResultSet) = {
    new Post(
      postId = rs.get(g.userId),
      isActive = rs.get(g.isActive),
      moderationStatus = rs.get(g.moderationStatus),
      moderationId = rs.get(g.moderationId),
      userId = rs.get(g.userId),
      time = rs.get(g.time),
      title = rs.get(g.title),
      text = rs.get(g.text),
      viewCount = rs.get(g.viewCount))
  }
}
object ModerationStatus extends Enumeration {
  type ModerationStatus = Value
  val NEW, ACCEPTED, DECLINED = Value
}