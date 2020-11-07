package valorank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userRepositoryUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/interiorrank")
                .hasRole("USER")
                .and()
            .authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()
            .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/interiorrank")
            .and()
                .logout().logoutSuccessUrl("/")
            ;

       /* The lines 45-52 were added to try make the changes that you had suggested making and to make sure that
        it was affecting my database the way I inteded it to be working *//*
        //anything after this will have to be deleted
                .and()
                .authorizeRequests()
                .antMatchers("/interiorrank")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/showrank")
                .permitAll();
        //stop here*/
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
