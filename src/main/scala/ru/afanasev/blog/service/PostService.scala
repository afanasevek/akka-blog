package ru.afanasev.blog.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.afanasev.blog.dao.{ModerationStatus, Post, PostDao}

import scala.beans.BeanProperty

@Service
class PostService {

  @BeanProperty
  @Autowired
  val postDao:PostDao = null

  def getAllPosts(offset:Int, limit: Int, mode: String): List[Post] ={
    postDao.findAllPostsOrderByTimeDesc()
  }

}
