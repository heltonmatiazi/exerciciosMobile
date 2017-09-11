package senac.com.br.exerciciotimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("nome");
            int idade = extras.getInt("idade");
            String time = extras.getString("time");
            if(name != ""){
            }
        }
    }
}
