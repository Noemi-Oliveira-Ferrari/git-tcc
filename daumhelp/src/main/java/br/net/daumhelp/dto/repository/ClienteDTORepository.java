package br.net.daumhelp.dto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.dto.ClienteDTO;

public interface ClienteDTORepository extends JpaRepository<ClienteDTO, Long> {

	
	//BUSCAR CLIENTE PELO CPF
	@Query("SELECT c FROM ClienteDTO c WHERE c.cpf = ?1")
	public ClienteDTO findByCpf(String cpf);

	//BUSCA CLIENTE PELO EMAIL
	@Query("SELECT c FROM ClienteDTO c WHERE c.email = ?1")
	public ClienteDTO findByEmail(String email);

	//BUSCA CLIENTES PELO ID DO ENDERECO
	@Query("SELECT c FROM ClienteDTO c WHERE c.endereco.idEndereco = ?1")
	public List<ClienteDTO> findByIdEndereco(Long idEndereco);
	
	//BUSCA CLIENTES PELO CEP
	@Query("SELECT c FROM ClienteDTO c WHERE c.endereco.cep = ?1")
	public List<ClienteDTO> findByCep(String cep);
	
	//BUSCA CLIENTES PELO ID DA CIDADE
	@Query("SELECT c FROM ClienteDTO c WHERE c.endereco.cidade.idCidade = ?1")
	public List<ClienteDTO> findByCidade(Long idCidade);
	
	//BUSCA CLIENTES PELO ID DA MICRORREGIÃO
	@Query("SELECT c FROM ClienteDTO c WHERE c.endereco.cidade.microrregiao.idMicro = ?1")
	public List<ClienteDTO> findByMicrorregiao(Long idMicro);

	//BUSCA CLIENTES PELA UF
	@Query("SELECT c FROM ClienteDTO c WHERE c.endereco.cidade.microrregiao.uf.uf = ?1")
	public List<ClienteDTO> findByUf(String uf);

	//BUSCA CLIENTES PELO ID DA UF
	@Query("SELECT c FROM ClienteDTO c WHERE c.endereco.cidade.microrregiao.uf.idUf = ?1")
	public List<ClienteDTO> findByIdUf(Long idUf);
	
}



