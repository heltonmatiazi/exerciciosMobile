package senac.com.br.myimchelt;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static senac.com.br.myimchelt.R.id.altura;
import static senac.com.br.myimchelt.R.id.peso;



public class MainActivity extends AppCompatActivity {

   private ImageView userImage;
   private final int requestCode = 20;
   private EditText pesoEt;
   private EditText alturaEt;
   private ProgressBar mProgressBar;
   private TextView imcTexto;
   public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userImage = (ImageView) findViewById(R.id.userImage);
        pesoEt = (EditText) findViewById(peso);
        alturaEt = (EditText) findViewById(altura);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        imcTexto = (TextView) findViewById(R.id.imcTexto);
        // precisa disso pra poder rodar a camera no sandbox do emulador
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        float peso = settings.getFloat("peso",0);
        float altura = settings.getFloat("altura",0);
        float imcUser = settings.getFloat("imc",0);
        pesoEt.setText(String.valueOf(peso));
        alturaEt.setText(String.valueOf(altura));
        imcTexto.setText(String.valueOf("Seu imc é: "+imcUser));
        setNotifications();
    }
    public void setNotifications(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
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
        float pesoFornecido = Float.parseFloat(pesoEt.getText().toString());
        float alturaFornecida = Float.parseFloat(alturaEt.getText().toString());
        if(pesoFornecido <= 1 || alturaFornecida <= 1){
            Toast.makeText(this, "O peso ou a altura são inválidos", Toast.LENGTH_SHORT).show();
            return;
        }
        float imcFinal;
        imcFinal = (pesoFornecido/(alturaFornecida*alturaFornecida));
        int maxValue=mProgressBar.getMax();
        int progressValue = Math.round((((imcFinal/15)-1)*100));
        if(imcFinal <= 15){
            mProgressBar.setProgress(1);
        }else if(imcFinal > 15 && imcFinal <30){
           mProgressBar.setProgress(progressValue);
        }else{
           mProgressBar.setProgress(maxValue);
        }
           mProgressBar.setVisibility(View.VISIBLE);
        imcTexto.setText("Seu imc é: "+imcFinal);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("peso",pesoFornecido);
        editor.putFloat("altura",alturaFornecida);
        editor.putFloat("imc",imcFinal);
        editor.commit();
    }
    public void consultarAcademias(View v) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
