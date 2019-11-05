package br.net.daumhelp.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.model.StatusPedido;
import br.net.daumhelp.repository.ClienteRepository;
import br.net.daumhelp.repository.PedidoRepository;
import br.net.daumhelp.repository.ProfissionalRepository;
import br.net.daumhelp.repository.StatusPedidosRepository;


@CrossOrigin
@RestController
@RequestMapping("/pedidos")
public class PedidosResource {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProfissionalRepository proRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private StatusPedidosRepository statusRepository;

	Long status;
	
	@GetMapping
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}

	@PostMapping("/solicitar")
	public ResponseEntity<Pedido> solicitarPedido(
			@RequestBody Pedido pedido,
			HttpServletResponse response){

		pedido.setValorServico(0.0);
		
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		
		 
		 URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequestUri()
				 .buildAndExpand(pedido.getIdPedido())
				 .toUri();
		 response.addHeader("Location", uri.toASCIIString());
		 
		 return ResponseEntity.created(uri).body(pedido);
	}
	

	@PutMapping("/resposta/{idPedido}")
	public ResponseEntity<Pedido> respostaPedido(
			@RequestBody Pedido pedido,
			@PathVariable Long idPedido){

		Profissional proPedido = proRepository.findById(pedido.getProfissional().getIdProfissional()).get();
		Cliente clientePedido = clienteRepository.findById(pedido.getCliente().getIdCliente()).get();
		
		status = (long) 2;
		
		StatusPedido statusPedido = statusRepository.findById(status).get();
		
		
		pedido.setProfissional(proPedido);
		pedido.setCliente(clientePedido);
		pedido.setStatus(statusPedido);
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		pedido.setValorServico(pedido.getHorasServico()*pedido.getProfissional().getValorHora());
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedidoSalvo);
	}

	@PutMapping("/aceitar/{idPedido}")
	public ResponseEntity<Pedido> aceitarPedido(
			@RequestBody Pedido pedido,
			@PathVariable Long idPedido){
		
		status = (long) 3;		
		StatusPedido statusPedido = statusRepository.findById(status).get();
		
		pedido.setStatus(statusPedido);
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		pedido.setValorServico(pedido.getHorasServico()*pedido.getProfissional().getValorHora());
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedidoSalvo);
	}
	
	
}
