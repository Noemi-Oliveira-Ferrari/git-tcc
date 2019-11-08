package br.net.daumhelp.files;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import br.net.daumhelp.dto.ClienteDTO;
import br.net.daumhelp.dto.ProfissionalDTO;
import br.net.daumhelp.dto.repository.ClienteDTORepository;
import br.net.daumhelp.dto.repository.ProfissionalDTORepository;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.repository.PedidoRepository;

public class ImgUpload {

	@Autowired
	private Disco disco;
	
	@Autowired
	private ClienteDTORepository clienteDTOrepository;
	
	@Autowired
	private ProfissionalDTORepository proDTOrepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
		
	
	public void uploadImgCliente(MultipartFile img, Long idCliente) {
		
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
	
	public void uploadImgPro(MultipartFile img, Long idPro) {
		
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
	
	public ArrayList<String> multiUploadPedido(List<MultipartFile> imgs, Long idPedido) {

		ArrayList<String> imgsPedidoCaminho = null;
		
		for(MultipartFile img : imgs) {
		 	imgsPedidoCaminho.add(disco.salvarFotoPedido(img, idPedido));
		}
		
		return imgsPedidoCaminho;		
		
	}
	
	
}
