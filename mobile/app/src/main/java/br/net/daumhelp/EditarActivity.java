package br.net.daumhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.menu.perfil.PerfilFragmentActivity;
import br.net.daumhelp.model.Categoria;
import br.net.daumhelp.model.Endereco;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.model.Subcategoria;
import br.net.daumhelp.model.TipoUsuario;
import br.net.daumhelp.recursos.EncryptString;
import br.net.daumhelp.recursos.Mascara;
import br.net.daumhelp.recursos.MascaraCpfCnpj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etData;
    private EditText etCpf;
    private EditText etSenha;
    private EditText etSenhaConfirmacao;
    private EditText etEmail;
    private EditText etCep;
    private EditText etCidade;
    private EditText etLogradouro;
    private EditText etUf;
    private EditText etBairro;
    private EditText etCategoria;
    private EditText etSubcategoria;
    private TextView tvEditarDados;
    private TextView tvEditarLocal;
    private TextView tvEditarServico;
    private TextView tvNome;
    private Button btnCep;
    private Profissional profissional;
    private Endereco endereco;
    private Long idCidade;
    private ImageView ivToolbar;
    private EditText etResumo;
    private EditText etValorHora;
    private TextView tvTituloCategoria;
    private TextView tvTituloSubcategoria;
    private Spinner spnCategoria;
    private Spinner spnSubcategoria;
    private List<Categoria> listaCategoria;
    private List<Subcategoria> listaSubcategoria;
    private Long idSub;
    private Long idCategoria;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        etNome = findViewById(R.id.et_nome_pro);
        etData = findViewById(R.id.et_nasc_pro);
        etResumo = findViewById(R.id.et_qualificacoes);
        etValorHora = findViewById(R.id.et_valor_hora);
        tvTituloCategoria = findViewById(R.id.tv_titulo_pro);
        spnCategoria = findViewById(R.id.spn_categoria);
        spnSubcategoria = findViewById(R.id.spn_subcategoria);
        etCpf = findViewById(R.id.et_cpf_pro);
        etSenha = findViewById(R.id.et_senha_pro);
        etSenhaConfirmacao = findViewById(R.id.et_confirm_pro);
        etEmail = findViewById(R.id.et_email_pro);
        etCep = findViewById(R.id.et_cep_pro);
        etCidade = findViewById(R.id.et_cidade_pro);
        etLogradouro = findViewById(R.id.et_logradouro_pro);
        etUf = findViewById(R.id.et_uf_pro);
        etBairro = findViewById(R.id.et_bairro_pro);
        tvEditarDados = findViewById(R.id.tv_editar_dados);
        tvEditarLocal = findViewById(R.id.tv_editar_local);
        btnCep = findViewById(R.id.btn_gerar_cep);
        tvNome = findViewById(R.id.tv_nome);
        ivToolbar = findViewById(R.id.iv_toolbar);
        tvEditarServico = findViewById(R.id.tv_editar_servico);
        tvTituloSubcategoria = findViewById(R.id.tv_titulo_sub);

        etCategoria = findViewById(R.id.et_categoria);
        etSubcategoria = findViewById(R.id.et_subcategoria);



        desativarCampos();

        Mascara maskCep = new Mascara("#####-###", etCep);
        etCep.addTextChangedListener(maskCep);

       /*Mascara maskData = new Mascara("##/##/####", etData);
        etData.addTextChangedListener(maskData);*/

        etCpf.addTextChangedListener(MascaraCpfCnpj.insert(etCpf));

        ivToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        /*PEGANDO PROFISSIONAL PARA CARREGAR O PERFIL*/
        Intent intent = getIntent();
        if (intent.getSerializableExtra("profissional") != null) {
            profissional = (Profissional) intent.getSerializableExtra("profissional");
            carregarDados(profissional);
        }



        tvEditarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etCep.setEnabled(true);
                btnCep.setEnabled(true);
                btnCep.setTextColor(Color.parseColor("#57BC90"));
                etCep.requestFocus(View.FOCUS_LEFT);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etCep, InputMethodManager.SHOW_IMPLICIT);

                if(tvEditarLocal.getText().equals("Editar")){

                    tvEditarLocal.setText("Salvar");

                    btnCep.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String cep = etCep.getText().toString();
                            /*VALIDAÇÃO DO TAMANHO DO CEP*/
                            if(etCep.length() == 8 || etCep.length() == 9){
                                Call<Endereco> call = new RetroFitConfig().getEnderecoService().buscarEndereco(cep);
                                call.enqueue(new Callback<Endereco>() {
                                    @Override
                                    public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                                        if(response.body().getCidade() == null){

                                            etCep.setError("CEP inválido");

                                        }else{
                                            carregarEndereco(response.body());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Endereco> call, Throwable t) {
                                        Log.i("Retrofit Endereço", t.getMessage());
                                        etCep.setError("CEP inválido");
                                    }

                                });
                            }else{
                                etCep.setError("CEP inválido");
                            }
                        }
                    });

                }else if(tvEditarLocal.getText().equals("Salvar")){
                    btnCep.setTextColor(Color.parseColor("#b1b1b1"));
                    tvEditarLocal.setText("Editar");
                    etCep.setEnabled(false);

                }
            }
        });

        tvEditarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ativarCampos();

                /*ABRIR TECLADO QUANDO CLICAR EM EDITAR DADOS*/
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etNome, InputMethodManager.SHOW_IMPLICIT);

                if(tvEditarDados.getText().equals("Editar")){

                    tvEditarDados.setText("Salvar");
                    Toast.makeText(EditarActivity.this, "DIGITAR", Toast.LENGTH_SHORT).show();

                }else if(tvEditarDados.getText().equals("Salvar")){

                    tvEditarDados.setText("Editar");
                    desativarCampos();

                    /*MONTANDO O OBJETO PROFISSIONAL QUE SERÁ ATUALIZADO*/
                    profissional.setNome(etNome.getText().toString());
                    profissional.setDataNasc(etData.getText().toString());
                    profissional.setCpf(etCpf.getText().toString());
                    profissional.setEmail(etEmail.getText().toString());
                    TipoUsuario tipoUsuario = new TipoUsuario();
                    tipoUsuario.setIdTipoUsuario(1);
                    tipoUsuario.setTipoUsuario("p");
                    profissional.setTipoUsuario(tipoUsuario);

                    if(etSenha.getText().equals("") || etSenha.getText().toString().isEmpty() || etSenha.getText().equals(null)){

                        profissional.setSenha(profissional.getSenha());

                    }else{

                        profissional.setSenha(EncryptString.gerarHash(etSenha.getText().toString()));

                    }


                    /*CHAMADA PARA ATUALIZAR PROFISSIONAL*/
                    Call<Profissional> call = new RetroFitConfig().getProfissionalService().atualizarPro(profissional.getIdProfissional(), profissional);
                    call.enqueue(new Callback<Profissional>() {
                        @Override
                        public void onResponse(Call<Profissional> call, Response<Profissional> response) {

                            response.body();

                        }

                        @Override
                        public void onFailure(Call<Profissional> call, Throwable t) {
                            Log.i("Retrofit", t.getMessage());
                        }
                    });
                }
            }
        });


        tvEditarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ativarCampos();


            }
        });


        /*CHAMADA DAS SUBCATEGORIAS*/
        Call<List<Subcategoria>> callsub = new RetroFitConfig().getSubcategoriaService().buscarSubcategorias(Integer.parseInt(profissional.getSubcategoria().getCategoria().getIdCategoria().toString()));
        callsub.enqueue(new Callback<List<Subcategoria>>() {
            @Override
            public void onResponse(Call<List<Subcategoria>> callsub, Response<List<Subcategoria>> response) {

                response.body();
                Log.d("CAT", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Subcategoria>> callsub, Throwable t) {
                Log.i("Retrofit2", t.getMessage());
            }
        });


    }

    /*MÉTODO DE CARREGAR ENDEREÇO*/
    private void carregarEndereco(Endereco endereco){
        this.endereco = endereco;
        etUf.setText(endereco.getCidade().getMicrorregiao().getUf().toString());
        etBairro.setText(endereco.getBairro());
        etLogradouro.setText(endereco.getLogradouro());
        etCidade.setText(endereco.getCidade().getCidade().toString());
        idCidade = endereco.getCidade().getIdCidade();

    }

    /*MÉTODO DE CARREGAR DADOS NO PERFIL*/
    private void carregarDados(Profissional profissional){
        etNome.setText(profissional.getNome());
        tvNome.setText(profissional.getNome());
        etData.setText(profissional.getDataNasc());
        etCpf.setText(profissional.getCpf());
        etEmail.setText(profissional.getEmail());
        etCep.setText(profissional.getEndereco().getCep());
        etCidade.setText(profissional.getEndereco().getCidade().getCidade());
        etLogradouro.setText(profissional.getEndereco().getLogradouro());
        etUf.setText(profissional.getEndereco().getCidade().getMicrorregiao().getUf().getUf());
        etBairro.setText(profissional.getEndereco().getBairro());
        etResumo.setText(profissional.getResumoQualificacoes());
        etValorHora.setText(String.valueOf(profissional.getValorHora()));
        etCategoria.setText(profissional.getSubcategoria().getCategoria().getCategoria());
        etSubcategoria.setText(profissional.getSubcategoria().getSubcategoria());



    }

    /*DESATIVA OS CAMPOS AO CLICAR EM SALVAR*/
    private void desativarCampos(){
        etNome.setEnabled(false);
        etData.setEnabled(false);
        etCpf.setEnabled(false);
        etSenha.setEnabled(false);
        etSenhaConfirmacao.setEnabled(false);
        etEmail.setEnabled(false);
        etCep.setEnabled(false);
        etValorHora.setEnabled(false);
        etResumo.setEnabled(false);
        tvTituloCategoria.setVisibility(View.INVISIBLE);
        tvTituloSubcategoria.setVisibility(View.INVISIBLE);
        spnSubcategoria.setVisibility(View.INVISIBLE);
        spnCategoria.setVisibility(View.INVISIBLE);

    }


    /*ATIVA OS CAMPOS AO CLICAR EM EDITAR*/
    private void ativarCampos(){
        etNome.setEnabled(true);
        etData.setEnabled(true);
        etCpf.setEnabled(true);
        etSenha.setEnabled(true);
        etCep.setEnabled(true);
        etSenhaConfirmacao.setEnabled(true);
        etEmail.setEnabled(true);
        etValorHora.setEnabled(true);
        etResumo.setEnabled(true);
        etNome.requestFocus(View.FOCUS_LEFT);

        tvTituloCategoria.setVisibility(View.VISIBLE);
        tvTituloSubcategoria.setVisibility(View.VISIBLE);
        spnSubcategoria.setVisibility(View.VISIBLE);
        spnCategoria.setVisibility(View.VISIBLE);

    }


    private boolean validar(){
        boolean validado = true;
        if(etNome.getText().toString().isEmpty()){
            etNome.setError("Digite seu nome por favor");
            validado = false;
        }
        if(etNome.getText().length()<=2){
            etNome.setError("O nome deve conter no min. 3 caracteres");
            validado = false;
        }
        if(etEmail.getText().toString().isEmpty()){
            validado = false;
        }
        if(etEmail.getText().length()<9){
            etEmail.setError("O e-mail deve conter no min. 9 caracteres");
            validado = false;
        }
        if(etCpf.getText().toString().isEmpty()){
            validado = false;
        }
        if(etCpf.getText().length()<14){
            etCpf.setError("CPF/CNPJ inválido");
            validado = false;
        }
        if(etSenha.getText().toString().isEmpty()){
            validado = false;
        }
        if(etSenha.getText().length()<8){
            etSenha.setError("A senha deve conter no min. 8 caracteres");
            validado = false;
        }
        if(etData.getText().toString().isEmpty()){
            validado = false;
        }
        if(etData.getText().length()<10){
            etData.setError("Data de nascimento inválida");
            validado = false;
        }

        return validado;
    }

}
