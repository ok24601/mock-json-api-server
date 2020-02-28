package ok.work.mockjson.service

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.annotation.PostConstruct

data class User(val clientId: String, val apis: MutableList<Api>?)

data class Api(val endpoint: String, val method: HttpMethod, val responseBody: String?)

@Service
class UserService {

    val userRepository: MutableMap<String, User> = mutableMapOf()

    @PostConstruct
    fun init() {
        val list = mutableListOf<Api>()
        list.add(Api("/fuel-assistant/stations", HttpMethod.GET, """{"features":[{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.379529,50.834765]},"id":"carpay-diem.71"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.352058,50.823509]},"id":"carpay-diem.70"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.312658,50.838619]},"id":"carpay-diem.72"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.316698,50.886722]},"id":"carpay-diem.73"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.324885,50.820095]},"id":"carpay-diem.69"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.403678,50.819186]},"id":"carpay-diem.68"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.434759,50.878978]},"id":"carpay-diem.45"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.314286,50.804012]},"id":"carpay-diem.66"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.427223,50.813449]},"id":"carpay-diem.67"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.327762,50.796492]},"id":"carpay-diem.65"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"LUKOIL","radius":500},"geometry":{"type":"Point","coordinates":[4.485224,50.873504]},"id":"carpay-diem.44"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"TEST","radius":500},"geometry":{"type":"Point","coordinates":[6.796133,51.235171]},"id":"carpay-diem.74"},{"type":"Feature","properties":{"stationLogoUrl":"","brandName":"TEST","radius":500},"geometry":{"type":"Point","coordinates":[11.518185,48.146619]},"id":"carpay-diem.75"}],"type":"FeatureCollection","_links":{"self":{"href":"https://dev.t6srv.valtech.io/fuel-assistant/stations?latitude=50.855805499999995&longitude=4.3594925&radius=5.0E7&limit=20"}}}"""))
        list.add(Api("/fuel-assistant/stations/44", HttpMethod.GET, """{"id":"44","brandName":"LUKOIL","stationName":"LUKOIL Zaventem DELIWAY CARREFOUR","fuelPumps":[{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":1,"id":"531"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":2,"id":"532"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":3,"id":"533"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":4,"id":"534"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":5,"id":"535"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":6,"id":"536"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":7,"id":"537"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":8,"id":"538"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":9,"id":"539"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":10,"id":"540"},{"fuelTypes":[{"name":"Diesel"},{"name":"Euro-95"},{"name":"Super-98"}],"status":"connected","number":11,"id":"541"}],"_links":{"self":{"href":"https://dev.t6srv.valtech.io/fuel-assistant/stations/44"}}}"""))
        list.add(Api("/fuel-assistant/payment-method/2", HttpMethod.GET, """{"_embedded":{"paymentMethodList":[{"type":"mastercard","identity":"4242","truncates":"start","name":"Mastercard"},{"type":"visa","identity":"1234","truncates":"start","name":"Visa"}]},"_links":{"self":{"href":"https://dev.t6srv.valtech.io/fuel-assistant/payment-method/2"}}}"""))
        list.add(Api("/oauth/device/code", HttpMethod.GET, """{"device_code":"KAkfsU-nSrziHPzg6RvprTSe","user_code":"PJLN-PSSB","verification_uri":"https://dev-docv8y53.eu.auth0.com/activate","expires_in":900,"interval":5,"verification_uri_complete":"https://dev-docv8y53.eu.auth0.com/activate?user_code=PJLN-PSSB"}"""))
        userRepository["okTestDemo"] = User("okTestDemo", list)
    }

    fun addUser(u: User) {
        userRepository[u.clientId] = u
    }

    fun getUser(id: String): User {
        return userRepository[id] ?: throw RuntimeException("User $id not found.")
    }

    fun addApiToUser(api: Api, userId: String) {
        userRepository[userId]!!.apis?.add(api)
    }

}
