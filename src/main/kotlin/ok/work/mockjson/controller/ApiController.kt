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
    fun handleGET(@PathVariable("userId") userId: String, request: HttpServletRequest): String? {
        return handleApi(userId, request);
    }

    @PostMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handlePOST(@PathVariable("userId") userId: String, @RequestBody body: String, request: HttpServletRequest): String? {
        return handleApi(userId, request)
    }

    @PutMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handlePUT(@PathVariable("userId") userId: String, @RequestBody body: String, request: HttpServletRequest): String? {
        return handleApi(userId, request)
    }

    @DeleteMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handleDELETE(@PathVariable("userId") userId: String, @RequestBody body: String, request: HttpServletRequest): String? {
        return handleApi(userId, request)
    }

    private fun handleApi(userId: String, request: HttpServletRequest): String? {
        val user = userService.getUser(userId)

        val path = request.requestURI.toString().replace("${request.contextPath}/api/$userId", "")

        return user.apis!!.find { api -> api.endpoint.equals(path) }?.responseBody ?: ""
    }

}
