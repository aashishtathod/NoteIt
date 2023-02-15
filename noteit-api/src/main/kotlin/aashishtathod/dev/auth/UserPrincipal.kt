package aashishtathod.dev.auth

import aashishtathod.dev.entity.User
import io.ktor.server.auth.*

class UserPrincipal(val user: User) : Principal