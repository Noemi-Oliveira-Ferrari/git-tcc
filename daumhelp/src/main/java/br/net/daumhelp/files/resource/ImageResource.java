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

import br.net.daumhelp.dto.repository.ClienteDTORepository;
import br.net.daumhelp.dto.repository.ProfissionalDTORepository;
import br.net.daumhelp.files.Disco;
import br.net.daumhelp.files.ImgUpload;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.repository.PedidoRepository;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://ec2-3-220-68-195.compute-1.amazonaws.com")
@RestController
@RequestMapping("/imagens")
public class ImageResource {


	private ImgUpload upload = new ImgUpload();
	
	@PostMapping("/cliente/{idCliente}")
	public void uploadImgCliente(@RequestParam MultipartFile img, @PathVariable Long idCliente) {
		
		upload.uploadImgCliente(img, idCliente);
	}
	
	@PostMapping("/profissional/{idPro}")
	public void uploadImgPro(@RequestParam MultipartFile img, @PathVariable Long idPro) {
		
		upload.uploadImgPro(img, idPro);
	}
	
//
//	@PostMapping("/pedido/{idPedido}")
//	public void uploadImgsPedido(@RequestParam List<MultipartFile> imgs, @PathVariable Long idPedido) {
////		public void uploadImgsPedido(@PathVariable Long idPedido) {
//
////		Pedido pedido = proDTOrepository.findById(idPro).get();
////		
////		if(pro.getFoto() != null) {
////			if(pro.getFoto() != "" && !pro.getFoto().isEmpty()) {
////				disco.apagar(pro.getFoto());			
////			}			
////		}
////		
////		pro.setFoto(imgProCaminho);
////		proDTOrepository.save(pro);
//		
//		
//		
//		
//		
//		System.out.println(pedido);
//		
//		
//	}
//	
	
	
	
}
