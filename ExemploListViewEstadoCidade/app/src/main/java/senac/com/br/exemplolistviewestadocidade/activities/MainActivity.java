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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import senac.com.br.exemplolistviewestadocidade.R;
import senac.com.br.exemplolistviewestadocidade.model.Cidade;
import senac.com.br.exemplolistviewestadocidade.model.Estado;

public class MainActivity extends AppCompatActivity {
    Cidade cidade = null;
    ListView lvCidades;
    ArrayList<Cidade> listCidades;
    ArrayAdapter<Cidade> adapterCidades;
    EditText editNome,editHabitantes;
    Spinner spEstados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spEstados = (Spinner) findViewById(R.id.spinnerEstados);
        editNome = (EditText) findViewById(R.id.editNome);
        editHabitantes = (EditText) findViewById(R.id.editHabitantes);
        iniciarListView();
    }

    public void iniciarListView(){
        listCidades = new ArrayList<Cidade>();
        adapterCidades = new ArrayAdapter<Cidade>(this,android.R.layout.simple_list_item_1,listCidades);
        lvCidades = (ListView) findViewById(R.id.listViewCidades);
        lvCidades.setAdapter(adapterCidades);
        lvCidades.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int i, long l){
                cidade = adapterCidades.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Alerta");
                alerta.setMessage("Deseja editar a  cidade " + cidade.getNome() + " ?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editNome.setText(cidade.getNome());
                        editHabitantes.setText(cidade.getHabitantes());
                    }
                });
                editNome.setText(cidade.getNome());
                editHabitantes.setText(cidade.getHabitantes());
            }
        });
        lvCidades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView,View view, int i, long l) {
                cidade = adapterCidades.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Excluindo estado");
                alerta.setMessage("Deseja excluir a cidade " + cidade.getNome() + " ?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterCidades.remove(cidade);
                        cidade = null;
                    }
                });
                alerta.show();
                return true;
            };
        });
    }
    public void gerenciarEstados(View v){
        Intent it = new Intent(this, EstadoActivity.class);
        startActivityForResult(it, RESULT_FIRST_USER);
    }
    public void salvarCidade(View v){
        if(cidade == null){
            cidade = new Cidade();
            if(editNome.getText().toString() != null || editNome.getText().toString() != ""){
                cidade.setNome(editNome.getText().toString());
            }else{
                Toast.makeText(this, "nome incorreto", Toast.LENGTH_SHORT).show();
            }
            if(editHabitantes.getText().toString() != null){
                cidade.setHabitantes(editHabitantes.getText().toString());
            }else{
                Toast.makeText(this, "numero de habitantes incorreto ou inválido", Toast.LENGTH_SHORT).show();
            }
            adapterCidades.add(cidade);
        }else{
            cidade.setNome(editNome.getText().toString());
            cidade.setHabitantes(editHabitantes.getText().toString());
            adapterCidades.notifyDataSetChanged();
        }
        cidade = null;
        editNome.setText(" ");
        editHabitantes.setText(" ");
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bundle param = data.getExtras();
        ArrayList<Estado> lista = (ArrayList<Estado>) param.getSerializable("lista");

        Toast.makeText(this,""+lista.size(), Toast.LENGTH_SHORT).show();
        ArrayAdapter<Estado> dataAdapter = new ArrayAdapter<Estado>(this,
                android.R.layout.simple_spinner_item, lista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstados.setAdapter(dataAdapter);
    }
}
