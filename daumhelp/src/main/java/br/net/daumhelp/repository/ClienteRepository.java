package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{


	@Query("SELECT c FROM Cliente c WHERE c.email = ?1 AND c.senha = ?2")
	public Cliente findUserLogin(String email, String senha);
	
}
