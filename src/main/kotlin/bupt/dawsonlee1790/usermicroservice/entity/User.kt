package bupt.dawsonlee1790.usermicroservice.entity

import bupt.dawsonlee1790.usermicroservice.util.SHA256
import javax.persistence.*


@Entity
@Table(name = "users")
class User private constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String,
        var password: String,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(name = "user_group",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "group_id", referencedColumnName = "id")])
        var groupList: MutableList<Group>
) {

    companion object {
        fun create(name: String, passwordToken: String, groupList: List<Group> = ArrayList()): User {
            val encryptedToken = SHA256().getSHA256StrJava(passwordToken)
            return User(
                    name = name,
                    password = encryptedToken,
                    groupList = groupList.toMutableList()
            )
        }
    }

    fun verifyPassword(passwordToken: String): Boolean {
        val encryptedToken = SHA256().getSHA256StrJava(passwordToken)
        return encryptedToken == this.password
    }

    fun createUser(name: String, passwordToken: String, groupList: MutableList<Group>): User {
//        if (groupList.none { it.name == "manager" })
//            throw Exception("只有管理员有权限创建新用户")
        val encryptedPassword = SHA256().getSHA256StrJava(passwordToken)
        return User(null, name, encryptedPassword, groupList)
    }

}