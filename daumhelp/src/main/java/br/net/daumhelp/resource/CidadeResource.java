package br.net.daumhelp.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.Cidade;
import br.net.daumhelp.repository.CidadeRepository;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	public List<Cidade> getCidades(){
		return cidadeRepository.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Cidade> getCidadeById(@PathVariable Long id){
		return cidadeRepository.findById(id);
	}

	@GetMapping("/microrregiao/{idMicro}")
	public List<Cidade> getCidadeByIdMicro(@PathVariable Long idMicro){
		return cidadeRepository.findByIdMicro(idMicro);
	}	

	@GetMapping("/uf/{uf}")
	public List<Cidade> getCidadeByUf(@PathVariable String uf){
		return cidadeRepository.findByUf(uf);	
	}

	@GetMapping("/idUf/{idUf}")
	public List<Cidade> getCidadeByIdUf(@PathVariable Long idUf){
		return cidadeRepository.findByIdUf(idUf);		
	}	
}
