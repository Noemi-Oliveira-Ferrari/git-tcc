package br.net.daumhelp.files.resource;

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
		
	@PostMapping("/cliente/{idCliente}")
	public void uploadImgCliente(@RequestParam MultipartFile img, @PathVariable Long idCliente) {
		ClienteDTO cliente = clienteDTOrepository.findById(idCliente).get();
	
		if(cliente.getFoto() != null) {			
			if(!cliente.getFoto().isBlank() && !cliente.getFoto().isEmpty()) {
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
			if(!pro.getFoto().isBlank() && !pro.getFoto().isEmpty()) {
				disco.apagar(pro.getFoto());			
			}			
		}
		
		String imgProCaminho = disco.salvarFotoPro(img, idPro);
		
		pro.setFoto(imgProCaminho);
		proDTOrepository.save(pro);	
	}
	
	
	
}
