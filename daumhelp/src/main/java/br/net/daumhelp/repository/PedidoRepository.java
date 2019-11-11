package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.net.daumhelp.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	
	//FILTROS DE DATA DA REALIZAÇÃO DO PEDIDO
	@Query("SELECT p FROM Pedido p WHERE p.dataHora > ?1")
	public List<Pedido> pedidosAPartirDe(String dataHoraInicial);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataHora > ?1 AND p.dataHora < ?2")
	public List<Pedido> pedidosEntre(String dataHoraInicial, String dataHoraFinal);

	@Query("SELECT p FROM Pedido p WHERE p.dataHora < ?1")
	public List<Pedido> pedidosAntesDe(String dataHoraFinal);

	//FILTROS DE DATA DA EXECUÇÃO DO SERVIÇO
	@Query("SELECT p FROM Pedido p WHERE p.dataServico > ?1")
	public List<Pedido> execucaoAPartirDe(String dataServicoInicial);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico > ?1 AND p.dataServico < ?2")
	public List<Pedido> execucaoEntre(String dataServicoInicial, String dataServicoFinal);

	@Query("SELECT p FROM Pedido p WHERE p.dataServico < ?1")
	public List<Pedido> execucaoAntesDe(String dataServicoFinal);
	
	
}
