package senac.com.br.precomuitojusto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView nome, valor, qtdParacela, juros;
    CheckBox comParcela;
    LinearLayout layoutDinamico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (TextView) findViewById(R.id.nome);
        valor = (TextView) findViewById(R.id.valor);
        qtdParacela = (TextView) findViewById(R.id.parcela);
        juros = (TextView) findViewById(R.id.juros);
        comParcela = (CheckBox) findViewById(R.id.checkBox);
        layoutDinamico = (LinearLayout) findViewById(R.id.layoutResultado);
    }
    public void calcular(View view) {
        Float jurosTotal = ( Float.valueOf(juros.getText().toString() )/ 100 ) * Float.valueOf(valor.getText().toString()) ;
        Float total;
        TextView totalText  = new TextView(this);
        if(comParcela.isChecked()){
            total = jurosTotal - (5 /100);
        }else{
            total =  jurosTotal + Float.valueOf(valor.getText().toString());
        }
        total =  (total / Integer.parseInt( qtdParacela.getText().toString() ) );
        String result = "Produto: "+ nome.getText().toString()+"\n Valor Inicial: "+valor.getText().toString()+
                "\n Valor Parcela: "+(total / Integer.parseInt( qtdParacela.getText().toString() ) )+"" +
                "\nValor Total: "+total+ "\nTotal Com juros: "+jurosTotal;
        totalText.setText(result);
        layoutDinamico.addView(totalText);
    }
    public void limparDados(View view) {
        nome.setText("");
        valor.setText("");
        qtdParacela.setText("");
        juros.setText("");
        layoutDinamico.removeAllViews();
    }
}
