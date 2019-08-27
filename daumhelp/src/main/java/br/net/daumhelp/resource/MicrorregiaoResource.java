package br.net.daumhelp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.Microrregiao;
import br.net.daumhelp.repository.MicrorregiaoRepository;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {

	@Autowired
	private MicrorregiaoRepository microRepository;
	
	@GetMapping
	public List<Microrregiao> getMicrorregioes(){
		return microRepository.findAll();
	}
	
}
