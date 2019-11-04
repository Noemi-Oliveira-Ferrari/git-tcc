package br.net.daumhelp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.repository.PedidoRepository;


@CrossOrigin
@RestController
@RequestMapping("/pedidos")
public class PedidosResource {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}
}
