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
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.repository.ClienteDTORepository;
import br.net.daumhelp.repository.ClienteRepository;
import br.net.daumhelp.utils.HandleDates;
import br.net.daumhelp.utils.HandleEmails;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteDTORepository clienteDTORepository;
	

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

	
	@PostMapping
	public ResponseEntity<Cliente> gravarCliente(
			@RequestBody Cliente cliente,
			HttpServletResponse response){

//		cliente.setCriadoEm(HandleDates.dataHoraAtual());
//		cliente.setAtualizadoEm(HandleDates.dataHoraAtual());
		
		 Cliente clienteSalvo = clienteRepository.save(cliente);
		 
		 URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequestUri()
				 .buildAndExpand(cliente.getIdCliente())
				 .toUri();
		 response.addHeader("Location", uri.toASCIIString());
		 
		 return ResponseEntity.created(uri).body(cliente);
	}

	@GetMapping
	public List<ClienteDTO> getClientes(){
		return clienteDTORepository.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<ClienteDTO> getClienteById(@PathVariable Long id) {
		return clienteDTORepository.findById(id);
	}
	
	@PutMapping("/atualizar/id/{idCliente}")
	public ResponseEntity<Cliente> atualizarCliente(
			@RequestBody Cliente cliente,
			@PathVariable Long idCliente){
		
		Cliente clienteSalvo = clienteRepository.findById(idCliente).get();
		
//		cliente.setAtualizadoEm(HandleDates.dataHoraAtual());
//		cliente.setCriadoEm(clienteSalvo.getCriadoEm());
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "idCliente", "criadoEm", "atualizadoEm");

		clienteRepository.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}

}
