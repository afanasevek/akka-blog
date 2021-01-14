package ru.afanasev.blog.common

import java.text.SimpleDateFormat
import java.util
import java.util. Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object Utils {

    def stringToTimeStamp(date: String): Long ={
      new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime
    }
    def decryptData(secret :String, encryptedString:String): String ={
      val decodedKey = Base64.getDecoder.decode(secret)
      try {
        val cipher = Cipher.getInstance("AES")
        val originalKey = new SecretKeySpec(util.Arrays.copyOf(decodedKey, 16), "AES")
        cipher.init(Cipher.DECRYPT_MODE, originalKey)
        val cipherText = cipher.doFinal(Base64.getDecoder.decode(encryptedString))
        new String(cipherText)
      } catch {
        case e: Exception =>
          throw new RuntimeException("Error occured while decrypting data", e)
      }
    }
}
