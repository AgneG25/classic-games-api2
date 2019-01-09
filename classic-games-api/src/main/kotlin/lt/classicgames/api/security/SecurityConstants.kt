package lt.classicgames.api.security

object SecurityConstants {
    val SECRET = "6c6aZKSva3LeLaUKTHgkaBQ4ddFZwYSh"
    val EXPIRATION_TIME: Long = 864000000 // 10 days
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
    val TOKEN_KEY = "access_token"
    val AUTHORITY_CLAIMS = "authorities"

    val LOGIN_URL = "/login"
    val SIGN_UP_URL = "/api/user/sign-up"
    val LOCATIONS_URL = "/api/location"
}