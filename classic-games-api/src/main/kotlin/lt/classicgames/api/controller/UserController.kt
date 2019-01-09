package lt.classicgames.api.controller

import lt.classicgames.api.model.User
import lt.classicgames.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @PostMapping("/sign-up")
    fun signup(@RequestBody user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return userRepository.save(user)
    }
}