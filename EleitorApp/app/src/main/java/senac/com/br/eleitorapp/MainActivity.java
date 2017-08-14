package senac.com.br.eleitorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    NumberPicker num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // o unico jeito dessa plataforma ser mais improdutiva é se reescreverem ela em C
        nome = (EditText) findViewById(R.id.nome);
        num = (NumberPicker) findViewById(R.id.num);

        /* configurando o numberpicker, que é a mesma coisa que bater duas pedras uma na outra pra
        fazer faisca*/
        num.setMinValue(0);
        num.setMaxValue(100);
        /* se o setValue for instanciada antes do minValue da erro pq o androidStudio finge que é OO
        mas na verdade é estruturado */
        num.setValue(20);
    }
    public void enviar(View v){
        String nom = nome.getText().toString();
        int idade = num.getValue();
        // instanciando a activity com o resultado
        Intent it = new Intent(this,resultadoActivity.class);
        // criando o bundle de parametros <chave,valor>
        it.putExtra("nome",nom);
        it.putExtra("num",idade);
        // invocando a activity
        startActivity(it);
    }
}
