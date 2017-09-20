package senac.com.br.cidadesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import senac.com.br.cidadesapp.R;
import senac.com.br.cidadesapp.DAO.EstadoBD;
import senac.com.br.cidadesapp.modelos.Estado;

public class NovoEstadoActivity extends AppCompatActivity {

    EstadoBD estadoBD;
    Estado estado;
    ArrayAdapter<Estado> adapterEstado;
    ListView lvEstados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_estado);

        estadoBD = new EstadoBD(this);
        adapterEstado = new ArrayAdapter<Estado>(this,
                                    android.R.layout.simple_list_item_1,
                                    estadoBD.listar());
        lvEstados = (ListView) findViewById(R.id.lvEstados);
        lvEstados.setAdapter(adapterEstado);

        lvEstados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                estado = adapterEstado.getItem(i);
                estadoBD.setEstado(estado);
                boolean res = estadoBD.excluir();
                if(res) {
                    adapterEstado.remove(estado);
                }else{
                    Toast.makeText(NovoEstadoActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }


    public void cadastrar(View view){
        EditText editNome = (EditText) findViewById(R.id.editNomeEstado);
        EditText editSigla = (EditText) findViewById(R.id.editSiglaEstado);

        estado = new Estado();
        estado.setNome(editNome.getText().toString());
        estado.setSigla(editSigla.getText().toString());

        estadoBD.setEstado(estado);
        boolean res = estadoBD.cadastrar();
        if(res){
            Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show();
            estado.setId(estadoBD.getLastInserId());
            adapterEstado.add(estado);
        }else{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }
    }


}
