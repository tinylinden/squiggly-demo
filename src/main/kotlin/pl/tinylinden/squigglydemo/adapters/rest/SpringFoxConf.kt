package pl.tinylinden.squigglydemo.adapters.rest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringFoxConf {

    @Bean
    @Primary
    fun swaggerResourceProvider(): SwaggerResourcesProvider =
        SwaggerResourcesProvider {
            listOf(
                swaggerResource("Squiggly Demo API (v1)", "3.0.0", "/public/specs/squiggly-demo-api.v1.yaml")
            )
        }

    private fun swaggerResource(name: String, swaggerVersion: String, location: String): SwaggerResource =
        SwaggerResource()
            .also {
                it.name = name
                it.swaggerVersion = swaggerVersion
                it.location = location
            }
}