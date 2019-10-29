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

import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.ClienteDTO;
import br.net.daumhelp.model.Confirmacao;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.ClienteDTO;
import br.net.daumhelp.repository.ClienteDTORepository;
import br.net.daumhelp.repository.ClienteRepository;
import br.net.daumhelp.repository.ProfissionalDTORepository;
import br.net.daumhelp.utils.HandleDates;
import br.net.daumhelp.utils.HandleEmails;


//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteDTORepository clienteDTORepository;
	
	@Autowired
	private ProfissionalDTORepository proDTORepository;
	

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/confirmacao")
	public ResponseEntity<Confirmacao> confirmarEmail(@RequestBody @Validated Confirmacao confirm) {
		System.out.println("__________\n"+confirm);
		if(HandleEmails.enviar(confirm)) {
			return new ResponseEntity<Confirmacao>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Confirmacao>(HttpStatus.REQUEST_TIMEOUT);
		}
	}

	

	@PostMapping("/login")
	public Cliente buscarUsuario(@RequestBody Cliente cliente) {
		return clienteRepository.findUserLogin(cliente.getEmail(), cliente.getSenha());
	}	

	
	@GetMapping
	public List<ClienteDTO> getClientes(){
		return clienteDTORepository.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<ClienteDTO> getClienteById(@PathVariable Long id) {
		return clienteDTORepository.findById(id);
	}

	@GetMapping("/cpf/{cpf}")
	public ClienteDTO getProByCpf(@Validated @PathVariable String cpf) {
		return clienteDTORepository.findByCpf(cpf);
	}

	@GetMapping("/verificar/cpf/{cpf}")
	public Optional<?> buscarCpfExistente(@Validated @PathVariable String cpf) {
		if(clienteDTORepository.verificarCpf(cpf) == Optional.empty()) {
			if(proDTORepository.verificarCpf(cpf) != Optional.empty()) {
				Optional<?> optionalProDTO = proDTORepository.verificarCpf(cpf);
				return optionalProDTO;
			}else {
				return Optional.empty();
			}
		}else {
			Optional<?> optionalClienteDTO = clienteDTORepository.verificarCpf(cpf);
			return optionalClienteDTO;	
		}
	}

	@GetMapping("/verificar/email/{email}")
	public Optional<?> buscarEmailExistente(@Validated @PathVariable String email) {
		if(clienteDTORepository.verificarEmail(email) == Optional.empty()) {
			if(proDTORepository.verificarEmail(email) != Optional.empty()) {
				Optional<?> optionalProDTO = proDTORepository.verificarEmail(email);
				return optionalProDTO;
			}else {
				return Optional.empty();
			}
		}else {
			Optional<?> optionalClienteDTO = clienteDTORepository.verificarEmail(email);
			return optionalClienteDTO;	
		}
	}

	@GetMapping("/endereco/{idEndereco}")
	public List<ClienteDTO> getProByIdEndereco(@PathVariable Long idEndereco) {
		return clienteDTORepository.findByIdEndereco(idEndereco);
	}

	@GetMapping("/cep/{cep}")
	public List<ClienteDTO> getProByIdEndereco(@Validated @PathVariable String cep) {
		return clienteDTORepository.findByCep(cep);
	}

	@GetMapping("/cidade/{idCidade}")
	public List<ClienteDTO> getProByCidade(@PathVariable Long idCidade) {
		return clienteDTORepository.findByCidade(idCidade);
	}

	@GetMapping("/microrregiao/{idMicro}")
	public List<ClienteDTO> getProByMicro(@PathVariable Long idMicro) {
		return clienteDTORepository.findByMicrorregiao(idMicro);
	}

	@GetMapping("/uf/{uf}")
	public List<ClienteDTO> getProByUf(@PathVariable String uf) {
		return clienteDTORepository.findByUf(uf);
	}

	@GetMapping("/idUf/{idUf}")
	public List<ClienteDTO> getProByIdUf(@PathVariable Long idUf) {
		return clienteDTORepository.findByIdUf(idUf);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> gravarCliente(
			@Validated @RequestBody Cliente cliente,
			HttpServletResponse response){
		
		 Cliente clienteSalvo = clienteRepository.save(cliente);
		 
		 URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequestUri()
				 .buildAndExpand(cliente.getIdCliente())
				 .toUri();
		 response.addHeader("Location", uri.toASCIIString());
		 
		 return ResponseEntity.created(uri).body(cliente);
	}
	
	@PutMapping("/atualizar/id/{idCliente}")
	public ResponseEntity<Cliente> atualizarCliente(
			@Validated @RequestBody Cliente cliente,
			@PathVariable Long idCliente){
		
		Cliente clienteSalvo = clienteRepository.findById(idCliente).get();
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "idCliente", "criadoEm", "atualizadoEm");

		clienteRepository.save(clienteSalvo);
		
		return ResponseEntity.ok(clienteSalvo);
	}

}
