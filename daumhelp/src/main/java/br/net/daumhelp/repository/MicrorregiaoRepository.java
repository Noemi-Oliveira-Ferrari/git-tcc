package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Microrregiao;

public interface MicrorregiaoRepository extends JpaRepository<Microrregiao, Long>{
	
	//BUSCA MICRORREGIÃO PELO ID DA UF
	@Query("SELECT m FROM Microrregiao m WHERE m.uf.idUf = ?1") 
	public List<Microrregiao> findByIdUf(Long idUf);

	//BUSCA MICRORREGIÃO PELA UF
	@Query("SELECT m FROM Microrregiao m WHERE m.uf.uf = ?1") 
	public List<Microrregiao> findByUf(String uf);
}
