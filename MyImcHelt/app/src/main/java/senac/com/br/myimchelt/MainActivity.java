package senac.com.br.myimchelt;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static senac.com.br.myimchelt.R.style.textView;


public class MainActivity extends AppCompatActivity {

   private ImageView userImage;
   private final int requestCode = 20;
   private EditText pesoEt;
   private EditText alturaEt;
   private ProgressBar mProgressBar;
   private int progressStatus = 0;
   private TextView imcTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userImage = (ImageView) findViewById(R.id.userImage);
        pesoEt = (EditText) findViewById(R.id.peso);
        alturaEt = (EditText) findViewById(R.id.altura);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        imcTexto = (TextView) findViewById(R.id.imcTexto);
        // precisa disso pra poder rodar a camera no sandbox do emulador
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());



    }

    public void novaImagem(View v) {
        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoCaptureIntent, requestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            userImage.setImageBitmap(bitmap);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void calcularImc(View v) {
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        float peso = getPrefs.getFloat("peso", 0);
        float altura = getPrefs.getFloat("altura",0);
        float imc = getPrefs.getFloat("imc",0);


        float pesoFornecido = Float.parseFloat(pesoEt.getText().toString());
        float alturaFornecida = Float.parseFloat(alturaEt.getText().toString());
        if(pesoFornecido <= 1 || alturaFornecida <= 1){
            Toast.makeText(this, "O peso ou a altura são inválidos", Toast.LENGTH_SHORT).show();
        }
        float imcFinal;
        imcFinal = (pesoFornecido/(alturaFornecida*alturaFornecida));

        Toast.makeText(this,"imcfinal: "+imcFinal,Toast.LENGTH_SHORT).show();

        int maxValue=mProgressBar.getMax();


        if(imcFinal <= 15){
            mProgressBar.setProgress(1);
        }else if(imcFinal > 15 && imcFinal <30){
           mProgressBar.setProgress(50);
            //delayedUpdate(imcFinal);
        }else{
           mProgressBar.setProgress(maxValue);
        }
           mProgressBar.setVisibility(View.VISIBLE);
        imcTexto.setText("Seu imc é: "+imcFinal);

        SharedPreferences.Editor editor = getPrefs.edit();
        editor.putFloat("peso",pesoFornecido);
        editor.putFloat("altura",alturaFornecida);
        editor.putFloat("imc",imcFinal);
        editor.apply();
    }

    public void consultarAcademias(View v) {
        Intent i = new Intent(this, PesquisarAcademia.class);
        startActivity(i);
    }



}
