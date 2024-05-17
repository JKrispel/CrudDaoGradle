// TODO testy jednostkowe
// przed każdym użyciem należy zastąpić HASŁO w pliku database.properties
// rzeczywistym hasłem użytkownika root bazy danych MySQL
// oraz ze baza danych MySQL istnieje i url do niej jest poprawny
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
            "3" -> gameDatabase.updateGame(inputGameNameInterface())
            "4" -> gameDatabase.deleteGame(deleteGameInterface())
            "5" -> gameDatabase.getAllGames().forEach { println(it) }
            "6" -> break
        }
        println("Wcisnij enter, aby wrocic do menu")
        readln()
    }

    println("Do zobaczenia!")
}
