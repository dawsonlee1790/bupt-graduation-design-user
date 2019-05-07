package bupt.dawsonlee1790.usermicroservice.entity

import bupt.dawsonlee1790.usermicroservice.enums.Role
import javax.persistence.*

@Entity
@Table(name = "groups")
class Group private constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String,
        var description: String,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(name = "user_group",
                joinColumns = [JoinColumn(name = "group_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
        var userList: MutableList<User>
) {
    companion object {
        fun create(role: Role, userList: List<User> = ArrayList()): Group {
            return Group(
                    name = role.name,
                    description = role.value,
                    userList = userList.toMutableList()
            )
        }
    }
}