package br.net.daumhelp.dto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.dto.ProfissionalDTO;

public interface ProfissionalDTORepository extends JpaRepository<ProfissionalDTO, Long> {
	
	//BUSCA PROFISSIONAL PELO ID DA CATEGORIA
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.subcategoria.categoria.idCategoria = ?1")
	public List<ProfissionalDTO> findByCategoria(Long idCategoria);
	
	//BUSCA PROFISSIONAL PELO ID DA SUBCATEGORIA
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.subcategoria.idSubcategoria = ?1")
	public List<ProfissionalDTO> findBySubcategoria(Long idSubcategoria);

	//BUSCA PROFISSIONAL PELO CPF
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.cpf = ?1")
	public ProfissionalDTO findByCpf(String cpf);

	//BUSCA PROFISSIONAL PELO EMAIL
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.email = ?1")
	public ProfissionalDTO findByEmail(String email);

	//BUSCA PROFISSIONAL PELO CNPJ
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.cnpj = ?1")
	public ProfissionalDTO findByCnpj(String cnpj);

	//BUSCA PROFISSIONAL PELO ID DO ENDERECO
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.endereco.idEndereco = ?1")
	public List<ProfissionalDTO> findByIdEndereco(Long idEndereco);

	//BUSCA PROFISSIONAL PELO CEP
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.endereco.cep = ?1")
	public List<ProfissionalDTO> findByCep(String cep);

	//BUSCA PROFISSIONAL PELO ID DA CIDADE
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.endereco.cidade.idCidade = ?1")
	public List<ProfissionalDTO> findByCidade(Long idCidade);

	//BUSCA PROFISSIONAL ID DA MICRORREGIÃO
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.endereco.cidade.microrregiao.idMicro = ?1")
	public List<ProfissionalDTO> findByMicrorregiao(Long idMicro);

	//BUSCA PROFISSIONAL PELA UF
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.endereco.cidade.microrregiao.uf.uf = ?1")
	public List<ProfissionalDTO> findByUf(String uf);

	//BUSCA PROFISSIONAL PELO ID DA UF
	@Query("SELECT p FROM ProfissionalDTO p WHERE p.endereco.cidade.microrregiao.uf.idUf = ?1")
	public List<ProfissionalDTO> findByIdUf(Long idUf);

}
