package bupt.dawsonlee1790.usermicroservice.repository

import bupt.dawsonlee1790.usermicroservice.entity.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface GroupRepository : JpaRepository<Group, Long> {

    fun findByName(name: String): Group

}