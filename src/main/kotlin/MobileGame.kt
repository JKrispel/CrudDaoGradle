import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object MobileGames : IntIdTable() {
    val title = varchar("title", 255)
    val year = integer("year")
    val month = integer("month")
    val day = integer("day")
    val system = varchar("system", 255)
}

class MobileGame(id: EntityID<Int>) : IntEntity(id), Game {
    companion object : IntEntityClass<MobileGame>(MobileGames)

    override var title by MobileGames.title
    override var year by MobileGames.year
    override var month by MobileGames.month
    override var day by MobileGames.day
    var system by MobileGames.system

    override fun toString(): String =
        "(Mobile) $title, release date: $day.$month.$year, system: $system"

    override fun edit() {
        when (whatToEditInterface()) {
            "1" -> {
                title = inputTitleInterface(title)
            }
            "2" -> {
                val data = inputDateInterface(title)
                year = data[0]
                month = data[1]
                day = data[2]
            }
            "3" -> {
                system = inputMobileDescriptionInterface(title)
            }
        }
    }
}
