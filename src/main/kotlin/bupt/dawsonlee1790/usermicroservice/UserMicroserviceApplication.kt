package bupt.dawsonlee1790.usermicroservice

import bupt.dawsonlee1790.usermicroservice.entity.Group
import bupt.dawsonlee1790.usermicroservice.entity.User
import bupt.dawsonlee1790.usermicroservice.enums.Role
import bupt.dawsonlee1790.usermicroservice.repository.GroupRepository
import bupt.dawsonlee1790.usermicroservice.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
class UserMicroserviceApplication {

    @Bean
    @Profile("dev")
    fun init(userRepository: UserRepository, groupRepository: GroupRepository) = CommandLineRunner {
        val passwordToken = "123456789"
        val user1 = User.create("Dawson", passwordToken)
        val user2 = User.create("Mary", passwordToken)
        val user3 = User.create("Jack", passwordToken)
        val user4 = User.create("Lina", passwordToken)
        val user5 = User.create("Doinb", passwordToken)
        val user6 = User.create("admin", passwordToken)
        userRepository.save(user1)
        userRepository.save(user2)
        userRepository.save(user3)
        userRepository.save(user4)
        userRepository.save(user5)
        userRepository.save(user6)
        userRepository.flush()

        val group1 = Group.create(Role.Planner)
        val group2 = Group.create(Role.Researcher)
        val group3 = Group.create(Role.Forklift)
        val group4 = Group.create(Role.WorkshopManager)
        val group5 = Group.create(Role.ProductionLeader)
        groupRepository.save(group1)
        groupRepository.save(group2)
        groupRepository.save(group3)
        groupRepository.save(group4)
        groupRepository.save(group5)
        groupRepository.flush()

        group1.userList.add(user6)
        group2.userList.add(user6)
        group3.userList.add(user6)
        group4.userList.add(user6)
        group5.userList.add(user6)

        group1.userList.add(user1)
        group2.userList.add(user2)
        group3.userList.add(user3)
        group4.userList.add(user4)
        group5.userList.add(user5)
        groupRepository.save(group1)
        groupRepository.save(group2)
        groupRepository.save(group3)
        groupRepository.save(group4)
        groupRepository.save(group5)
    }
}


fun main(args: Array<String>) {
    runApplication<UserMicroserviceApplication>(*args)
}



