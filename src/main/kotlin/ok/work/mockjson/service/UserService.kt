package ok.work.mockjson.service

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotBlank

data class User(val clientId: String, val apis: MutableList<Api>?)

data class Header(val key: String, val value: String)

data class Api(@NotBlank val name: String, @NotBlank val endpoint: String, @NotBlank val status: String,
               val method: HttpMethod, val responseBody: String?,
               val responseHeaders: List<Header>?, val templates: String?,
               val date: LocalDateTime?)

@Service
class UserService {

    val userRepository: MutableMap<String, User> = mutableMapOf()

    fun addUser(u: User) {
        userRepository[u.clientId] = u
    }

    fun getUser(id: String): User {
        return userRepository[id] ?: throw RuntimeException("User $id not found.")
    }

    fun addApiToUser(api: Api, userId: String) {
        val apis = userRepository[userId]!!.apis
        if (apis?.filter { a -> matchesTemplateUrl(a.endpoint, api.endpoint) }.isNullOrEmpty()) {
            apis?.add(api)
        } else {
            throw java.lang.RuntimeException("API already exist")
        }
    }

    fun generateUser(): User {
        val user = User(UUID.randomUUID().toString(), mutableListOf())
        addUser(user)
        return user
    }

}
