package ru.afanasev.blog.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.afanasev.blog.dao.{User, UserDao}
import ru.afanasev.blog.routes.PostRequest

import scala.beans.BeanProperty

@Service
class UserService {
  @BeanProperty
  @Autowired
  val userDao: UserDao = null

  def savePost(post: PostRequest): Unit ={

  }

  def getUserById(id: Long): Option[User]={
    userDao.findUserById(id)
  }


}
