package br.net.daumhelp.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.Categoria;
import br.net.daumhelp.repository.CategoriaRepository;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> getCategorias(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<Categoria> getCategoriaById(@PathVariable Long id) {
		return categoriaRepository.findById(id);
	}
	

}
