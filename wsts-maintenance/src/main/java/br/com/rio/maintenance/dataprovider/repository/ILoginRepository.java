package br.com.rio.maintenance.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rio.maintenance.dataprovider.repository.entity.LoginEntity;

@Repository
public interface ILoginRepository extends JpaRepository<LoginEntity, Long> {
	public LoginEntity findByUser(String user);
	
	public LoginEntity findByUser(LoginEntity entity);
}