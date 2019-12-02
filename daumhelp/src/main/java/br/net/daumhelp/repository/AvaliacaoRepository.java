package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

	@Query("SELECT a FROM Avaliacao a WHERE a.profissional.idProfissional = ?1")
	public List<Avaliacao> avaliacaoPorPro(Long idProfissional);

	@Query("SELECT a FROM Avaliacao a WHERE a.cliente.idCliente = ?1")
	public List<Avaliacao> avaliacaoPorCliente(Long idCliente);
}
