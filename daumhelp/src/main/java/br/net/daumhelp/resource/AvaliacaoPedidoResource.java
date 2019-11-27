package br.net.daumhelp.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.daumhelp.model.AvaliacaoPedido;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.repository.AvaliacaoPedidoRepository;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoPedidoResource {

	private AvaliacaoPedidoRepository avaliacaoRepository;
	
	@GetMapping
	public List<AvaliacaoPedido> getAvaliacoes(){
		return avaliacaoRepository.findAll();
	}

	@PostMapping("/avaliar/pedido/{idPedido}")
	public ResponseEntity<AvaliacaoPedido> solicitarPedido(
			@RequestBody AvaliacaoPedido avaliacao,
			HttpServletResponse response){

		AvaliacaoPedido avaliacaoSalva = avaliacaoRepository.save(avaliacao);
			
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.buildAndExpand(avaliacao.getIdAvaliacaoPedido())
				.toUri();
		response.addHeader("Location", uri.toASCIIString());
		 
		return ResponseEntity.created(uri).body(avaliacao);
	}

}
