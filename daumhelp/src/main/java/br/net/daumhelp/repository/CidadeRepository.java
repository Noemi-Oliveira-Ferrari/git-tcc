package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	@Query("SELECT c FROM Cidade c WHERE c.microrregiao.idMicro = ?1")
	public List<Cidade> findByIdMicro(Long idMicro);

	@Query("SELECT c FROM Cidade c WHERE c.microrregiao.uf.uf = ?1")
	public List<Cidade> findByUf(String uf);
	
	@Query("SELECT c FROM Cidade c WHERE c.microrregiao.uf.idUf = ?1")
	public List<Cidade> findByIdUf(Long uf);
	
	@Query("SELECT c FROM Cidade c WHERE c.idCidade = ?1")
	public Cidade findByOtherId(Long idCidade);
}
