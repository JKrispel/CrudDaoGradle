interface CrudDao {

    fun addGame(game: GameData)
    fun getGame(gameName: String): Game?
    fun updateGameTitle(gameName: String, newTitle: String)
    fun updateGameDate(gameName: String, year: Int, month: Int, day: Int)
    fun updatePcGameDescription(gameName: String, modding: String, requirements: String)
    fun updateMobileGameDescription(gameName: String, system: String)
    fun updateConsoleGameDescription(gameName: String, console: String)
    fun deleteGame(gameName: String)
    fun getAllGames(): MutableList<Game>
}
