package aashishtathod.dev.data.daoImpl

import aashishtathod.dev.data.dao.UserDao
import aashishtathod.dev.data.db.DatabaseFactory
import aashishtathod.dev.data.db.tables.UsersTable
import aashishtathod.dev.entity.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserDaoImpl : UserDao {
    override suspend fun registerUser(username: String, password: String, name: String): User {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = UsersTable.insert {
                it[UsersTable.name] = name
                it[UsersTable.username] = username
                it[UsersTable.password] = password
            }
        }
        return rowToUser(statement?.resultedValues!![0])
    }

    override suspend fun findByUsernameAndPassword(username: String, password: String): User? =
        DatabaseFactory.dbQuery {
            UsersTable.select { UsersTable.username.eq(username) and UsersTable.username.eq(username) }
                .map { rowToUser(it) }
                .singleOrNull()
        }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        val user = DatabaseFactory.dbQuery {
            UsersTable.select { UsersTable.username.eq(username) }
                .map { rowToUser(it) }
                .singleOrNull()
        }
        return user == null
    }

    override suspend fun findByUserId(userId: Int): User? = DatabaseFactory.dbQuery {
        UsersTable.select { UsersTable.userId.eq(userId) }
            .map { rowToUser(it) }
            .singleOrNull()
    }

    private fun rowToUser(row: ResultRow): User {
     //   return if (row == null) null
     //   else
        return  User(
            row[UsersTable.name],
            row[UsersTable.username],
            row[UsersTable.password],
            row[UsersTable.userId],
        )
    }
}