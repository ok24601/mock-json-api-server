package ok.work.mockjson.controller

import ok.work.mockjson.service.Api
import ok.work.mockjson.service.User
import ok.work.mockjson.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/createUser")
    fun createUser(user: User) {
        userService.addUser(user)
    }

    @PostMapping("/createApi")
    fun addApi(@RequestBody api: Api, @RequestHeader userId: String) {
        userService.addApiToUser(api, userId)
    }
}
