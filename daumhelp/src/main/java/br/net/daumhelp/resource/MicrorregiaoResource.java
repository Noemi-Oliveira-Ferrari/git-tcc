package br.net.daumhelp.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.Microrregiao;
import br.net.daumhelp.repository.MicrorregiaoRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {

	@Autowired
	private MicrorregiaoRepository microRepository;

	@GetMapping
	public List<Microrregiao> getMicros(){
		return microRepository.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Microrregiao> getMicroById(@PathVariable Long id) {
		return microRepository.findById(id);
	}

	@GetMapping("/uf/{uf}")
	public List<Microrregiao> getMicrosByIdUf(@PathVariable String uf){
		return microRepository.findByUf(uf);
	}
	
	@GetMapping("/idUf/{idUf}")
	public List<Microrregiao> getMicrosByUf(@PathVariable Long idUf){
		return microRepository.findByIdUf(idUf);
	}
	
}
