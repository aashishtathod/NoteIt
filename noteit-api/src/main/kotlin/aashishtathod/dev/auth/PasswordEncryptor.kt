package aashishtathod.dev.auth

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class PasswordEncryptor {
    private val secret = System.getenv("PASSWORD_ENCRYPTION_KEY")
    private val hMacKey: SecretKeySpec = SecretKeySpec(secret.toByteArray(), ALGORITHM)

    fun encrypt(data: String): String {
        val hMac = Mac.getInstance(ALGORITHM)
        hMac.init(hMacKey)
        return hex(hMac.doFinal(data.toByteArray(Charsets.UTF_8)))
    }

    companion object {
        private const val ALGORITHM = "HmacSHA256"
    }
}