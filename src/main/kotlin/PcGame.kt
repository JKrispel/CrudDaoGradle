import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object PcGames : IntIdTable() {
    val title = varchar("title", 255)
    val year = integer("year")
    val month = integer("month")
    val day = integer("day")
    val requirements = varchar("requirements", 255)
    val modding = varchar("modding", 255)
}

class PcGame(id: EntityID<Int>) : IntEntity(id), Game {
    companion object : IntEntityClass<PcGame>(PcGames)

    override var title by PcGames.title
    override var year by PcGames.year
    override var month by PcGames.month
    override var day by PcGames.day
    var requirements by PcGames.requirements
    var modding by PcGames.modding

    override fun toString(): String =
        "(PC) $title, release date: $day.$month.$year, modding: $modding, requirements: $requirements"

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
                val data = inputPcDescriptionInterface(title)
                modding = data[0]
                requirements = data[1]
            }
        }
    }
}
