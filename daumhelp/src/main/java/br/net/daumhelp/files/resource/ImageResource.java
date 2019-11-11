package br.net.daumhelp.files.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.net.daumhelp.dto.ClienteDTO;
import br.net.daumhelp.dto.ProfissionalDTO;
import br.net.daumhelp.dto.repository.ClienteDTORepository;
import br.net.daumhelp.dto.repository.ProfissionalDTORepository;
import br.net.daumhelp.files.Disco;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.repository.PedidoRepository;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/imagens")
public class ImageResource {

	@Autowired
	private Disco disco;
	
	@Autowired
	private ClienteDTORepository clienteDTOrepository;
	
	@Autowired
	private ProfissionalDTORepository proDTOrepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	
	@PostMapping("/cliente/{idCliente}")
	public void uploadImgCliente(@RequestParam MultipartFile img, @PathVariable Long idCliente) {

		ClienteDTO cliente = clienteDTOrepository.findById(idCliente).get();
	
		if(cliente.getFoto() != null) {			
			if(cliente.getFoto() != "" && !cliente.getFoto().isEmpty()) {
				disco.apagar(cliente.getFoto());			
			}
		}
		String imgClienteCaminho = disco.salvarFotoCliente(img, idCliente);
		
		cliente.setFoto(imgClienteCaminho);
		clienteDTOrepository.save(cliente);	
	}
	
	@PostMapping("/profissional/{idPro}")
	public void uploadImgPro(@RequestParam MultipartFile img, @PathVariable Long idPro) {

		ProfissionalDTO pro = proDTOrepository.findById(idPro).get();
		
		if(pro.getFoto() != null) {
			if(pro.getFoto() != "" && !pro.getFoto().isEmpty()) {
				disco.apagar(pro.getFoto());			
			}			
		}
		
		String imgProCaminho = disco.salvarFotoPro(img, idPro);
		
		pro.setFoto(imgProCaminho);
		proDTOrepository.save(pro);	
	}
	

	@PostMapping("/pedido/{idPedido}")
	public void uploadImgsPedido(@RequestParam List<MultipartFile> imgs, @PathVariable Long idPedido) {

		Pedido pedido = pedidoRepository.findById(idPedido).get();
		
		
		ArrayList<String> imgsPedidoCaminho = new ArrayList<String>();

		int i = -1;

		for(MultipartFile img : imgs) {
			if(!img.getOriginalFilename().isEmpty() && img.getSize() > 0) {
				imgsPedidoCaminho.add(disco.salvarFotoPedido(img, idPedido));
				++i;				
				if(i == 0) {
					pedido.setFoto1(imgsPedidoCaminho.get(i));				
				}else if(i == 1) {
					pedido.setFoto2(imgsPedidoCaminho.get(i));				
				}else if(i == 2){
					pedido.setFoto3(imgsPedidoCaminho.get(i));
				}
			}
		}
		
		
		pedidoRepository.save(pedido);
		
	}
	
	
	
	
}
