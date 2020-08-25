package br.com.rio.maintenance.usecase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rio.maintenance.dataprovider.repository.ILoginRepository;
import br.com.rio.maintenance.dataprovider.repository.entity.LoginEntity;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private ILoginRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginEntity login = repository.findByUser(username);

		UserBuilder builder = null;
		if (login != null) {
			builder = User.withUsername(login.getUser());
			builder.password(new BCryptPasswordEncoder().encode(login.getPass()));
			builder.roles(login.getRole());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}
}