package senac.com.br.precojusto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText nome,valor,qtd,juros;
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (EditText) findViewById(R.id.produto);
        valor = (EditText) findViewById(R.id.valor);
        qtd = (EditText) findViewById(R.id.parcelas);
        juros = (EditText) findViewById(R.id.juros);
        resultado = (TextView) findViewById(R.id.resultado);
    }
    public void calcular(View v){
        Double totalJuros = (Double.parseDouble(valor.getText().toString())/100)*(Double.parseDouble(juros.getText().toString()));
        Double valorParcelas = ((Double.parseDouble(valor.getText().toString())) / (Double.parseDouble(qtd.getText().toString())))+ totalJuros;
        Double valorTotal = Double.parseDouble(valor.getText().toString()) + totalJuros;
        resultado.setText("Produto: " + nome.getText() + "\n" +
                "Valor Inicial: " + "R$ " + valor.getText() + "\n" +
                "Valor das Parcelas: "  + "R$ "+  valorParcelas + "\n" +
                "Valor total: " +"R$ "+ valorTotal + "\n" +
                "Total de juros: " + "R$ "+ totalJuros);
    }
    public void apagar(View v){
        nome.setText(" ");
        valor.setText(" ");
        qtd.setText(" ");
        juros.setText(" ");
        resultado.setText(" ");
    }
}
