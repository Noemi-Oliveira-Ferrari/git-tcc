package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.daumhelp.model.ClienteDTO;

public interface ClienteDTORepository extends JpaRepository<ClienteDTO, Long> {
}
