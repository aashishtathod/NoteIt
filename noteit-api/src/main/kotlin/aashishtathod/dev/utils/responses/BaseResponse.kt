package aashishtathod.dev.utils.responses

import io.ktor.http.*

interface BaseResponse {
    val status: Int
    val message: String
}

/**
 * HTTP Response Status. Used for evaluation of [HttpResponse] type.
 */
enum class State(val value: Int) {
    SUCCESS(HttpStatusCode.OK.value),
    NOT_FOUND(HttpStatusCode.NotFound.value),
    FAILED(HttpStatusCode.BadRequest.value),
    UNAUTHORIZED(HttpStatusCode.Unauthorized.value),
}