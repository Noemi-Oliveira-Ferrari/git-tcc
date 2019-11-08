package br.net.daumhelp.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.net.daumhelp.utils.GetRandom;
import br.net.daumhelp.utils.HandleDates;

@Component
public class Disco {
	
	@Value("${duh.disco.raiz}")
	private String raiz;
	
	@Value("${cliente.disco.diretorio-imgs}")
	private String dirImgClientes;
	
	@Value("${pro.disco.diretorio-imgs}")
	private String dirImgPro;
	
	@Value("${pedido.disco.diretorio-imgs}")
	private String dirImgPedido;
	
	
	public String salvarFotoCliente(MultipartFile imgCliente, Long idCliente) {
		return this.salvar(this.dirImgClientes, imgCliente, idCliente, "cli");
	}
	
	
	public String salvarFotoPro(MultipartFile imgPro, Long idPro) {
		return this.salvar(this.dirImgPro, imgPro, idPro, "pro");
	}
	
	public String salvarFotoPedido(MultipartFile imgPro, Long idPedido) {
		return this.salvar(this.dirImgPro, imgPro, idPedido, "pedido");
	}
	
	public String salvar(String diretorio, MultipartFile img, Long id, String tipoUsr) {		
		String dataHora = HandleDates.dataHoraAtualBr();
		dataHora = dataHora.replaceAll(" ", "_").replaceAll("[/:]", "");
		
		String novoNome = "duh_"+tipoUsr+"_"+id+"_"+dataHora+"_"+GetRandom.random()+"."+img.getContentType().split("/")[1];
		
		//destino da imagem server
		Path caminhoImgServer = Paths.get(this.raiz, diretorio);
		//destino sem raiz para guardar no banco
		Path caminhoImg = Paths.get(diretorio);
		
		//caminho da imagem
		Path imgPath = caminhoImgServer.resolve(novoNome);
		//caminho da imagem sem raiz para guardar no banco
		Path novoCaminho = caminhoImg.resolve(novoNome);
		
		try {
			Files.createDirectories(caminhoImgServer);
			img.transferTo(imgPath.toFile());
		} catch (IOException error) {
			error.printStackTrace();
			throw new RuntimeException("Problemas para salvar arquivo");
		}
		System.out.println(novoCaminho);
		return novoCaminho.toString();
		
	}
	
	public void apagar(String img) {
		Path caminhoImg = Paths.get(this.raiz, img);
		try {
			Files.deleteIfExists(caminhoImg);
		} catch (IOException error) {
			error.printStackTrace();
			throw new RuntimeException("Problemas para apagar arquivo");
		}
	}
	
	
	
}
