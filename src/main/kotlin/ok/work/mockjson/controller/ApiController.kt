package ok.work.mockjson.controller

import ok.work.mockjson.service.UserService
import ok.work.mockjson.service.getValuesFromUrl
import ok.work.mockjson.service.matchesTemplateUrl
import ok.work.mockjson.service.putValuesInPayload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handleGET(@PathVariable("userId") userId: String, request: HttpServletRequest): ResponseEntity<String>? {
        return handleApi(userId, request)
    }

    @PostMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handlePOST(@PathVariable("userId") userId: String, @RequestBody body: String?, request: HttpServletRequest): ResponseEntity<String>? {
        return handleApi(userId, request)
    }

    @PutMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handlePUT(@PathVariable("userId") userId: String, @RequestBody body: String?, request: HttpServletRequest): ResponseEntity<String>? {
        return handleApi(userId, request)
    }

    @DeleteMapping("/{userId}/**", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun handleDELETE(@PathVariable("userId") userId: String, @RequestBody body: String?, request: HttpServletRequest): ResponseEntity<String>? {
        return handleApi(userId, request)
    }

    private fun handleApi(userId: String, request: HttpServletRequest): ResponseEntity<String>? {
        val user = userService.getUser(userId)

        val path = request.requestURI.toString().replace("${request.contextPath}/api/$userId", "")

        val api = user.apis!!.find { api -> matchesTemplateUrl(api.endpoint, path) }

        var payload = api
                ?.responseBody
                ?: throw RuntimeException("No API defined for $path")
        payload = putValuesInPayload(payload, request, getValuesFromUrl(api.endpoint, path))

        val map = LinkedMultiValueMap<String, String>()
        api.responseHeaders?.forEach { h -> map[h.key] = h.value }

        return ResponseEntity(payload, map, HttpStatus.valueOf(api.status.toInt()))
    }

}
