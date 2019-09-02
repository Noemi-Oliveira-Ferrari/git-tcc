package br.net.daumhelp.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.repository.ProfissionalRepository;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/profissionais")
public class ProfissionalResource {
	
	@Autowired
	private ProfissionalRepository proRepository;
	
	@GetMapping
	public List<Profissional> getPros(){
		return proRepository.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Profissional> getProById(@PathVariable Long id) {
		return proRepository.findById(id);
	}

	@GetMapping("/categoria/{idCategoria}")
	public List<Profissional> getProByCategoria(@PathVariable Long idCategoria) {
		return proRepository.findByCategoria(idCategoria);
	}

	@GetMapping("/subcategoria/{idSubategoria}")
	public List<Profissional> getProBySubcategoria(@PathVariable Long idSubategoria) {
		return proRepository.findBySubcategoria(idSubategoria);
	}

	@GetMapping("/cpf/{cpf}")
	public Profissional getProByCpf(@PathVariable String cpf) {
		return proRepository.findByCpf(cpf);
	}

	@GetMapping("/cnpj/{cnpj}")
	public Profissional getProByCnpj(@PathVariable String cnpj) {
		return proRepository.findByCnpj(cnpj);
	}

	@GetMapping("/endereco/{idEndereco}")
	public Profissional getProByIdEndereco(@PathVariable Long idEndereco) {
		return proRepository.findByIdEndereco(idEndereco);
	}

	@GetMapping("/cep/{cep}")
	public Profissional getProByIdEndereco(@PathVariable String cep) {
		return proRepository.findByCpf(cep);
	}

	@GetMapping("/cidade/{cidade}")
	public List<Profissional> getProByCidade(@PathVariable Long idCidade) {
		return proRepository.findByCidade(idCidade);
	}

	@GetMapping("/microrregiao/{microrregiao}")
	public List<Profissional> getProByMicro(@PathVariable Long idMicro) {
		return proRepository.findByMicrorregiao(idMicro);
	}

	@GetMapping("/uf/{uf}")
	public List<Profissional> getProByUf(@PathVariable String uf) {
		return proRepository.findByUf(uf);
	}

	@GetMapping("/idUf/{idUf}")
	public List<Profissional> getProByIdUf(@PathVariable Long idUf) {
		return proRepository.findByIdUf(idUf);
	}
	
	
}
