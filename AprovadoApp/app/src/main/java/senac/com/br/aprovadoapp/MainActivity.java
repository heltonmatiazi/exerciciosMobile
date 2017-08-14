package senac.com.br.aprovadoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    EditText disciplina;
    NumberPicker num1, num2, num3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disciplina = (EditText) findViewById(R.id.disciplina1);
        num1 = (NumberPicker) findViewById(R.id.numberPicker1);
        num2 = (NumberPicker) findViewById(R.id.numberPicker2);
        num3 = (NumberPicker) findViewById(R.id.numberPicker3);
        num1.setMinValue(0);
        num1.setMaxValue(10);
        num1.setValue(5);
        num2.setMinValue(0);
        num2.setMaxValue(10);
        num2.setValue(5);
        num3.setMinValue(0);
        num3.setMaxValue(10);
        num3.setValue(5);
    }
    public void enviar(View v){
        String nomeDisciplina = disciplina.getText().toString();
        int nota1 = num1.getValue();
        int nota2 = num2.getValue();
        int nota3 = num3.getValue();
        Intent it = new Intent(this,disciplina2Activity.class);
        // criando o bundle de parametros <chave,valor>
        it.putExtra("nomeDisciplina",nomeDisciplina);
        it.putExtra("nota1",nota1);
        it.putExtra("nota2",nota2);
        it.putExtra("nota3",nota3);
        startActivity(it);
    }
}
