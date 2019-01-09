package lt.classicgames.api.security

import lt.classicgames.api.security.SecurityConstants.LOCATIONS_URL
import lt.classicgames.api.security.SecurityConstants.SIGN_UP_URL
import lt.classicgames.api.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
class WebSecurity(val userService: UserService, val bCryptPasswordEncoder: BCryptPasswordEncoder): WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, LOCATIONS_URL).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    public override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}