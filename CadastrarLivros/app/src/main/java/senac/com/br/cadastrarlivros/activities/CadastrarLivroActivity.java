package senac.com.br.cadastrarlivros.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senac.com.br.cadastrarlivros.DAO.LivroDao;
import senac.com.br.cadastrarlivros.R;
import senac.com.br.cadastrarlivros.helpers.MyORMLiteHelper;
import senac.com.br.cadastrarlivros.model.Autor;
import senac.com.br.cadastrarlivros.model.Livro;

import static senac.com.br.cadastrarlivros.R.id.parent;

public class CadastrarLivroActivity extends AppCompatActivity {
    Dao<Livro,Integer> livroDao;
    ArrayList<Livro> listLivros;
    ArrayAdapter<Livro>adapterLivro;
    ListView listViewLivro;
    Livro livro;
    EditText etNome,etPaginas;
    Spinner spAutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);
        etNome = (EditText)findViewById(R.id.etNome);
        spAutor = (Spinner) findViewById(R.id.spAutor);
        etPaginas = (EditText) findViewById(R.id.etPaginas);
        listViewLivro = (ListView)findViewById(R.id.listaCadastrados);
        try {
            livroDao = MyORMLiteHelper.getmInstance(this).getLivroDao();
            // listLivros = (ArrayList<Livro>) livroDao.queryForAll();
            adapterLivro = new ArrayAdapter<Livro>(this, android.R.layout.simple_list_item_1, livroDao.queryForAll());
            listViewLivro.setAdapter(adapterLivro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listViewLivro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                livro = adapterLivro.getItem(pos);
                etNome.setText(livro.getTitulo());
                etPaginas.setText(livro.getNumeroPaginas());
                spAutor.setSelection(livro.getAutor());
                Toast.makeText(CadastrarLivroActivity.this, "editando", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        listViewLivro.setOnItemClickListener(cliqueCurto());
    }
    private AdapterView.OnItemClickListener cliqueCurto(){
      return new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              livro = adapterLivro.getItem(i);
              AlertDialog.Builder alerta = new AlertDialog.Builder(CadastrarLivroActivity.this);
              alerta.setTitle("Deletando livro");
              alerta.setMessage("Confirmar exclusão de "+livro.toString());
              alerta.setNegativeButton("Não",null);
              alerta.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      try {
                          livroDao.delete(livro);
                          adapterLivro.remove(livro);
                          livro = null;
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }
              });
              alerta.show();
          }
      };
    };
    public void cadastrarLivro(View v)throws SQLException{
        if(livro == null){
            livro = new Livro();
            livro.setTitulo(etNome.getText().toString());
            livro.setAutor((Autor) spAutor.getSelectedItem());
            livro.setNumeroPaginas(etPaginas.getText().toString());
            Dao.CreateOrUpdateStatus res = livroDao.createOrUpdate(livro);
            if (res.isCreated()) {
                adapterLivro.add(livro);
                Toast.makeText(this, "livro cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } else if (res.isUpdated()) {
                adapterLivro.notifyDataSetChanged();
                Toast.makeText(this, "livro editado com sucesso", Toast.LENGTH_SHORT).show();
            }
            livroDao.createOrUpdate(livro);
        }
        livro = null;
        etNome.setText(" ");
        etPaginas.setText(" ");
        spAutor.setSelection(1);
    }
}
