package aashishtathod.dev.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object NotesTable : Table() {
    val userId = integer("user_id").references(UsersTable.userId)
    val noteId = integer("note_id").autoIncrement()
    var title = varchar("note_title", length = 30)
    var note = text("note_text")
    var createdAt = datetime("created_at").default(DateTime.now())
    var isPinned = bool("is_pinned").default(false)
    var updatedAt = datetime("updated_at").default(DateTime.now())

    override val primaryKey: PrimaryKey = PrimaryKey(noteId)

}