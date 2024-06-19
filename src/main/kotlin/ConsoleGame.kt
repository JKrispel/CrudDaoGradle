import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ConsoleGames : IntIdTable() {
    val title = varchar("title", 255)
    val year = integer("year")
    val month = integer("month")
    val day = integer("day")
    val console = varchar("console", 255)
}

class ConsoleGame(id: EntityID<Int>) : IntEntity(id), Game {
    companion object : IntEntityClass<ConsoleGame>(ConsoleGames)

    override var title by ConsoleGames.title
    override var year by ConsoleGames.year
    override var month by ConsoleGames.month
    override var day by ConsoleGames.day
    var console by ConsoleGames.console

    override fun toString(): String =
        "(Console) $title, release date: $day.$month.$year, console: $console"
}
