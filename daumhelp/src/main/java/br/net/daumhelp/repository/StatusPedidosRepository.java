package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.daumhelp.model.StatusPedido;

@Repository
public interface StatusPedidosRepository extends JpaRepository<StatusPedido, Long>{

}
