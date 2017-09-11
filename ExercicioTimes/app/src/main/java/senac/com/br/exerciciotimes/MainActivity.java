package senac.com.br.exerciciotimes;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvNome;
    TextView tvIdade;
    ListView lvTime;
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<Pessoa> pessoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvIdade = (TextView) findViewById(R.id.tvIdade);
       renderzarSpinner();
        createListView();

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
    }
    public void createListView(){
        pessoas = new ArrayList<Pessoa>();
        Time time1 = new Time("Corinthians",1);
        Pessoa pessoa1 = new Pessoa("zé",15,time1);

        listView.setAdapter(adapterListView);
        listView.setCacheColorHint(Color.TRANSPARENT);
    }
    public void enviar(){

    }

}
