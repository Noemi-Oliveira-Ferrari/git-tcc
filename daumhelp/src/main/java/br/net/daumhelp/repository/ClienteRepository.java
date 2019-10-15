package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.daumhelp.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
