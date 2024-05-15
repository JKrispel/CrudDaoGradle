interface CrudDao {

    fun addGame(game: Game)
    fun getGame(gameName: String): Game?
    fun updateGame(gameName: String)
    fun deleteGame(gameName: String)
    fun getAllGames(): MutableList<Game>
}