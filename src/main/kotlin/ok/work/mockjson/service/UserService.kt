package ok.work.mockjson.service

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.PostConstruct

data class User(val clientId: String, val apis: MutableList<Api>?)

data class Api(val name: String, val endpoint: String,
               val method: HttpMethod, val responseBody: String?,
               val responseHeaders: List<HttpHeaders>?, val templates: String?,
               val date: LocalDateTime?)

@Service
class UserService {

    val userRepository: MutableMap<String, User> = mutableMapOf()

    @PostConstruct
    fun init() {
        val list = mutableListOf<Api>()
        list.add(Api("oauth", "/oauth/device/code",
                HttpMethod.GET,
                """{"device_code":"KAkfsU-nSrziHPzg6RvprTSe","user_code":"PJLN-PSSB","verification_uri":"https://dev-docv8y53.eu.auth0.com/activate","expires_in":900,"interval":5,"verification_uri_complete":"https://dev-docv8y53.eu.auth0.com/activate?user_code=PJLN-PSSB"}""",
                null, null, LocalDateTime.now()))
        userRepository["okTestDemo"] = User("okTestDemo", list)
    }

    fun addUser(u: User) {
        userRepository[u.clientId] = u
    }

    fun getUser(id: String): User {
        return userRepository[id] ?: throw RuntimeException("User $id not found.")
    }

    fun addApiToUser(api: Api, userId: String) {
        val apis = userRepository[userId]!!.apis;
        if (apis?.filter { a -> matchesTemplateUrl(a.endpoint, api.endpoint) }.isNullOrEmpty()) {
           apis?.add(api)
        }
        return
    }

}
