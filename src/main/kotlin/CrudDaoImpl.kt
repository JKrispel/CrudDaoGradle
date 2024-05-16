import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class CrudDaoImpl : CrudDao {
    // logger dla bazy danych
    companion object {
        private val logger = LoggerFactory.getLogger(CrudDaoImpl::class.java)
    }

    override fun addGame(game: GameData) {
        transaction {
            when (game) {
                is PcGameData -> PcGame.new {
                    title = game.title
                    year = game.year
                    month = game.month
                    day = game.day
                    requirements = game.requirements
                    modding = game.modding
                }
                is MobileGameData -> MobileGame.new {
                    title = game.title
                    year = game.year
                    month = game.month
                    day = game.day
                    system = game.system
                }
                is ConsoleGameData -> ConsoleGame.new {
                    title = game.title
                    year = game.year
                    month = game.month
                    day = game.day
                    console = game.console
                }
                else -> logger.error("Nieobsługiwany typ gry: ${game.javaClass.simpleName}")
            }
        }
        logger.info("Dodano nowa gre:\n${game}")
    }

    override fun getGame(gameName: String): Game? {
        return transaction {
            PcGame.find { PcGames.title eq gameName }.firstOrNull()
                ?: MobileGame.find { MobileGames.title eq gameName }.firstOrNull()
                ?: ConsoleGame.find { ConsoleGames.title eq gameName }.firstOrNull()
        }
    }

    override fun updateGame(gameName: String) {
        transaction {
            val game = getGame(gameName)
            if (game != null) {
                game.edit()
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }

    override fun deleteGame(gameName: String) {
        transaction {
            val game = getGame(gameName)
            if (game != null) {
                when (game) {
                    is PcGame -> game.delete()
                    is MobileGame -> game.delete()
                    is ConsoleGame -> game.delete()
                    else -> logger.error("Nieobsługiwany typ gry: ${game.javaClass.simpleName}")
                }
                gameDeletedInterface(gameName)
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }

    override fun getAllGames(): MutableList<Game> {
        return transaction {
            val games = mutableListOf<Game>()
            games.addAll(PcGame.all().toList())
            games.addAll(MobileGame.all().toList())
            games.addAll(ConsoleGame.all().toList())
            games
        }
    }
}
