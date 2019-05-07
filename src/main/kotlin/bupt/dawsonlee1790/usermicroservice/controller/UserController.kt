package bupt.dawsonlee1790.usermicroservice.controller

import bupt.dawsonlee1790.usermicroservice.dto.LoginDTO
import bupt.dawsonlee1790.usermicroservice.repository.UserRepository
import bupt.dawsonlee1790.usermicroservice.util.KeyGenerator
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/UserController")
@Api(tags = ["用例：用户登陆"])
class UserController {

    @Autowired
    private lateinit var keyGenerator: KeyGenerator

    @Autowired
    private lateinit var request: HttpServletRequest

    @Autowired
    private lateinit var userRepository: UserRepository


    @PostMapping("/login")
    @ApiOperation("登陆")
    @ApiResponses(
            ApiResponse(code = 200, message = "登陆成功body和header（“AUTHORIZATION”）返回JWT的令牌"),
            ApiResponse(code = 400, message = "账号或密码不正确"))
    fun login(@RequestBody @Validated loginDTO: LoginDTO, response: HttpServletResponse): String {
        println("Hello ${loginDTO.name}!")
        val user = userRepository.findByName(loginDTO.name)
        if (user == null || !user.verifyPassword(loginDTO.passwordToken)) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return "账号或密码不正确"
        }
//            System.out.println("Authenticated user: " + sctx.getUserPrincipal().getName());
//            String authenticatedUser = sctx.getUserPrincipal().getName();
//编辑JWT(Json web token)的payload数据
//        {
//          "sub": "2015212101",
//          "iss": "http://localhost:8080/RESTful-User-Authorization/api/user/login",
//          "iat": 1531308470089,
//          "exp": 1531310270089,
//          "role": [
//              "生产责任人",
        "叉车工"
//          ]
//        }
        val date = Date()
        val key = keyGenerator.generateKey()
        val jwtToken = Jwts.builder()
                .setSubject(user.id.toString())
                .setIssuer(request.requestURL.toString())
                .setIssuedAt(date)
                .setExpiration(Date(date.time + 30 * 60 * 1000))
                .claim("Role", user.groupList.map { it.name })
                .signWith(SignatureAlgorithm.HS512, key)
                .compact()
        println("#### generating token for a key :$jwtToken---$key")
        response.setHeader(AUTHORIZATION, "$jwtToken")
        return "$jwtToken"
    }


}