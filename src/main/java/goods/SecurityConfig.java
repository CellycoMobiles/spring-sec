package goods;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                    .withUser("admin").password("password").roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/resources/**").permitAll()
                        //.antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/login2").permitAll()
                        .anyRequest().authenticated()
                        .and()
                .formLogin()
                        .loginPage("/login2")
                        .defaultSuccessUrl("/welcome");
    }
}
