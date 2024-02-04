import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch (
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String, // "MMMM DD, YYYY" format, for example, OCTOBER 5, 2022.
    @SerialName("success")
    val launchSuccess: Boolean?,
)