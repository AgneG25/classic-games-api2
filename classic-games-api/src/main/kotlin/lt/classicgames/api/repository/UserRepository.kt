package lt.classicgames.api.repository

import lt.classicgames.api.model.User
import org.springframework.data.mongodb.repository.MongoRepository


interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
}