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
                logger.info("Gra $gameName usunieta!")
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


    override fun updateGameTitle(gameName: String, newTitle: String) {
        transaction {
            val game = getGame(gameName)
            if (game != null) {
                game.title = newTitle
                logger.info("Nowy tytul ${game.title}:\n${game}")
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }

    override fun updateGameDate(gameName: String, year: Int, month: Int, day: Int) {
        transaction {
            val game = getGame(gameName)
            if (game != null) {
                game.year = year
                game.month = month
                game.day = day
                logger.info("Nowa data premiery ${game.title}:\n${game}")
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }

    override fun updatePcGameDescription(gameName: String, modding: String, requirements: String) {
        transaction {
            val game = getGame(gameName) as? PcGame
            if (game != null) {
                game.modding = modding
                game.requirements = requirements
                logger.info("Nowy opis ${game.title}:\n${game}")
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }

    override fun updateMobileGameDescription(gameName: String, system: String) {
        transaction {
            val game = getGame(gameName) as? MobileGame
            if (game != null) {
                game.system = system
                logger.info("Nowy opis ${game.title}:\n${game}")
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }

    override fun updateConsoleGameDescription(gameName: String, console: String) {
        transaction {
            val game = getGame(gameName) as? ConsoleGame
            if (game != null) {
                game.console = console
                logger.info("Nowy opis ${game.title}:\n${game}")
            } else {
                logger.error("Nie ma tej gry w bazie danych")
                throw Exception("Nie ma tej gry w bazie danych")
            }
        }
    }
}

