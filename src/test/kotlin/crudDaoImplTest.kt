import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class CrudDaoImplTest {

    private lateinit var dao: CrudDaoImpl

    @BeforeEach
    fun setUp() {
        // Konfiguracja bazy danych w pamięci
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")

        // Tworzenie tabel
        transaction {
            SchemaUtils.create(PcGames, MobileGames, ConsoleGames)
        }

        dao = CrudDaoImpl()
    }

    @AfterEach
    fun tearDown() {
        // Czyszczenie tabel po każdym teście
        transaction {
            SchemaUtils.drop(PcGames, MobileGames, ConsoleGames)
        }
    }

    // Testy dla PcGame

    @Test
    fun `test add and get PC game`() {
        val pcGame = PcGameData("Test PC Game", 2022, 1, 1, "High", "Yes")
        dao.addGame(pcGame)

        val retrievedGame = dao.getGame("Test PC Game")
        assertNotNull(retrievedGame)
        assertTrue(retrievedGame is PcGame)
        assertEquals("Test PC Game", retrievedGame?.title)
    }

    @Test
    fun `test update PC game title`() {
        val pcGame = PcGameData("Test PC Game", 2022, 1, 1, "High", "Yes")
        dao.addGame(pcGame)

        dao.updateGameTitle("Test PC Game", "Updated Game Title")

        val updatedGame = dao.getGame("Updated Game Title")
        assertNotNull(updatedGame)
        assertEquals("Updated Game Title", updatedGame?.title)
    }

    @Test
    fun `test update PC game date`() {
        val pcGame = PcGameData("Test PC Game", 2022, 1, 1, "High", "Yes")
        dao.addGame(pcGame)

        dao.updateGameDate("Test PC Game", 2023, 2, 2)

        val updatedGame = dao.getGame("Test PC Game")
        assertNotNull(updatedGame)
        assertEquals(2023, updatedGame?.year)
        assertEquals(2, updatedGame?.month)
        assertEquals(2, updatedGame?.day)
    }

    @Test
    fun `test update PC game description`() {
        val pcGame = PcGameData("Test PC Game", 2022, 1, 1, "High", "Yes")
        dao.addGame(pcGame)

        dao.updatePcGameDescription("Test PC Game", "No", "Low")

        val updatedGame = dao.getGame("Test PC Game") as PcGame
        assertNotNull(updatedGame)
        assertEquals("No", updatedGame.modding)
        assertEquals("Low", updatedGame.requirements)
    }

    @Test
    fun `test delete PC game`() {
        val pcGame = PcGameData("Test PC Game", 2022, 1, 1, "High", "Yes")
        dao.addGame(pcGame)

        dao.deleteGame("Test PC Game")

        val deletedGame = dao.getGame("Test PC Game")
        assertNull(deletedGame)
    }

    // Testy dla MobileGame

    @Test
    fun `test add and get mobile game`() {
        val mobileGame = MobileGameData("Test Mobile Game", 2022, 1, 1, "Android")
        dao.addGame(mobileGame)

        val retrievedGame = dao.getGame("Test Mobile Game")
        assertNotNull(retrievedGame)
        assertTrue(retrievedGame is MobileGame)
        assertEquals("Test Mobile Game", retrievedGame?.title)
    }

    @Test
    fun `test update mobile game title`() {
        val mobileGame = MobileGameData("Test Mobile Game", 2022, 1, 1, "Android")
        dao.addGame(mobileGame)

        dao.updateGameTitle("Test Mobile Game", "Updated Mobile Game Title")

        val updatedGame = dao.getGame("Updated Mobile Game Title")
        assertNotNull(updatedGame)
        assertEquals("Updated Mobile Game Title", updatedGame?.title)
    }

    @Test
    fun `test update mobile game date`() {
        val mobileGame = MobileGameData("Test Mobile Game", 2022, 1, 1, "Android")
        dao.addGame(mobileGame)

        dao.updateGameDate("Test Mobile Game", 2023, 2, 2)

        val updatedGame = dao.getGame("Test Mobile Game")
        assertNotNull(updatedGame)
        assertEquals(2023, updatedGame?.year)
        assertEquals(2, updatedGame?.month)
        assertEquals(2, updatedGame?.day)
    }

    @Test
    fun `test update mobile game description`() {
        val mobileGame = MobileGameData("Test Mobile Game", 2022, 1, 1, "Android")
        dao.addGame(mobileGame)

        dao.updateMobileGameDescription("Test Mobile Game", "iOS")

        val updatedGame = dao.getGame("Test Mobile Game") as MobileGame
        assertNotNull(updatedGame)
        assertEquals("iOS", updatedGame.system)
    }

    @Test
    fun `test delete mobile game`() {
        val mobileGame = MobileGameData("Test Mobile Game", 2022, 1, 1, "Android")
        dao.addGame(mobileGame)

        dao.deleteGame("Test Mobile Game")

        val deletedGame = dao.getGame("Test Mobile Game")
        assertNull(deletedGame)
    }

    // Testy dla ConsoleGame

    @Test
    fun `test add and get console game`() {
        val consoleGame = ConsoleGameData("Test Console Game", 2022, 1, 1, "PlayStation")
        dao.addGame(consoleGame)

        val retrievedGame = dao.getGame("Test Console Game")
        assertNotNull(retrievedGame)
        assertTrue(retrievedGame is ConsoleGame)
        assertEquals("Test Console Game", retrievedGame?.title)
    }

    @Test
    fun `test update console game title`() {
        val consoleGame = ConsoleGameData("Test Console Game", 2022, 1, 1, "PlayStation")
        dao.addGame(consoleGame)

        dao.updateGameTitle("Test Console Game", "Updated Console Game Title")

        val updatedGame = dao.getGame("Updated Console Game Title")
        assertNotNull(updatedGame)
        assertEquals("Updated Console Game Title", updatedGame?.title)
    }

    @Test
    fun `test update console game date`() {
        val consoleGame = ConsoleGameData("Test Console Game", 2022, 1, 1, "PlayStation")
        dao.addGame(consoleGame)

        dao.updateGameDate("Test Console Game", 2023, 2, 2)

        val updatedGame = dao.getGame("Test Console Game")
        assertNotNull(updatedGame)
        assertEquals(2023, updatedGame?.year)
        assertEquals(2, updatedGame?.month)
        assertEquals(2, updatedGame?.day)
    }

    @Test
    fun `test update console game description`() {
        val consoleGame = ConsoleGameData("Test Console Game", 2022, 1, 1, "PlayStation")
        dao.addGame(consoleGame)

        dao.updateConsoleGameDescription("Test Console Game", "Xbox")

        val updatedGame = dao.getGame("Test Console Game") as ConsoleGame
        assertNotNull(updatedGame)
        assertEquals("Xbox", updatedGame.console)
    }

    @Test
    fun `test delete console game`() {
        val consoleGame = ConsoleGameData("Test Console Game", 2022, 1, 1, "PlayStation")
        dao.addGame(consoleGame)

        dao.deleteGame("Test Console Game")

        val deletedGame = dao.getGame("Test Console Game")
        assertNull(deletedGame)
    }

    @Test
    fun `test get all games`() {
        val pcGame = PcGameData("Test PC Game", 2022, 1, 1, "High", "Yes")
        val mobileGame = MobileGameData("Test Mobile Game", 2022, 1, 1, "Android")
        val consoleGame = ConsoleGameData("Test Console Game", 2022, 1, 1, "PlayStation")

        dao.addGame(pcGame)
        dao.addGame(mobileGame)
        dao.addGame(consoleGame)

        val allGames = dao.getAllGames()
        assertEquals(3, allGames.size)
        assertTrue(allGames.any { it.title == "Test PC Game" })
        assertTrue(allGames.any { it.title == "Test Mobile Game" })
        assertTrue(allGames.any { it.title == "Test Console Game" })
    }
}
