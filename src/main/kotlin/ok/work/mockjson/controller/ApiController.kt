package ok.work.mockjson.controller

import ok.work.mockjson.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handleGET(@PathVariable("userId") userId: String, request: HttpServletRequest): String {
        val user = userService.getUser(userId)

        val path = request.requestURI.toString().replace("${request.contextPath}/api/$userId", "")

        return user.apis!!.find { api -> api.endpoint.equals(path) }?.body ?: ""
    }

    @PostMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handlePOST(@PathVariable("userId") userId: String, body: String): String? {
        val user = userService.getUser(userId)
        return null;
    }

    fun handlePUT() {

    }

    fun handleDELETE() {

    }

}
