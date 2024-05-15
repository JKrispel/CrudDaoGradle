import kotlinx.serialization.Serializable

@Serializable
data class PcGame(


    override var title: String,
    override var year: Int,
    override var month: Int,
    override var day: Int,
    private var requirements: String,
    private var modding: String,
) : Game {


    override fun toString(): String =
            "(PC) " +
            "${title}, " +
            "release date: $day." +
            "$month." +
            "$year, " +
            "modding: ${modding}, " +
            "requirements: ${requirements}"


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
                val data = inputPcDescriptionInterface(title)
                modding = data[0]
                requirements = data[1]
            }
        }
    }
}