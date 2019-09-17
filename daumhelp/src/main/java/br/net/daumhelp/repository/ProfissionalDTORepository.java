package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.model.ProfissionalDTO;

public interface ProfissionalDTORepository extends JpaRepository<ProfissionalDTO, Long>{

	@Query("SELECT p FROM Profissional p WHERE p.subcategoria.categoria.idCategoria = ?1")
	public List<ProfissionalDTO> findByCategoria(Long idCategoria);
	
	@Query("SELECT p FROM Profissional p WHERE p.subcategoria.idSubcategoria = ?1")
	public List<ProfissionalDTO> findBySubcategoria(Long idSubcategoria);

	@Query("SELECT p FROM Profissional p WHERE p.cpf = ?1")
	public ProfissionalDTO findByCpf(String cpf);

	@Query("SELECT p FROM Profissional p WHERE p.cnpj = ?1")
	public ProfissionalDTO findByCnpj(String cnpj);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.idEndereco = ?1")
	public ProfissionalDTO findByIdEndereco(Long idEndereco);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.cep = ?1")
	public List<ProfissionalDTO> findByCep(String cep);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.idCidade = ?1")
	public List<ProfissionalDTO> findByCidade(Long idCidade);
	
	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.microrregiao.idMicro = ?1")
	public List<ProfissionalDTO> findByMicrorregiao(Long idMicro);

	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.microrregiao.uf.uf = ?1")
	public List<ProfissionalDTO> findByUf(String uf);

	@Query("SELECT p FROM Profissional p WHERE p.endereco.cidade.microrregiao.uf.idUf = ?1")
	public List<ProfissionalDTO> findByIdUf(Long idUf);
}
