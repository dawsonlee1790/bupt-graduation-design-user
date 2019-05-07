package bupt.dawsonlee1790.usermicroservice

import bupt.dawsonlee1790.usermicroservice.entity.Group
import bupt.dawsonlee1790.usermicroservice.entity.User
import bupt.dawsonlee1790.usermicroservice.repository.GroupRepository
import bupt.dawsonlee1790.usermicroservice.repository.UserRepository
import bupt.dawsonlee1790.usermicroservice.util.SHA256
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class UserMicroserviceApplication {

//    @Bean
//    fun init(userRepository: UserRepository, groupRepository: GroupRepository) = CommandLineRunner {
//        val passwordToken = SHA256().getSHA256StrJava("123456789")
//        val user1 = User(
//                name = "Dawson",
//                password = passwordToken
//        )
//        val user2 = User(
//                name = "Mary",
//                password = passwordToken
//        )
//        val user3 = User(
//                name = "Jack",
//                password = passwordToken
//        )
//        val user4 = User(
//                name = "Lina",
//                password = passwordToken
//        )
//        val user5 = User(
//                name = "Doinb",
//                password = passwordToken
//        )
//        val group1 = Group(
//                name = "计划人",
//                description = "制定生产批次计划"
//        )
//        val group2 = Group(
//                name = "研究员",
//                description = "制定SOP流程"
//        )
//        val group3 = Group(
//                name = "叉车工",
//                description = "执行生产指令"
//        )
//        val group4 = Group(
//                name = "车间管理员",
//                description = "执行生产指令"
//        )
//        val group5 = Group(
//                name = "生产负责人",
//                description = "对一个批次的生产负责"
//        )
//        group1.userList.add(user1)
//        group2.userList.add(user2)
//        group3.userList.add(user3)
//        group4.userList.add(user4)
//        group5.userList.add(user5)
//        groupRepository.save(group1)
//        groupRepository.save(group2)
//        groupRepository.save(group3)
//        groupRepository.save(group4)
//        groupRepository.save(group5)
//    }


}


fun main(args: Array<String>) {
    runApplication<UserMicroserviceApplication>(*args)
}



