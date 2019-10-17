package br.net.daumhelp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.TipoUsuario;
import br.net.daumhelp.repository.TipoUsuarioRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tipos")
public class TipoUsuarioResource {
	
	@Autowired
	public TipoUsuarioRepository tipoRepository;
	
	@GetMapping
	public List<TipoUsuario> getTipos() {
		return tipoRepository.findAll();
	}
	
}
