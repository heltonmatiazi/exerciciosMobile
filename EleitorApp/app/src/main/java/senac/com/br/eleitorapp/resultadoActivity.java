package senac.com.br.eleitorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resultadoActivity extends AppCompatActivity {
    TextView Tvnome,Tvidade,Tvvotar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Tvnome = (TextView) findViewById(R.id.resNome);
        Tvidade = (TextView) findViewById(R.id.resIdade);
        Tvvotar = (TextView) findViewById(R.id.resVotar);
        // resgatando os dados do bundle de parametros
        Bundle param = getIntent().getExtras();
        String nome = param.getString("nome");
        int num = param.getInt("num");

        Tvnome.setText(getString(R.string.nome)+ ": " + nome);
        Tvidade.setText(getString(R.string.idade)+ ": "+num);
        Tvvotar.setText(getString(R.string.podeVotar)+": ");
        if(num>=16){
            Tvvotar.setText(getString(R.string.sim));
        }else{
            Tvvotar.setText(getString(R.string.nao));
        }
    }
    public void retornar(View v){
        finish();
    }

}
