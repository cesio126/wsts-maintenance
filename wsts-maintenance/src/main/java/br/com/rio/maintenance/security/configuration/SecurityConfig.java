package br.com.rio.maintenance.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.rio.maintenance.usecase.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .jdbcAuthentication()
//            .authoritiesByUsernameQuery("select user, authority from login l inner join authority a on l.authority_id = a.id where USERNAME=?")
//            .usersByUsernameQuery("select user, pass, 1 as enabled from login where USERNAME=?");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	http
//	        //HTTP Basic authentication
//	        .httpBasic()
//	        .and()
//	        .userDetailsService(userDetailsService())
//	        .authorizeRequests()
//	        .antMatchers(HttpMethod.GET, "/vehicle/**").hasRole("USER")
//	        .antMatchers(HttpMethod.POST, "/vehicle").hasRole("ADMIN")
//	        .antMatchers(HttpMethod.PUT, "/vehicle/**").hasRole("ADMIN")
//	        .antMatchers(HttpMethod.PATCH, "/vehicle/**").hasRole("ADMIN")
//	        .antMatchers(HttpMethod.DELETE, "/vehicle/**").hasRole("ADMIN")
//	        .and()
//	        .csrf().disable()
//	        .formLogin().disable();
//    }
//    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //ok for demo
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("user").roles("USER").build());
//        manager.createUser(users.username("admin").password("admin").roles("USER", "ADMIN").build());
//        return manager;
//    }

	@Bean
	public UserDetailsService userDetailsService() {
		return new LoginService();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

    	http
	        //HTTP Basic authentication
	        .httpBasic()
	        .and()
	        .userDetailsService(userDetailsService())
	        .authorizeRequests()
	        .antMatchers(HttpMethod.GET, "/vehicle/**").hasRole("USER")
	        .antMatchers(HttpMethod.POST, "/vehicle").hasRole("ADMIN")
	        .antMatchers(HttpMethod.PUT, "/vehicle/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.PATCH, "/vehicle/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.DELETE, "/vehicle/**").hasRole("ADMIN")
	        .and()
	        .csrf().disable()
	        .formLogin().disable();
	}
}