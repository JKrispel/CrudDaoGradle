fun clearConsole() {

    for (i in 1..50) println()
}


fun showMenu() {

    clearConsole()

    println(
        "Wybierz co chesz zrobic wprowadzajac wybrany numer:\n" +
                "1. Wprowadz gre wideo do bazy\n" +
                "2. Wyswietl wybrana gre\n" +
                "3. Zaaktualizuj info. o grze\n" +
                "4. Usun gre z bazy danych\n" +
                "5. Wyswietl wszystkie gry w bazie\n" +
                "6. Zapisz gry do pliku\n" +
                "7. Wczytaj gry z pliku\n" +
                "8. Wyjdz"
    )
}


fun gameUpdatedInterface(game: Game) {

    println("\nWpis gry po zmianie:\n ${game}\n")
    gameLogger.info("Wpis gry po zmianie:\n ${game}")
}