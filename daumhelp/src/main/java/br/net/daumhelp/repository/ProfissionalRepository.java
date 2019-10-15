package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.daumhelp.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
}