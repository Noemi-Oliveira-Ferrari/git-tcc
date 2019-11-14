package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.net.daumhelp.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	
	//FILTROS DE DATA DA REALIZAÇÃO DO PEDIDO---------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.dataHora >= ?1")
	public List<Pedido> pedidosAPartirDe(String dataHoraInicial);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataHora >= ?1 AND p.dataHora <= ?2")
	public List<Pedido> pedidosEntre(String dataHoraInicial, String dataHoraFinal);

	@Query("SELECT p FROM Pedido p WHERE p.dataHora <= ?1")
	public List<Pedido> pedidosAntesDe(String dataHoraFinal);
	//FILTROS DE DATA DA REALIZAÇÃO DO PEDIDO---------------------------------

//******************************************************************************************************************************************************************
		
	//FILTROS DE DATA DA EXECUÇÃO DO SERVIÇO----------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.dataServico >= ?1")
	public List<Pedido> execucaoAPartirDe(String dataServicoInicial);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico >= ?1 AND p.dataServico <= ?2")
	public List<Pedido> execucaoEntre(String dataServicoInicial, String dataServicoFinal);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico <= ?1")
	public List<Pedido> execucaoAntesDe(String dataServicoFinal);
	//FILTROS DE DATA DA EXECUÇÃO DO SERVIÇO----------------------------------

//******************************************************************************************************************************************************************
	
	//FILTROS DE DATA EM QUE O SERVIÇO FOI FINALIZADO----------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.dataServico >= ?1")
	public List<Pedido> finalizadoAPartirDe(String finalizado);
	
	@Query("SELECT p FROM Pedido p WHERE p.finalizado >= ?1 AND p.finalizado <= ?2")
	public List<Pedido> finalizadoEntre(String finalizadoDe, String finalizadoAte);
	
	@Query("SELECT p FROM Pedido p WHERE p.finalizado <= ?1")
	public List<Pedido> finalizadoAntesDe(String finalizado);
	//FILTROS DE DATA EM QUE O SERVIÇO FOI FINALIZADO----------------------------------
	
//******************************************************************************************************************************************************************
	
	//FILTROS DE HORARIO INICIAL DO SERVICO ----------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.dataServico = ?1 AND p.horarioInicial >= ?2")
	public List<Pedido> horarioInicialAPartirDe(String dataServico, String horarioInicial);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico = ?1 AND (p.horarioInicial >= ?2 AND p.horarioInicial <= ?3)")
	public List<Pedido> horarioInicialEntre(String dataServico, String horarioInicialDe, String horarioInicialAte);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico = ?1 AND p.horarioInicial <= ?2")
	public List<Pedido> horarioInicialAntesDe(String dataServico, String horarioInicial);
	//FILTROS DE HORARIO INICIAL DO SERVICO ----------------------------------

//******************************************************************************************************************************************************************
	
	//FILTROS DE HORARIO FINAL DO SERVICO ----------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.dataServico = ?1 AND p.horarioFinal >= ?2")
	public List<Pedido> horarioFinalAPartirDe(String dataServico, String horarioFinal);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico = ?1 AND (p.horarioFinal >= ?2 AND p.horarioFinal <= ?3)")
	public List<Pedido> horarioFinalEntre(String dataServico, String horarioFinalDe, String horarioFinalAte);
	
	@Query("SELECT p FROM Pedido p WHERE p.dataServico = ?1 AND p.horarioFinal <= ?2")
	public List<Pedido> horarioFinalAntesDe(String dataServico, String horarioFinal);
	//FILTROS DE HORARIO FINAL DO SERVICO ----------------------------------
	
//******************************************************************************************************************************************************************
	
	//FILTROS DE HORAS DE SERVIÇO ----------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.horasServico >= ?1")
	public List<Pedido> horasServicoMaiorQue(String horasServico);
	
	@Query("SELECT p FROM Pedido p WHERE p.horasServico >= ?1 AND p.horasServico <= ?2")
	public List<Pedido> horasServicoEntre(String horasServicoDe, String horasServicoAte);
	
	@Query("SELECT p FROM Pedido p WHERE p.horasServico <= ?1")
	public List<Pedido> horasServicoMenorQue(String horasServico);
	//FILTROS DE HORAS DE SERVIÇO ----------------------------------
	
//******************************************************************************************************************************************************************
	
	//FILTROS DE VALOR DO SERVIÇO ----------------------------------
	@Query("SELECT p FROM Pedido p WHERE p.valorServico >= ?1")
	public List<Pedido> valorServicoMaiorQue(String valorServico);
	
	@Query("SELECT p FROM Pedido p WHERE p.valorServico >= ?1 AND p.valorServico <= ?2")
	public List<Pedido> valorServicoEntre(String valorServicoDe, String valorServicoAte);

	@Query("SELECT p FROM Pedido p WHERE p.valorServico <= ?1")
	public List<Pedido> valorServicoMenorQue(String valorServico);
	//FILTROS DE VALOR DO SERVIÇO ----------------------------------

//******************************************************************************************************************************************************************
		
	@Query("SELECT p FROM Pedido p WHERE p.status.idStatusPedido = ?1")
	public List<Pedido> buscarPorStatus(Long idStatusPedido);
	
	@Query("SELECT p FROM Pedido p WHERE p.descricao LIKE '%?1%'")
	public List<Pedido> buscarPorDescricao(String descricao);
	
	@Query("SELECT p FROM Pedido p WHERE p.cliente.idCliente = ?1")
	public List<Pedido> buscarPorCliente(Long idCliente);
	
	@Query("SELECT p FROM Pedido p WHERE p.profissional.idProfissional = ?1")
	public List<Pedido> buscarPorPro(Long idProfissional);
	
	@Query("SELECT p FROM Pedido p WHERE p.cliente.idCliente = ?1 AND p.status.idStatusPedido = ?2")
	public List<Pedido> buscarPorClienteStatus(Long idCliente, Long idStatusPedido);
	
	@Query("SELECT p FROM Pedido p WHERE p.profissional.idProfissional = ?1 AND p.status.idStatusPedido = ?2")
	public List<Pedido> buscarPorProfissionalStatus(Long idProfissional, Long idStatusPedido);
	
	
	
}
