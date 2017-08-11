package senac.com.br.calculadoracomhistorico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView num1, num2, hist;
    ArrayList<String> historico;
    LinearLayout layoutDinamico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (TextView) findViewById(R.id.numero1);
        num2 = (TextView) findViewById(R.id.numero2);
        layoutDinamico = (LinearLayout) findViewById(R.id.layoutResultado);
        historico = new ArrayList<>();
    }
    public void somar (View v){
        try{
            String helper = null;
            Integer soma = Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString());
            helper = num1.getText().toString() + " + " + num2.getText().toString() + " = " + soma.toString();
            historico.add(helper);
            limpar();
            for (int i = 0; i < historico.size(); i++) {
                TextView tv = new TextView(this);
                tv.setText(historico.get(i).toString());
                layoutDinamico.addView(tv);
            }
        }catch(Exception e){
            Toast.makeText(this, "Não foi possível processar o calculo solicitado.", Toast.LENGTH_SHORT).show();
        }
    }
    public void Multiplicar(View view) {
        try {
            String helper = null;
            Integer soma = Integer.parseInt(num1.getText().toString()) * Integer.parseInt(num2.getText().toString());
            helper = num1.getText().toString() + " * " + num2.getText().toString() + " = " + soma.toString();
            historico.add(helper);
            limpar();
            for (int i = 0; i < historico.size(); i++) {
                TextView tv = new TextView(this);
                tv.setText(historico.get(i).toString());
                layoutDinamico.addView(tv);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível processar o calculo solicitado.", Toast.LENGTH_SHORT).show();
        }
    }
    public void Diminuir(View view) {
        try {
            String helper = null;
            Integer soma = Integer.parseInt(num1.getText().toString()) - Integer.parseInt(num2.getText().toString());
            helper = num1.getText().toString() + " - " + num2.getText().toString() + " = " + soma.toString();
            historico.add(helper);
            limpar();
            for (int i = 0; i < historico.size(); i++) {
                TextView tv = new TextView(this);
                tv.setText(historico.get(i).toString());
                layoutDinamico.addView(tv);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível processar o calculo solicitado.", Toast.LENGTH_SHORT).show();
        }
    }
    public void Dividir(View view) {
        try {
            String helper = null;
            Integer soma = Integer.parseInt(num1.getText().toString()) / Integer.parseInt(num2.getText().toString());
            helper = num1.getText().toString() + " / " + num2.getText().toString() + " = " + soma.toString();
            historico.add(helper);
            limpar();
            for (int i = 0; i < historico.size(); i++) {
                TextView tv = new TextView(this);
                tv.setText(historico.get(i).toString());
                layoutDinamico.addView(tv);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível processar o calculo solicitado.", Toast.LENGTH_SHORT).show();
        }
    }
    private void limpar() {
        layoutDinamico.removeAllViews();
        num1.setText("");
        num2.setText("");
    }
}
