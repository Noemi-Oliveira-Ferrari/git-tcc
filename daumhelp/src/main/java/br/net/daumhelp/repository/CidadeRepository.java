package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.daumhelp.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
