package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.net.daumhelp.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	@Query("SELECT p FROM Profissional p WHERE p.email = ?1 AND p.senha = ?2")
	public Profissional findUserLogin(String email, String senha);
	
}