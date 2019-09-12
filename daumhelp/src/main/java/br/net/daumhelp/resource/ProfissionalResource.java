package br.net.daumhelp.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.daumhelp.model.Confirmacao;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.repository.ProfissionalRepository;
import br.net.daumhelp.utils.HandleDates;
import br.net.daumhelp.utils.HandleEmails;

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
	
	@GetMapping("/confirmacao")
	public void confirmarEmail(@RequestBody Confirmacao confirm) {
		HandleEmails.enviar(confirm);
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
	public List<Profissional> getProByIdEndereco(@PathVariable String cep) {
		return proRepository.findByCep(cep);
	}

	@GetMapping("/cidade/{idCidade}")
	public List<Profissional> getProByCidade(@PathVariable Long idCidade) {
		return proRepository.findByCidade(idCidade);
	}

	@GetMapping("/microrregiao/{idMicro}")
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
	
	@PostMapping
	public ResponseEntity<Profissional> gravarPro(
			@RequestBody Profissional profissional,
			HttpServletResponse response){

		
		profissional.setCriadoEm(HandleDates.dataHoraAtual());
		profissional.setAtualizadoEm(HandleDates.dataHoraAtual());
		
		 Profissional proSalvo = proRepository.save(profissional);
		 
		 URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequestUri()
				 .buildAndExpand(profissional.getIdProfissional())
				 .toUri();
		 response.addHeader("Location", uri.toASCIIString());
		 
		 return ResponseEntity.created(uri).body(profissional);
	}
	
	@PutMapping("/id/{idPro}")
	public ResponseEntity<Profissional> atualizarPro(
			@RequestBody Profissional profissional,
			@PathVariable Long idPro){
		
		Profissional proSalvo = proRepository.findById(idPro).get();
		
		profissional.setAtualizadoEm(HandleDates.dataHoraAtual());
		profissional.setCriadoEm(proSalvo.getCriadoEm());
		
		BeanUtils.copyProperties(profissional, proSalvo, "idProfissional");

		proRepository.save(profissional);
		return ResponseEntity.ok(proSalvo);
	}
	
	
}
