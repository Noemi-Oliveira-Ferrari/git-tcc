package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	@Query("SELECT p FROM Profissional p WHERE p.email = ?1 AND p.senha = ?2")
	public Profissional findUserLogin(String email, String senha);
	
}