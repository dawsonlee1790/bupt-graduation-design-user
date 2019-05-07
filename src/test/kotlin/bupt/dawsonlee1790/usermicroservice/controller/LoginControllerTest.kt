package bupt.dawsonlee1790.usermicroservice.controller

import bupt.dawsonlee1790.usermicroservice.UserMicroserviceApplication
import org.intellij.lang.annotations.Language
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [UserMicroserviceApplication::class])
class LoginControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    val baseUrl = "/UserController"

    @Before
    fun init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    private fun post(url: String, content: String): MvcResult {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()
    }

    private fun put(url: String, content: String): MvcResult {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .put(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()
    }


    @Test
    fun loginSuccessful() {
        val url = "$baseUrl/login"
        val contextJson = "{\n  \"name\": \"Dawson\",\n  \"passwordToken\": \"123456789\"\n}"
        val result = post(url, contextJson)
        Assert.assertEquals(200, result.response.status)
    }

    @Test
    fun loginErrorByName() {
        val url = "$baseUrl/login"
        val contextJson = "{\n  \"name\": \"===Dawson\",\n  \"passwordToken\": \"123456\"\n}"
        val result = post(url, contextJson)
        Assert.assertEquals(400, result.response.status)
    }

    @Test
    fun loginErrorByPassword() {
        val url = "$baseUrl/login"
        val contextJson = "{\n  \"name\": \"Dawson\",\n  \"passwordToken\": \"18\"\n}"
        val result = post(url, contextJson)
        Assert.assertNotEquals(200, result.response.status)
    }

    /**
     * 其实因为dev环境每次都会重新创建数据库所以在dev环境测试是不需要Transactional回滚的
     * 但product环境进行是需要Transactional回滚的，所以添加了
     */
    @Test
    @Transactional
    fun registerSuccessful() {
        val url = "$baseUrl/register"
        @Language("JSON") val contextJson = "{\n  \"name\": \"Uzl\",\n  \"password\": \"123456789\",\n  \"roleList\": [\n    \"Planner\"\n  ]\n}"
        val result = put(url, contextJson)
        Assert.assertEquals(200, result.response.status)
    }

}