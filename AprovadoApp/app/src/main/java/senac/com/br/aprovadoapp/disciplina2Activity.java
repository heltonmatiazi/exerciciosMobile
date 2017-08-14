package senac.com.br.aprovadoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class disciplina2Activity extends AppCompatActivity {
    EditText disciplina;
    NumberPicker num1, num2, num3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina2);
        disciplina = (EditText) findViewById(R.id.disciplina1);
        num1 = (NumberPicker) findViewById(R.id.nP1);
        num2 = (NumberPicker) findViewById(R.id.nP2);
        num3 = (NumberPicker) findViewById(R.id.nP3);
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
        Bundle param = getIntent().getExtras();
        String nomeDisciplina1 = param.getString("nomeDisciplina");
        int nota1disciplina1 = param.getInt("nota1");
        int nota2disciplina1 = param.getInt("nota2");
        int nota3disciplina1 = param.getInt("nota3");

        String nomeDisciplina = disciplina.getText().toString();
        int nota1 = num1.getValue();
        int nota2 = num2.getValue();
        int nota3 = num3.getValue();
        Intent it = new Intent(this,resultadoActivity.class);
        // criando o bundle de parametros <chave,valor>
        it.putExtra("nomedisciplina2",nomeDisciplina);
        it.putExtra("nota1disciplina2",nota1);
        it.putExtra("nota2disciplina2",nota2);
        it.putExtra("nota3disciplina2",nota3);
        it.putExtra("nomedisciplina1",nomeDisciplina1);
        it.putExtra("nota1discipina1",nota1disciplina1);
        it.putExtra("nota2disciplina1",nota2disciplina1);
        it.putExtra("nota3disciplina1",nota3disciplina1);

        startActivity(it);
    }
}
