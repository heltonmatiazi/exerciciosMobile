package senac.com.br.prova02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import senac.com.br.prova02.R;

import static android.R.attr.defaultValue;

public class FecharContaActivity extends AppCompatActivity {
    Double valorConta;
    NumberPicker qtdPessoas;
    TextView totalIndividual;
    DecimalFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_conta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent recebendoDouble = this.getIntent();
        valorConta = recebendoDouble.getDoubleExtra("valorConta", defaultValue);

        qtdPessoas = (NumberPicker) findViewById(R.id.numFecharConta);
        qtdPessoas.setMinValue(1);
        qtdPessoas.setMaxValue(10);
        totalIndividual = (TextView) findViewById(R.id.totalIndividual);
        df = new DecimalFormat("0.00");
    }
    public void voltarParaMain(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void encerrarApp(View v){
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void calcularFinal(View v){
        if(qtdPessoas.getValue() >= 1){
            Double valorFinal = valorConta/qtdPessoas.getValue();
            totalIndividual.setText(getString(R.string.valorTotal1)+df.format(valorFinal)+getString(R.string.valorTotal2));
        }else{
            Toast.makeText(this,R.string.erro_no_person,Toast.LENGTH_SHORT).show();
        }
    }
}
