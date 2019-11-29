package br.net.daumhelp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroFotoActivity extends AppCompatActivity {

    private Button btnSemFoto;
    private ImageButton ibGaleria;
    private ImageButton ibCamera;
    private ImageButton ibConfirmar;
    private String caminhoFoto;
    private ImageView ivFoto;
    private File arquivoFoto;
    public static final int GALERIA_REQUEST = 1;
    public static final int CAMERA_REQUEST = 2;
    private String tokenProfissional;
    private String tokenCliente;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));

        btnSemFoto = findViewById(R.id.btn_sem_imagem);
        ibGaleria = findViewById(R.id.ib_galeria);
        ibCamera = findViewById(R.id.ib_camera);
        ivFoto = findViewById(R.id.iv_foto);
        btnSemFoto = findViewById(R.id.btn_sem_imagem);
        ibConfirmar = findViewById(R.id.btn_confirmar_foto);


        /*INTENT PARA ABRIR A GALERIA*/
        ibGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);
                intentGaleria.setType("image/*");
                startActivityForResult(intentGaleria, GALERIA_REQUEST);
            }
        });

        /*INTENT PARA ABRIR A CÂMERA*/
        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String nomeFoto = "/IMG_" + System.currentTimeMillis() + ".png";
                caminhoFoto = getExternalFilesDir(null) + nomeFoto;

                arquivoFoto = new File(caminhoFoto);
                Uri fotoUri = FileProvider.getUriForFile(CadastroFotoActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intentCamera, CAMERA_REQUEST);
            }
        });

        final Intent intent = getIntent();


        if (intent.getSerializableExtra("tokenCliente") != null) {
           tokenCliente = (String) intent.getSerializableExtra("tokenCliente");
        }

        if (intent.getSerializableExtra("tokenProfissional") != null) {
            tokenProfissional = (String) intent.getSerializableExtra("tokenProfissional");
            Log.d("token" , tokenProfissional);
        }

        if (intent.getSerializableExtra("cliente") != null) {

            final Cliente cliente = (Cliente) intent.getSerializableExtra("cliente");


            ibConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ProgressDialog progressDialog = new ProgressDialog(CadastroFotoActivity.this);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.layout_progressbar);

                    final RequestBody fbody = RequestBody.create(MediaType.parse("image/png"), arquivoFoto);
                    MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("img", arquivoFoto.getName(), fbody);
                    RequestBody idClienteBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(cliente.getIdCliente()));

                    Call<Cliente> call = new RetroFitConfig().getFotoService().cadastrarFotoCli(tokenCliente, idClienteBody, multipartBody);
                    call.enqueue(new Callback<Cliente>() {
                        @Override
                        public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                            progressDialog.dismiss();
                            response.body();
                            Intent intent = new Intent(CadastroFotoActivity.this, MenuClienteActivity.class);
                            intent.putExtra("cliente", cliente);
                            intent.putExtra("tokenCliente", tokenCliente);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Cliente> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.i("FOTOCLICADASTRO", t.getMessage());

                        }
                    });


                }
            });

            btnSemFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CadastroFotoActivity.this, MenuClienteActivity.class);
                    intent.putExtra("cliente", cliente);
                    intent.putExtra("tokenCliente", tokenCliente);
                    startActivity(intent);
                    Toast.makeText(CadastroFotoActivity.this, "Foto cadastrada :)", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }
        if (intent.getSerializableExtra("profissional") != null) {


            final Profissional profissional = (Profissional) intent.getSerializableExtra("profissional");


            ibConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final RequestBody fbody = RequestBody.create(MediaType.parse("image/png"), arquivoFoto);
                    MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("img", arquivoFoto.getName(), fbody);
                    RequestBody idProBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(profissional.getIdProfissional()));

                    Call<Profissional> call2 = new RetroFitConfig().getFotoService().cadastrarFotoPro(tokenProfissional, idProBody, multipartBody);
                    call2.enqueue(new Callback<Profissional>() {
                        @Override
                        public void onResponse(Call<Profissional> call2, Response<Profissional> response) {
                            response.body();
                            Intent intent = new Intent(CadastroFotoActivity.this, MenuActivity.class);
                            intent.putExtra("profissional", profissional);
                            intent.putExtra("tokenProfissional", tokenProfissional);
                            Toast.makeText(CadastroFotoActivity.this, "Foto cadastrada :)", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Profissional> call, Throwable t) {


                        }
                    });



                }
            });

            btnSemFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= new Intent(CadastroFotoActivity.this, MenuActivity.class);
                    intent.putExtra("profissional", profissional);
                    intent.putExtra("tokenProfissional", tokenProfissional);
                    finish();
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            try {
                /*tratamento para não dar erro se o usuário não escolher nenhuma imagem*/
                if (requestCode == GALERIA_REQUEST) {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ivFoto.setImageBitmap(bitmap);
                }
                if (requestCode == CAMERA_REQUEST){

//                    Matrix matrix = new Matrix();
//                    matrix.postRotate(90);
                    Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                    Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                    ivFoto.setImageBitmap(bitmapReduzido);

                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }






    }

}