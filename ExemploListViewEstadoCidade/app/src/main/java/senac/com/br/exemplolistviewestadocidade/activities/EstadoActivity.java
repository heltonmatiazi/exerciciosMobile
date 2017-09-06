package senac.com.br.exemplolistviewestadocidade.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import senac.com.br.exemplolistviewestadocidade.R;
import senac.com.br.exemplolistviewestadocidade.model.Estado;

public class EstadoActivity extends AppCompatActivity {
    Estado estado = null;
    ListView lvEstados;
    ArrayList<Estado> listEstados;
    ArrayAdapter<Estado> adapterEstados;
    EditText editNome,editSigla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
        editNome = (EditText) findViewById(R.id.nomeEstado);
        editSigla = (EditText) findViewById(R.id.siglaEstado);
        iniciarListVIew();
    }
    public void iniciarListVIew(){
        listEstados = new ArrayList<Estado>();
        adapterEstados = new ArrayAdapter<Estado>(this, android.R.layout.simple_list_item_1,listEstados);
        lvEstados = (ListView) findViewById(R.id.listViewEstados);
        lvEstados.setAdapter(adapterEstados);

        lvEstados.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l){
                estado = adapterEstados.getItem(i);
                editNome.setText(estado.getNome());
                editSigla.setText(estado.getSigla());
            }
        });
        lvEstados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView,View view, int i, long l){
                estado = adapterEstados.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(EstadoActivity.this);
                alerta.setTitle("Excluindo estado");
                alerta.setMessage("Deseja excluir o estado " + estado.getNome() + " ?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setNegativeButton("Não",null);
                alerta.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterEstados.remove(estado);
                        estado = null;
                    }
                });
                alerta.show();
                return true;
            };
        });
    }
    public void salvar(View v){
        if(estado == null){
            estado = new Estado();
            estado.setNome(editNome.getText().toString());
            estado.setSigla(editSigla.getText().toString());
            adapterEstados.add(estado);
        }else{
            estado.setNome(editNome.getText().toString());
            estado.setSigla(editSigla.getText().toString());
            adapterEstados.notifyDataSetChanged();
        }
        estado = null;
        editNome.setText(" ");
        editSigla.setText(" ");
    }
    public void onBackPressed(){
        Intent it = new Intent();
        it.putExtra("lista",listEstados);
        setResult(RESULT_OK,it);
        finish();
    }
}
