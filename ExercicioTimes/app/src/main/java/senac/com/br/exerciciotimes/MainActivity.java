package senac.com.br.exerciciotimes;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    TextView tvNome;
    TextView tvIdade;
    ListView lvTime;
    String itemStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvIdade = (TextView) findViewById(R.id.tvIdade);
       renderzarSpinner();
    }
    public void renderzarSpinner() {
        ArrayList<Time> list=new ArrayList<>();
        list.add(new Time("Corinthians",R.drawable.corinthians));
        list.add(new Time("Cruzeiro",R.drawable.cruzeiro));
        list.add(new Time("Flamengo",R.drawable.flamengo));
        list.add(new Time("Santos",R.drawable.santos));
        list.add(new Time("São Paulo",R.drawable.saopaulo));
        Spinner sp=(Spinner)findViewById(R.id.spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.row,R.id.txt,list);
        sp.setAdapter(adapter);
        String itemStr = sp.getSelectedItem().toString();
    }

    public void enviar(){
        Intent i = new Intent(getApplicationContext(), Resultado.class);
        i.putExtra("nome",tvNome.getText().toString());
        i.putExtra("idade",parseInt(tvIdade.getText().toString()));
        i.putExtra("Time",itemStr);
        startActivity(i);
    }

}
