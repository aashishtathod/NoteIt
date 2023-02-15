package aashishtathod.dev.data.dao

import aashishtathod.dev.entity.User
import java.util.*

interface UserDao {
    suspend fun registerUser(username: String, password: String, name: String): User
    suspend fun findByUsernameAndPassword(username: String,password: String): User?
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun findByUserId(userId : Int) : User?
}