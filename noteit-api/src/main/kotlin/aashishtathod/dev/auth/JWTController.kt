package aashishtathod.dev.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JWTController {

    private val secret = System.getenv("JWT_SECRET_KEY")
    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .build()

    /**
     * Generates JWT token from [userId].
     */
    fun sign(userId: Int): String = JWT
        .create()
        .withSubject("Authentication")
        .withIssuer(ISSUER)
        .withClaim(ClAIM, userId)
        .sign(algorithm)

    companion object {
        private const val ISSUER = "http://0.0.0.0:8080/"
        private const val AUDIENCE = "http://0.0.0.0:8080/"
        const val ClAIM = "userId"
    }

}