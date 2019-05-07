package bupt.dawsonlee1790.usermicroservice.dto

import javax.validation.constraints.Pattern

data class RegisterDTO(
        @field:Pattern(regexp = "[a-zA-Z0-9]{1,16}", message = "username只能由数字和大小写字母组成")
        val name: String,
        @field:Pattern(regexp = "[a-zA-Z0-9]{8,16}", message = "password只能由数字和大小写字母组成")
        val password: String,
        val manager: Boolean = false,
        val teacher: Boolean = false,
        val student: Boolean = false
)