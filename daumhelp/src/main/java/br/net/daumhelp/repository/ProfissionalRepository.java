package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
	@Query("SELECT p FROM Profissional p WHERE p.subcategoria.categoria.idCaregoria = ?1")
	public List<Profissional> findByCategoria(Long idCategoria);
	
	@Query("SELECT p FROM Profissional p WHERE p.subcategoria.idSubcategoria = ?1")
	public List<Profissional> findBySubcategoria(Long idSubcategoria);

	@Query("SELECT p FROM Profissional p WHERE p.cpf = ?1")
	public Profissional findByCpf(String cpf);

	@Query("SELECT p FROM Profissional p WHERE p.cnpj = ?1")
	public Profissional findByCnpj(String cnpj);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.idEndereco = ?")
	public Profissional findByIdEndereco(Long idEndereco);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.cep = ?1")
	public List<Profissional> findByCep(String cep);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.idCidade = ?1")
	public List<Profissional> findByCidade(Long idCidade);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.microrregiao.idMicro = ?1")
	public List<Profissional> findByMicrorregiao(Long idMicro);

	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.microrregiao.UF.uf = ?1")
	public List<Profissional> findByUf(String uf);

	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.microrregiao.UF.idUf = ?1")
	public List<Profissional> findByIdUf(Long iduf);
}