import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.modules.*
import java.io.File

// Dostosowanie konfiguracji JSON aby wspierala polimorfizm
val module = SerializersModule {
    polymorphic(Game::class) {
        subclass(PcGame::class)
        subclass(ConsoleGame::class)
        subclass(MobileGame::class)
    }
}

val json = Json { serializersModule = module }


fun saveGameData(games: List<Game>, filename: String) {
    val jsonData = json.encodeToString(games)
    File(filename).writeText(jsonData)
    println("Dane zapisano do pliku: $filename")
}


fun loadGameData(filename: String): List<Game> {
    val jsonData = File(filename).readText()
    return json.decodeFromString<List<Game>>(jsonData)
}
