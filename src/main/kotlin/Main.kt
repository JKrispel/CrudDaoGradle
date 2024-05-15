// TODO testy jednostkowe
// TODO zamienic printy w CrudDaoImpl na wyjatki
// TODO dodaj serializacje danych

fun main() {

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
            "6" -> saveGameData(gameDatabase.getAllGames(), saveToFileInterface())
            "7" -> loadGameData(loadFromFileInterface()).forEach { gameDatabase.addGame(it) }
            "8" -> break

        }
        println("Wcisnij enter, aby wrocic do menu")
        readln()
    }


    println("Do zobaczenia!")
}

