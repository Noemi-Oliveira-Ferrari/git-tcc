package br.net.daumhelp.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.daumhelp.model.Avaliacao;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.repository.AvaliacaoRepository;
import br.net.daumhelp.utils.HandleDates;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@GetMapping
	public List<Avaliacao> getAvaliacoes(){
		return avaliacaoRepository.findAll();
	}
	
	@GetMapping("id/{idAvaliacao}")
	public Optional<Avaliacao> getAvaliacaoById(@PathVariable Long idAvaliacao){
		return avaliacaoRepository.findById(idAvaliacao);
	}
	
	@GetMapping("/profissional/id/{idProfissional}")
	public List<Avaliacao> getAvaliacaoByPro(@PathVariable Long idProfissional){
		return avaliacaoRepository.avaliacaoPorPro(idProfissional);
	}
	
	@GetMapping("/cliente/id/{idCliente}")
	public List<Avaliacao> getAvaliacaoByCliente(@PathVariable Long idCliente){
		return avaliacaoRepository.avaliacaoPorCliente(idCliente);
	}

	@PostMapping("/avaliar")
	public ResponseEntity<Avaliacao> solicitarPedido(
			@RequestBody Avaliacao avaliacao,
			HttpServletResponse response){

		System.out.println("-_____________________________---");
		avaliacao.setDataAvaliacao(HandleDates.dataHoraAtual());
		System.out.println(avaliacao);
		Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
		System.out.println(avaliacaoSalva);
			
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.buildAndExpand(avaliacao.getIdAvaliacaoPedido())
				.toUri();
		response.addHeader("Location", uri.toASCIIString());
		 
		return ResponseEntity.created(uri).body(avaliacao);
	}

}
