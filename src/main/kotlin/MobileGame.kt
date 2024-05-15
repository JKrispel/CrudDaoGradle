import kotlinx.serialization.Serializable

@Serializable
data class MobileGame(


    override var title: String,
    override var year: Int,
    override var month: Int,
    override var day: Int,
    private var system: String,
) : Game {


    override fun toString(): String =
            "(Mobile) " +
            "${title}, " +
            "release date: $day." +
            "$month." +
            "$year, " +
            "system: ${system}"


    override fun edit() {


        when (whatToEditInterface()) {


            "1" -> {
                title = inputTitleInterface(title)
            }

            "2" -> {
                val data = inputDateInterface(title)
                year = data[0]
                month = data[1]
                day = data[2]
            }

            "3" -> {
                system = inputMobileDescriptionInterface(title)
            }
        }
    }
}