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

import br.net.daumhelp.dto.ClienteDTO;
import br.net.daumhelp.dto.ProfissionalDTO;
import br.net.daumhelp.dto.repository.ClienteDTORepository;
import br.net.daumhelp.dto.repository.ProfissionalDTORepository;
import br.net.daumhelp.model.CodeStatusPedido;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.StatusPedido;
import br.net.daumhelp.repository.PedidoRepository;
import br.net.daumhelp.repository.StatusPedidosRepository;


@CrossOrigin
@RestController
@RequestMapping("/pedidos")
public class PedidosResource {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProfissionalDTORepository proDTORepository;
	
	@Autowired
	private ClienteDTORepository clienteDTORepository;
	
	@Autowired
	private StatusPedidosRepository statusRepository;

	
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
	
//	@PostMapping("/solicitar")
//	public ResponseEntity<Pedido> solicitarPedido(
//			@RequestParam List<MultipartFile> imgs,
//			@RequestBody Pedido pedido,
//			HttpServletResponse response){
//		
//		pedido.setValorServico(0.0);
//		
//		Pedido pedidoSalvo = pedidoRepository.save(pedido);
//		
//		ArrayList<String> caminhosImgs = upload.multiUploadPedido(imgs, pedido.getIdPedido());
//		
//		pedido.setFoto1(caminhosImgs.get(0));
//		pedido.setFoto2(caminhosImgs.get(1));
//		pedido.setFoto3(caminhosImgs.get(2));
//		
//		pedidoRepository.save(pedido);
//		
//		URI uri = ServletUriComponentsBuilder
//				.fromCurrentRequestUri()
//				.buildAndExpand(pedido.getIdPedido())
//				.toUri();
//		response.addHeader("Location", uri.toASCIIString());
//		
//		return ResponseEntity.created(uri).body(pedido);
//	}
	

	@PutMapping("/resposta/{idPedido}")
	public ResponseEntity<Pedido> respostaPedido(
			@RequestBody Pedido pedido,
			@PathVariable Long idPedido){

		ProfissionalDTO proPedido = proDTORepository.findById(pedido.getProfissional().getIdProfissional()).get();
		ClienteDTO clientePedido = clienteDTORepository.findById(pedido.getCliente().getIdCliente()).get();
		
		StatusPedido statusPedido = statusRepository.findById((long)CodeStatusPedido.ORCADO.getValue()).get();
		
		pedido.setProfissional(proPedido);
		pedido.setCliente(clientePedido);
		pedido.setStatus(statusPedido);
		System.out.println(idPedido);
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		pedido.setValorServico(pedido.getHorasServico()*pedido.getProfissional().getValorHora());
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedidoSalvo);
	}

	@PutMapping("/aceitar/{idPedido}")
	public ResponseEntity<Pedido> aceitarPedido(@PathVariable Long idPedido){
		
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		
		StatusPedido statusPedido = statusRepository.findById((long)CodeStatusPedido.ACEITO.getValue()).get();
		
		pedido.setStatus(statusPedido);
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping("/rejeitar/{idPedido}")
	public ResponseEntity<Pedido> rejeitarPedido(@PathVariable Long idPedido){
		
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		
		StatusPedido statusPedido = statusRepository.findById((long)CodeStatusPedido.REJEITADO.getValue()).get();
		
		pedido.setStatus(statusPedido);
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping("/{usuario}/cancelar/{idPedido}")
	public ResponseEntity<Pedido> proClientePedido(@PathVariable String usuario, @PathVariable Long idPedido){
		
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		StatusPedido statusPedido = pedido.getStatus();
		
		if(usuario.equals("cliente")) {
			ProfissionalDTO proPedido = proDTORepository.findById(pedido.getProfissional().getIdProfissional()).get();
			
			statusPedido = statusRepository.findById((long)CodeStatusPedido.CANCELADO_CLIENTE.getValue()).get();
			
			pedido.setMultaCliente(proPedido.getValorHora()*0.2);	
			
			 
		}else if(usuario.equals("profissional")){
			statusPedido = statusRepository.findById((long)CodeStatusPedido.CANCELADO_PROFISSIONAL.getValue()).get();		
		}
		
		pedido.setStatus(statusPedido);
		
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping("/concluir/{idPedido}")
	public ResponseEntity<Pedido> concluirPedido(@PathVariable Long idPedido){
		
		Pedido pedido = pedidoRepository.findById(idPedido).get();
		StatusPedido statusPedido = statusRepository.findById((long)CodeStatusPedido.CONCLUIDO.getValue()).get();

		pedido.setStatus(statusPedido);
		
		Pedido pedidoSalvo = pedidoRepository.findById(idPedido).get();
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "idPedido", "criadoEm", "atualizadoEm", "cliente", "profissional");
		pedidoRepository.save(pedidoSalvo);
		
		return ResponseEntity.ok(pedido);
	}
	
	
}
