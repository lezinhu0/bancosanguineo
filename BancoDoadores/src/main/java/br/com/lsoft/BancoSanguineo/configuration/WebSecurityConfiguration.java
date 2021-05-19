package br.com.lsoft.BancoSanguineo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll()
			.antMatchers("/usuarios").hasAuthority("admin")
			.antMatchers("/usuarios/form**").hasAuthority("admin")
			.anyRequest().authenticated()
		.and()
			.formLogin().permitAll()
		.and()
			.logout().logoutSuccessUrl("/").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").authorities("admin");

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select u.username, u.password, u.enabled from usuario u where u.username = ?")
				.passwordEncoder(new BCryptPasswordEncoder()).authoritiesByUsernameQuery("select usuario_username, authorities from usuario_authorities where usuario_username = ?");
	}

}
