package br.net.daumhelp.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.daumhelp.dto.ProfissionalDTO;
import br.net.daumhelp.dto.repository.ClienteDTORepository;
import br.net.daumhelp.dto.repository.ProfissionalDTORepository;
import br.net.daumhelp.model.Confirmacao;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.repository.ProfissionalRepository;
import br.net.daumhelp.utils.HandleEmails;

//@CrossOrigin
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/profissionais")
public class ProfissionalResource {
	
	@Autowired
	private ProfissionalRepository proRepository;
	@Autowired
	private ProfissionalDTORepository proDTORepository;
	@Autowired
	private ClienteDTORepository clienteDTORepository;
	

	//ENVIAR EMAIL COM CODIGO DE CONFIRMAÇÃO PARA USUARIO CLIENTE
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/confirmacao")
	public ResponseEntity<Confirmacao> confirmarEmail(@RequestBody @Validated Confirmacao confirm) {
		if(HandleEmails.enviar(confirm)) {
			return new ResponseEntity<Confirmacao>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Confirmacao>(HttpStatus.REQUEST_TIMEOUT);
		}
	}

	@PostMapping("/login")
	public Profissional buscarUsuario(@RequestBody Profissional profissional) {
		return proRepository.findUserLogin(profissional.getEmail(), profissional.getSenha());
	}
	
	@GetMapping
	public List<ProfissionalDTO> getPros(){
		return proDTORepository.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<ProfissionalDTO> getProById(@PathVariable Long id) {
		return proDTORepository.findById(id);
	}

	@GetMapping("/categoria/{idCategoria}")
	public List<ProfissionalDTO> getProByCategoria(@PathVariable Long idCategoria) {
		return proDTORepository.findByCategoria(idCategoria);
	}

	@GetMapping("/subcategoria/{idSubategoria}")
	public List<ProfissionalDTO> getProBySubcategoria(@PathVariable Long idSubategoria) {
		return proDTORepository.findBySubcategoria(idSubategoria);
	}

	@GetMapping("/cpf/{cpf}")
	public ProfissionalDTO getProByCpf(@PathVariable String cpf) {
		return proDTORepository.findByCpf(cpf);
	}
	
	@GetMapping("/email/{email}")
	public ProfissionalDTO getProByEmail(@PathVariable String email) {
		return proDTORepository.findByEmail(email);
	}

	@GetMapping("/cnpj/{cnpj}")
	public ProfissionalDTO getProByCnpj(@PathVariable String cnpj) {
		return proDTORepository.findByCnpj(cnpj);
	}
	
//**************************************
	@GetMapping("{idProfissional}/cpf/{cpf}")
	public String getCpfByPro(@PathVariable Long idProfissional, @PathVariable String cpf) {
		return proDTORepository.findByCpfPro(idProfissional, cpf).getCpf();
		
	}
	
	@GetMapping("{idProfissional}/email/{email}")
	public String getEmailByPro(@PathVariable Long idProfissional, @PathVariable String email) {
		return proDTORepository.findByEmailPro(idProfissional, email).getEmail();
	}

	@GetMapping("{idProfissional}/cnpj/{cnpj}")
	public String getCnpjByPro(@PathVariable Long idProfissional, @PathVariable String cnpj) {
		return proDTORepository.findByCnpjPro(idProfissional, cnpj).getCnpj();
	}
//**************************************

	@GetMapping("/endereco/{idEndereco}")
	public List<ProfissionalDTO> getProByIdEndereco(@PathVariable Long idEndereco) {
		return proDTORepository.findByIdEndereco(idEndereco);
	}

	@GetMapping("/cep/{cep}")
	public List<ProfissionalDTO> getProByIdEndereco(@PathVariable String cep) {
		return proDTORepository.findByCep(cep);
	}

	@GetMapping("/cidade/{idCidade}")
	public List<ProfissionalDTO> getProByCidade(@PathVariable Long idCidade) {
		return proDTORepository.findByCidade(idCidade);
	}

	@GetMapping("/microrregiao/{idMicro}")
	public List<ProfissionalDTO> getProByMicro(@PathVariable Long idMicro) {
		return proDTORepository.findByMicrorregiao(idMicro);
	}

	@GetMapping("/uf/{uf}")
	public List<ProfissionalDTO> getProByUf(@PathVariable String uf) {
		return proDTORepository.findByUf(uf);
	}

	@GetMapping("/idUf/{idUf}")
	public List<ProfissionalDTO> getProByIdUf(@PathVariable Long idUf) {
		return proDTORepository.findByIdUf(idUf);
	}
	
	@PostMapping
	public ResponseEntity<Profissional> gravarPro(
			@RequestBody Profissional profissional,
			HttpServletResponse response){

		 Profissional proSalvo = proRepository.save(profissional);
		 
		 URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequestUri()
				 .buildAndExpand(profissional.getIdProfissional())
				 .toUri();
		 response.addHeader("Location", uri.toASCIIString());
		 
		 return ResponseEntity.created(uri).body(profissional);
	}
	
	@PutMapping("/atualizar/id/{idPro}")
	public ResponseEntity<Profissional> atualizarPro(
			@RequestBody Profissional profissional,
			@PathVariable Long idPro){
		
		Profissional proSalvo = proRepository.findById(idPro).get();
		
		if(!profissional.getSenha().isEmpty() && profissional.getSenha() != null) {
			BeanUtils.copyProperties(profissional, proSalvo, "idProfissional", "criadoEm", "atualizadoEm", "senha");
			
		}else {
			BeanUtils.copyProperties(profissional, proSalvo, "idProfissional", "criadoEm", "atualizadoEm");			
		}
		

		proRepository.save(proSalvo);
		
		return ResponseEntity.ok(proSalvo);
	}
	
	
}
