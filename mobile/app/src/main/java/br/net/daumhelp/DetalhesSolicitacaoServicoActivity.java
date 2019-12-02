package br.net.daumhelp;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.recursos.Data;
import br.net.daumhelp.recursos.Mascara;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesSolicitacaoServicoActivity extends AppCompatActivity {

    private EditText etData;
    private EditText etHoraInicio;
    private EditText etHoraFim;
    private EditText etDescricao;
    private ImageView ivFoto1;
    private ImageView ivFoto2;
    private ImageView ivFoto3;
    private Button btnFoto1;
    private Button btnFoto2;
    private Button btnFoto3;
    private Button btnConfirmar;
    private String caminhoFoto;
    private Profissional profissionalSolicitado;
    private Cliente clienteLogado;
    private String tokenCliente;
    public static final int CAMERA_REQUEST = 1;
    public static final int CAMERA_REQUEST2 = 2;
    public static final int CAMERA_REQUEST3 = 3;
    private boolean resultadoSolicitacao;
    private boolean resultadoSolicitacaoFoto;
    private Pedido pedidoFeito;
    private File arquivoFoto1;
    private File arquivoFoto2;
    private File arquivoFoto3;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_solicitacao);

        getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        etData = findViewById(R.id.et_data_servico);
        etHoraInicio = findViewById(R.id.et_primeira_hora);
        etHoraFim = findViewById(R.id.et_ultima_hora);
        etDescricao = findViewById(R.id.et_descricao);
        ivFoto1 = findViewById(R.id.iv_servico_1);
        ivFoto2 = findViewById(R.id.iv_servico_2);
        ivFoto3 = findViewById(R.id.iv_servico_3);
        btnFoto1 = findViewById(R.id.btn_image_1);
        btnFoto2 = findViewById(R.id.btn_image_2);
        btnFoto3 = findViewById(R.id.btn_image_3);
        btnConfirmar = findViewById(R.id.btn_confirmar_solicitacao);

        /*MÁSCARAS*/
        Mascara maskData = new Mascara("##/##/####", etData);
        etData.addTextChangedListener(maskData);
        Mascara maskHoraI = new Mascara("##:##", etHoraInicio);
        etHoraInicio.addTextChangedListener(maskHoraI);
        Mascara maskHoraF = new Mascara("##:##", etHoraFim);
        etHoraFim.addTextChangedListener(maskHoraF);


        Intent intent = getIntent();
        /*PEGANDO O TOKEN*/
        if (intent.getSerializableExtra("tokenCliente") != null) {
            tokenCliente = (String) intent.getSerializableExtra("tokenCliente");
        }

        if (intent.getSerializableExtra("profissionalSolicitado") != null && intent.getSerializableExtra("clienteLogado") != null) {
            
            profissionalSolicitado = (Profissional) intent.getSerializableExtra("profissionalSolicitado");
            clienteLogado = (Cliente) intent.getSerializableExtra("clienteLogado");

            /*SOLICITAÇÃO DO PROFISSIONAL*/
            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final ProgressDialog progressDialog = new ProgressDialog(DetalhesSolicitacaoServicoActivity.this);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.layout_progressbar);

                    final Pedido pedido = new Pedido();
                    pedido.setCliente(clienteLogado);
                    pedido.setProfissional(profissionalSolicitado);
                    pedido.setDescricao(etDescricao.getText().toString());
                    pedido.setHorarioInicial(etHoraInicio.getText().toString());
                    pedido.setHorarioFinal(etHoraFim.getText().toString());

                    if(validar()){

                        Date data = Data.brStringToDate(etData.getText().toString());
                        String dataFormatada = Data.dataToString(data);
                        pedido.setDataServico(dataFormatada);

                            RequestBody fbody1 = RequestBody.create(MediaType.parse("image/png"), arquivoFoto1);
                            final MultipartBody.Part multipartBody1 = MultipartBody.Part.createFormData("img1", arquivoFoto1.getName(), fbody1);

                            RequestBody fbody2 = RequestBody.create(MediaType.parse("image/png"), arquivoFoto2);
                            final MultipartBody.Part multipartBody2 = MultipartBody.Part.createFormData("img2", arquivoFoto2.getName(), fbody2);

                            RequestBody fbody3 = RequestBody.create(MediaType.parse("image/png"), arquivoFoto3);
                            final MultipartBody.Part multipartBody3 = MultipartBody.Part.createFormData("img3", arquivoFoto3.getName(), fbody3);


                        Call<Pedido> call = new RetroFitConfig().getPedidoService().solicitarProfissional(tokenCliente, pedido);
                        call.enqueue(new Callback<Pedido>() {



                            @Override
                            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                                pedidoFeito = response.body();
                                id = pedidoFeito.getIdPedido();

                                RequestBody idPedidoBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

                                Call<Pedido> call2 = new RetroFitConfig().getFotoService().cadastrarFotoSolicitacao(tokenCliente, idPedidoBody, multipartBody1, multipartBody2, multipartBody3);
                                call2.enqueue(new Callback<Pedido>() {

                                    @Override
                                    public void onResponse(Call<Pedido> call2, Response<Pedido> response) {
                                        progressDialog.dismiss();
                                        response.body();
                                        Toast.makeText(DetalhesSolicitacaoServicoActivity.this, "Aguarde a resposta do profissional! =D", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<Pedido> call2, Throwable t) {
                                        Log.i("Retrofit FOTO", t.getMessage());
                                        Toast.makeText(DetalhesSolicitacaoServicoActivity.this, "Ocorreu um erro ao solicitar esse profissional |||=( \nTente mais tarde", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<Pedido> call, Throwable t) {
                                Log.i("Retrofit PEDIDO", t.getMessage());
                                Toast.makeText(DetalhesSolicitacaoServicoActivity.this, "Ocorreu um erro ao solicitar esse profissional =( \nTente mais tarde", Toast.LENGTH_SHORT).show();
                            }
                        });

                        }
                }
            });

        }

        /*INTENT PARA ABRIR A CÂMERA*/
        btnFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String nomeFoto = "/IMG_" + System.currentTimeMillis() + ".jpg";
                caminhoFoto = getExternalFilesDir(null) + nomeFoto;

                arquivoFoto1 = new File(caminhoFoto);
                Uri fotoUri = FileProvider.getUriForFile(DetalhesSolicitacaoServicoActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto1);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intentCamera, CAMERA_REQUEST);
            }
        });

        /*INTENT PARA ABRIR A CÂMERA*/
        btnFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String nomeFoto = "/IMG_" + System.currentTimeMillis() + ".jpg";
                caminhoFoto = getExternalFilesDir(null) + nomeFoto;

                arquivoFoto2 = new File(caminhoFoto);
                Uri fotoUri = FileProvider.getUriForFile(DetalhesSolicitacaoServicoActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto2);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intentCamera, CAMERA_REQUEST2);
            }
        });

        /*INTENT PARA ABRIR A CÂMERA*/
        btnFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String nomeFoto = "/IMG_" + System.currentTimeMillis() + ".jpg";
                caminhoFoto = getExternalFilesDir(null) + nomeFoto;

                arquivoFoto3 = new File(caminhoFoto);
                Uri fotoUri = FileProvider.getUriForFile(DetalhesSolicitacaoServicoActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto3);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intentCamera, CAMERA_REQUEST3);
            }
        });


    }

    /*TRATAMENTO DE ERRO CASO NÃO SEJA SELECIONADA NENHUMA FOTO*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            if (requestCode == CAMERA_REQUEST){

                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                ivFoto1.setImageBitmap(bitmapReduzido);

            }else if (requestCode == CAMERA_REQUEST2){

                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                ivFoto2.setImageBitmap(bitmapReduzido);

            }else if (requestCode == CAMERA_REQUEST3){

                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                ivFoto3.setImageBitmap(bitmapReduzido);

            }

     }

     /*VALIDAÇÃO DOS CAMPOS*/
    private boolean validar(){
        boolean validado = true;

        if(etDescricao.getText().toString().isEmpty()){
            etDescricao.setError("Dá o papo");
            validado = false;
        }
        if(etHoraFim.getText().toString().isEmpty()){
            etHoraFim.setError("Digite a hora que o profissional vai embora da sua casa o se não ele não vai morar aí e você não vai poder reclamar!");
            validado = false;
        }
        if(etHoraInicio.getText().toString().isEmpty()){
            etHoraInicio.setError("Digite a hora que o profissional vai lá na sua casa fazer o serviço se não ele não vai saber aí ele vai chegar aí 1 da manhã e você não vai poder reclamar!");
            validado = false;
        }
        if(etData.getText().toString().isEmpty()){
            etData.setError("Digite uma data");
            validado = false;
        }

        return validado;
    }


}
