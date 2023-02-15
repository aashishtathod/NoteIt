package aashishtathod.dev.data.db.tables

import org.jetbrains.exposed.sql.Table

object UsersTable : Table() {
    val name = varchar("name", length = 24)
    val username = varchar("username", length = 30).uniqueIndex()
    val password = text("password")
    val userId = integer("user_id").autoIncrement()

    override val primaryKey = PrimaryKey(userId)
}