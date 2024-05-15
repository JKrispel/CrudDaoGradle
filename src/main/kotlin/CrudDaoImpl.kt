import org.slf4j.LoggerFactory

class CrudDaoImpl: CrudDao {
    // logger dla bazy danych
    companion object {
        private val logger = LoggerFactory.getLogger(CrudDaoImpl::class.java)
    }
    // persistance layer
    private val hashGames = hashMapOf<String, Game>() // kompozycja

    override fun addGame(game: Game) {

            hashGames[game.title] = game
    }

    override fun getGame(gameName: String): Game? {

        return hashGames[gameName]
    }

    override fun updateGame(gameName: String) {


        if (hashGames.containsKey(gameName)) {

            val game = hashGames[gameName]
            game!!.edit()
            hashGames.remove(gameName)
            hashGames[game.title] = game    // wpis mapy z nowym kluczem
            gameUpdatedInterface(game)

        } else {
            logger.error("Nie ma tej gry w bazie danych")
            throw Exception("Nie ma tej gry w bazie danych")
        }
    }

    override fun deleteGame(gameName: String) {



        if (hashGames.containsKey(gameName)) {


            hashGames.remove(gameName)
            gameDeletedInterface(gameName)
        } else {
            logger.error("Nie ma tej gry w bazie danych")
            throw Exception("Nie ma tej gry w bazie danych")
        }
    }

    override fun getAllGames(): MutableList<Game> {

        val gameList = mutableListOf<Game>()

        hashGames.values.forEach {
            gameList.add(it)
        }

        return gameList
    }
}