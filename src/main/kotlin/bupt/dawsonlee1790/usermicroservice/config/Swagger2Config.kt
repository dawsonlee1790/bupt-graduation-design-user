package bupt.dawsonlee1790.usermicroservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact


@EnableSwagger2
@Configuration
class Swagger2Config {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("bupt.dawsonlee1790.usermicroservice.controller"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("用户管理微服务")
                .description("基于微服务架构的SOP系统的设计与实现-辅助模块")
                // 作者信息
                .contact(Contact("dawsonlee1790", "https://dawsonlee1790.github.io/", "dawsonlee1790@gmail.com"))
                .version("1.0.0")
                .build()
    }


}