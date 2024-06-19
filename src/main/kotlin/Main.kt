// UWAGA ! ! !
// przed każdym użyciem należy zastąpić parametry w pliku "database.properties"
// odpowiednimi do pracy z wybraną bazą danych (MySQL , PostgreSQL itp.)
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    // Inicjalizacja połączenia z bazą danych
    DatabaseConfig.connect()

    // Tworzenie tabel, jeśli nie istnieją
    transaction {
        SchemaUtils.create(PcGames, MobileGames, ConsoleGames)
    }

    println(
        "Witaj w CRUD Game Database,\n" +
                "bazie danych do przechowywania informacji o grach wideo\n" +
                "(Wcisnij enter, aby wyswietlic menu)"
    )
    readln()

    val gameDatabase: CrudDao = CrudDaoImpl() // implementuje interfejs CrudDao

    while (true) {
        showMenu()

        when (readln()) {
            // wywołanie odpowiednich metod interfejsu na DAO (Data Access Object)
            // parametrem metod są odpowiednie graficzne interfejsy zbierające dane
            "1" -> gameDatabase.addGame(addGameInterface())
            "2" -> println(gameDatabase.getGame(findGameInterface()))
            "3" -> {
                val gameName = inputGameNameInterface()
                val option = whatToEditInterface()
                when (option) {
                    "1" -> {
                        val newTitle = inputTitleInterface(gameName)
                        gameDatabase.updateGameTitle(gameName, newTitle)
                    }
                    "2" -> {
                        val dateParams = inputDateInterface(gameName)
                        gameDatabase.updateGameDate(gameName, dateParams[0], dateParams[1], dateParams[2])
                    }
                    "3" -> {
                        val game = gameDatabase.getGame(gameName)
                        if (game is PcGame) {
                            val descParams = inputPcDescriptionInterface(gameName)
                            gameDatabase.updatePcGameDescription(gameName, descParams[0], descParams[1])
                        } else if (game is MobileGame) {
                            val system = inputMobileDescriptionInterface(gameName)
                            gameDatabase.updateMobileGameDescription(gameName, system)
                        } else if (game is ConsoleGame) {
                            val console = inputConsoleDescriptionInterface(gameName)
                            gameDatabase.updateConsoleGameDescription(gameName, console)
                        }
                    }
                }
            }
            "4" -> gameDatabase.deleteGame(deleteGameInterface())
            "5" -> gameDatabase.getAllGames().forEach { println(it) }
            "6" -> break
        }
        println("Wcisnij enter, aby wrocic do menu")
        readln()
    }

    println("Do zobaczenia!")
}
