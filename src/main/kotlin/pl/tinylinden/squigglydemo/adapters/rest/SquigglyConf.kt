package pl.tinylinden.squigglydemo.adapters.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.bohnman.squiggly.Squiggly
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider
import com.github.bohnman.squiggly.web.SquigglyRequestFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SquigglyConf {

    @Bean
    fun squigglyFilter(mapper: ObjectMapper): FilterRegistrationBean<SquigglyRequestFilter> {
        Squiggly.init(mapper, RequestSquigglyContextProvider())
        return FilterRegistrationBean<SquigglyRequestFilter>()
            .also {
                it.filter = SquigglyRequestFilter()
            }
    }
}