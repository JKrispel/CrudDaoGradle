import org.jetbrains.exposed.sql.Database
import java.util.*

object DatabaseConfig {
    fun connect() {
        val props = Properties().apply {
            load(DatabaseConfig::class.java.getResourceAsStream("/database.properties"))
        }
        Database.connect(
            url = props.getProperty("db.url"),
            driver = props.getProperty("db.driver"),
            user = props.getProperty("db.user"),
            password = props.getProperty("db.password")
        )
    }
}
