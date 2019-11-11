package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.UF;

public interface UFRepository extends JpaRepository<UF, Long>{
	
	//BUSCA A UF PELA SIGLA (UF)
	@Query("SELECT u FROM UF u WHERE u.uf = ?1")
	public UF findByUf(String uf);
//	
//	@Query("SELECT u FROM UF u WHERE u.idUf = ?1")
//	public UF findById(Long id);
}
