package aashishtathod.dev.data.db

import aashishtathod.dev.data.db.tables.NotesTable
import aashishtathod.dev.data.db.tables.UsersTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        val tables = arrayOf(UsersTable, NotesTable)

        Database.connect(createDataSource())

        transaction {
            SchemaUtils.createMissingTablesAndColumns(*tables)
        }
    }

    private fun createDataSource(): HikariDataSource {
        val config = HikariConfig()

        val dbHost = System.getenv("PGHOST")
        val dbPort = System.getenv("PGPORT")
        val dbName = System.getenv("PGDATABASE")
        val dbPassword = System.getenv("PGPASSWORD")
        val dbUsername = System.getenv("PGUSER")
        val dbDriver = System.getenv("JDBC_DRIVER")

        config.apply {
            password = dbPassword
            username = dbUsername
            jdbcUrl = "jdbc:postgresql://$dbHost:$dbPort/$dbName"
            driverClassName = dbDriver
            maximumPoolSize = 3
            isAutoCommit = true
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction {
                block()
            }
        }
}