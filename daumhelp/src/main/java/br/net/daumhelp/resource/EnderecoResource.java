package br.net.daumhelp.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.daumhelp.cep.GetCep;
import br.net.daumhelp.model.Cidade;
import br.net.daumhelp.model.Endereco;
import br.net.daumhelp.model.EnderecoCep;
import br.net.daumhelp.repository.CidadeRepository;
import br.net.daumhelp.repository.EnderecoRepository;
import br.net.daumhelp.utils.HandleDates;
import br.net.daumhelp.utils.HandleEmails;
import br.net.daumhelp.utils.HandleJsonInJava;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Endereco> getEnderecos(){
		return enderecoRepository.findAll();
	}

	@GetMapping("/id/{idEndereco}")
	public Optional<Endereco> getEnderecoById(@PathVariable Long idEndereco){
		return enderecoRepository.findById(idEndereco);
	}
	
	@GetMapping("/cep/{cep}")
	public ResponseEntity<Endereco> getEnderecosViacep (@PathVariable String cep){
		Endereco endereco = new Endereco(); 
		String jsonEndereco = GetCep.trazerCep(cep);
		if(!jsonEndereco.contains("erro")) {
			EnderecoCep enderecoCep = HandleJsonInJava.convertEnderecoJsonToJavaObject(jsonEndereco);
			
			endereco = new Endereco();
			endereco.setLogradouro(enderecoCep.getLogradouro());
			endereco.setCep(enderecoCep.getCep());
			endereco.setBairro(enderecoCep.getBairro());
			
			Long idCidade = Long.parseLong(enderecoCep.getIbge());
			Cidade cidade = cidadeRepository.findById(idCidade).get();
			
			endereco.setCidade(cidade);
			return ResponseEntity.ok(endereco);
		}else {
			return new ResponseEntity<Endereco>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Endereco> saveEndereco
			(@RequestBody Endereco endereco, 
			HttpServletResponse response){
		
//		
//		endereco.setCriadoEm(HandleDates.dataHoraAtual());
//		endereco.setAtualizadoEm(HandleDates.dataHoraAtual());
	
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		URI uri = ServletUriComponentsBuilder	
				.fromCurrentRequestUri()
				.buildAndExpand(endereco.getIdEndereco())
				.toUri();
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(endereco);
	}
	
	
	@PutMapping("/atualizar/id/{idEndereco}")
	public ResponseEntity<Endereco> atualizarEndereco(
			@RequestBody Endereco endereco,
			@PathVariable Long idEndereco){
		Endereco enderecoSalvo = enderecoRepository.findById(idEndereco).get();
		
//		endereco.setAtualizadoEm(HandleDates.dataHoraAtual());
//		endereco.setCriadoEm(enderecoSalvo.getCriadoEm());
//		
		BeanUtils.copyProperties(endereco, enderecoSalvo, "idEndereco", "criadoEm", "atualizadoEm");
		
		enderecoRepository.save(enderecoSalvo);
		
		return ResponseEntity.ok(enderecoSalvo);
	}
	
	
}
