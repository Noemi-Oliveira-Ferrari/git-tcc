package br.net.daumhelp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CadastroFotoActivity extends AppCompatActivity {

    private Button btnSemFoto;
    private ImageButton ibGaleria;
    private ImageButton ibCamera;
    private String caminhoFoto;
    private ImageView ivFoto;
    public static final int GALERIA_REQUEST = 1;
    public static final int CAMERA_REQUEST = 2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        btnSemFoto = findViewById(R.id.btn_sem_imagem);
        ibGaleria = findViewById(R.id.ib_galeria);
        ibCamera = findViewById(R.id.ib_camera);
        ivFoto = findViewById(R.id.iv_foto);


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
                String nomeFoto = "/IMG_" + System.currentTimeMillis() + ".jpg";
                caminhoFoto = getExternalFilesDir(null) + nomeFoto;

                File arquivoFoto = new File(caminhoFoto);
                Uri fotoUri = FileProvider.getUriForFile(CadastroFotoActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intentCamera, CAMERA_REQUEST);
            }
        });


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
