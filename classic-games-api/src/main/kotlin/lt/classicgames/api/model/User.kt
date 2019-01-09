package lt.classicgames.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "users")
class User(
        @Id var _id: String?,
        var username: String,
        var password: String,
        var roles: List<Role>?
)