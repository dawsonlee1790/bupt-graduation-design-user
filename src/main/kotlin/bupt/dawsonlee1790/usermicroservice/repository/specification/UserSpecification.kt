package bupt.dawsonlee1790.usermicroservice.repository.specification

import bupt.dawsonlee1790.usermicroservice.entity.User
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class UserSpecification: Specification<User> {
    override fun toPredicate(root: Root<User>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}