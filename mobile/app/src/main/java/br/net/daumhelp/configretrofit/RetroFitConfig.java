package br.net.daumhelp.configretrofit;

import br.net.daumhelp.service.CategoriaService;
import br.net.daumhelp.service.ClienteService;
import br.net.daumhelp.service.ComentarioService;
import br.net.daumhelp.service.EnderecoService;
import br.net.daumhelp.service.FotoService;
import br.net.daumhelp.service.LoginService;
import br.net.daumhelp.service.PedidoService;
import br.net.daumhelp.service.ProfissionalService;
import br.net.daumhelp.service.SubcategoriaService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetroFitConfig {

    private Retrofit retroFit;

//    public RetroFitConfig(){
//        retroFit = new Retrofit.Builder()
//                .baseUrl("http://10.107.144.13:8080/")
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build();
//    }

    public RetroFitConfig(){
        retroFit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-220-68-195.compute-1.amazonaws.com:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    public CategoriaService getCategoriaService(){
        return this.retroFit.create(CategoriaService.class);
    }

    public SubcategoriaService getSubcategoriaService(){
        return this.retroFit.create(SubcategoriaService.class);
    }

    public EnderecoService getEnderecoService(){
        return this.retroFit.create(EnderecoService.class);
    }

    public ProfissionalService getProfissionalService(){
        return this.retroFit.create(ProfissionalService.class);
    }

    public ClienteService getClienteService(){
        return this.retroFit.create(ClienteService.class);
    }

    public PedidoService getPedidoService(){
        return this.retroFit.create(PedidoService.class);
    }

    public LoginService getLoginService(){
        return this.retroFit.create(LoginService.class);
    }

    public FotoService getFotoService(){
        return this.retroFit.create(FotoService.class);
    }

    public ComentarioService getComentarioService(){return  this.retroFit.create(ComentarioService.class);}


}
