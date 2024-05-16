import org.slf4j.LoggerFactory

val gameLogger = LoggerFactory.getLogger("GameLogger")

fun addGameInterface(): GameData {
    clearConsole()
    println(
        "Wybierz:\n" +
                "1. Gra PC\n" +
                "2. Gra mobilna\n" +
                "3. Gra konsolowa\n"
    )

    var newGame: GameData? = null

    fun createPcGameData(): PcGameData {
        println("Podaj tytul:")
        val title = readln()
        println("Podaj rok wydania:")
        val year = getNumberInput()
        println("Podaj miesiac wydania:")
        val month = getNumberInput()
        println("Podaj dzien wydania:")
        val day = getNumberInput()
        println("Czy wspiera modowanie:")
        val modding = readln()
        println("Dowolnie opisz wymagania sprzetowe:")
        val requirements = readln()

        return PcGameData(
            title = title,
            year = year.toInt(),
            month = month.toInt(),
            day = day.toInt(),
            requirements = requirements,
            modding = modding
        )
    }

    fun createMobileGameData(): MobileGameData {
        println("Podaj tytul:")
        val title = readln()
        println("Podaj rok wydania:")
        val year = getNumberInput()
        println("Podaj miesiac wydania:")
        val month = getNumberInput()
        println("Podaj dzien wydania:")
        val day = getNumberInput()
        println("Podaj system, na ktory jest gra:")
        val system = readln()

        return MobileGameData(
            title = title,
            year = year.toInt(),
            month = month.toInt(),
            day = day.toInt(),
            system = system
        )
    }

    fun createConsoleGameData(): ConsoleGameData {
        println("Podaj tytul:")
        val title = readln()
        println("Podaj rok wydania:")
        val year = getNumberInput()
        println("Podaj miesiac wydania:")
        val month = getNumberInput()
        println("Podaj dzien wydania:")
        val day = getNumberInput()
        println("Podaj konsole, na ktora jest gra:")
        val console = readln()

        return ConsoleGameData(
            title = title,
            year = year.toInt(),
            month = month.toInt(),
            day = day.toInt(),
            console = console
        )
    }

    var validOption = false

    while (!validOption) {
        validOption = true
        when (readln()) {
            "1" -> newGame = createPcGameData()
            "2" -> newGame = createMobileGameData()
            "3" -> newGame = createConsoleGameData()
            else -> {
                println("Wybierz odpowiednia z opcji")
                validOption = false
            }
        }
    }

    return newGame  ?: throw IllegalStateException("Nie udalo sie zainicjalizowac nowej gry")
}

interface GameData {

    val title: String
    val year: Int
    val month: Int
    val day: Int
}

data class PcGameData(
    override val title: String,
    override val year: Int,
    override val month: Int,
    override val day: Int,
    val requirements: String,
    val modding: String
) : GameData

data class MobileGameData(
    override val title: String,
    override val year: Int,
    override val month: Int,
    override val day: Int,
    val system: String
) : GameData

data class ConsoleGameData(
    override val title: String,
    override val year: Int,
    override val month: Int,
    override val day: Int,
    val console: String,
) : GameData



fun findGameInterface(): String {

    clearConsole()

    println("Podaj nazwe gry, ktora chcesz wyszukac:")

    return readln()
}


fun inputGameNameInterface(): String {

    clearConsole()

    println("Podaj nazwe gry, ktorej informacje chcesz edytowac:")

    return readln()
}


fun whatToEditInterface(): String {

    while (true) {
        println(
            "ktora informacje chcesz edytowac?\n" +
                    "1. nazwe\n" +
                    "2. date premiery\n" +
                    "3. opis\n"
        )

        val input = readln()

        if (input == "1" || input == "2" || input == "3") {

            return input
        } else {

            println("Wybrano niewlasciwa opcje")
        }
    }
}


fun inputTitleInterface(gameName: String): String {

    println("Podaj nowy tytul dla ${gameName}:")

    return readln()
}


fun inputDateInterface(gameName: String): List<Int> {

    val parameters = MutableList(3) { 0 }

    println("Podaj rok wydania ${gameName}:")
    parameters[0] = getNumberInput().toInt()
    println("Podaj miesiac wydania ${gameName}:")
    parameters[1] = getNumberInput().toInt()
    println("Podaj dzien wydania ${gameName}:")
    parameters[2] = getNumberInput().toInt()

    return parameters
}


fun inputPcDescriptionInterface(gameName: String): List <String> {

    val descParameters = MutableList(2) {""}

    println("Czy $gameName wspiera modowanie?:")
    descParameters[0] = readln()
    println("Podaj wymagania sprzetowe ${gameName}?:")
    descParameters[1] = readln()

    return descParameters
}


fun inputMobileDescriptionInterface(gameName: String): String {

    println("Na jakich systemach zagramy w ${gameName}?:")

    return readln()
}


fun inputConsoleDescriptionInterface(gameName: String): String {

    println("Na jakich konsolach zagramy w ${gameName}?:")

    return readln()
}

fun deleteGameInterface(): String {

    clearConsole()

    println("Podaj nazwe gry, ktora chcesz usunac:")

    return readln()
}


fun gameDeletedInterface(gameName: String) {

    println("\nGra $gameName usunieta!")
    gameLogger.info("Gra $gameName usunieta!")
}

fun getNumberInput(): String {

    var input = readln()

    while (true) {
        try {
            input.toInt()
            break
        } catch (e: NumberFormatException) {
            println("Podaj wartosc ponownie (musi byc liczbowa):")
            input = readln()
        }
    }
    return input
}
