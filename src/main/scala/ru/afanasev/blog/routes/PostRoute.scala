package ru.afanasev.blog.routes


import akka.http.scaladsl.client.RequestBuilding.Post
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.{_symbol2NR, complete, parameters, path}
import akka.http.scaladsl.server.{Directive, Directives, Route}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.afanasev.blog.common.Utils
import ru.afanasev.blog.dao.Post
import ru.afanasev.blog.service.{PostService, UserService}
import spray.json.DefaultJsonProtocol

import scala.beans.BeanProperty

final case class UserDto(id: Long, name: String)

final case class PostDto(id: Long, timestamp: Long, user: UserDto, title: String, announce: String, likeCount: Int, dislikeCount: Int, viewCount: Int)

final case class PostResponse(count: Int, posts: List[PostDto])

trait PostJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userDtoFormat = jsonFormat2(UserDto)
  implicit val postDtoFormat = jsonFormat8(PostDto)
  implicit val postRequestFormat = jsonFormat2(PostResponse)
}



@Component
class PostRoute extends Directives with PostJsonSupport{
  @BeanProperty
  @Autowired
  val postService: PostService = null
  @BeanProperty
  @Autowired
  val userService: UserService = null
  @BeanProperty
  val postRoute: Route = {
    path("post") {
      Directives.get {
        parameters(Symbol("offset") ? 0, Symbol("limit") ? 10, Symbol("mode")) { (offset, limit, mode) =>
          postService.getAllPosts(offset, limit, mode)
          complete("hello")
        }
      }
    }
  }
//  object PostMapper {
//    def postsToListPostDto(posts: List[Post]): Unit ={
//      posts.foreach{
//        post => PostDto(
//          post.postId,
//          Utils.stringToTimeStamp(post.time),
//          UserMapper..
//          userService.getUserById(post.userId).getOrElse( throw new Exception),
//          post.title,
//          post.text,
//          0,
//          0,
//          0
//        )
//      }
//    }
//  }
}
