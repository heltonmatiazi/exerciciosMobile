package florianopolis.senac.renato.exemplobancoormlite;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;

import static florianopolis.senac.renato.exemplobancoormlite.R.id.editNome;
import static florianopolis.senac.renato.exemplobancoormlite.R.id.etValor;
import static florianopolis.senac.renato.exemplobancoormlite.R.id.lvCategorias;
import static florianopolis.senac.renato.exemplobancoormlite.R.id.spProdutos;
import static java.lang.Double.parseDouble;

@DatabaseTable(tableName = "Produtos")
public class ProdutosActivity extends AppCompatActivity {
    EditText etNome;
    EditText etValor;
    Spinner spProdutos;
    ListView lvProdutos;
    Dao<Produto,Integer> produtoDao;
    Dao<Categoria,Integer>categoriaDao;
    ArrayList<Produto> listProduto;
    ArrayAdapter<Produto> adapterProduto;
    Produto produto = null;
    ArrayList<Categoria> listCategoria;
    ArrayAdapter<Categoria> adapterCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        etNome = (EditText) findViewById(R.id.etNome);
        etValor = (EditText) findViewById(R.id.etValor);
        spProdutos = (Spinner) findViewById(R.id.spProdutos);
        lvProdutos = (ListView) findViewById(R.id.lvProdutos);




        try {
            produtoDao = MyORMLiteHelper.getInstance(this).getProdutoDao();
            categoriaDao = MyORMLiteHelper.getInstance(this).getCategoriaDao();
            adapterProduto = new ArrayAdapter<Produto>(this,
                    android.R.layout.simple_list_item_1,
                    produtoDao.queryForAll());
            lvProdutos.setAdapter(adapterProduto);
            try {
                listCategoria = (ArrayList<Categoria>) categoriaDao.queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            adapterCategoria = new ArrayAdapter<Categoria>(ProdutosActivity.this,android.R.layout.simple_spinner_item,listCategoria);
            adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spProdutos.setAdapter(adapterCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProduto.getItem(position);
                etNome.setText(produto.getNome());
                //produto.setValor(parseDouble(etValor.getText().toString()));
                etValor.setText("");
                Toast.makeText(ProdutosActivity.this, "Editando "+produto.toString(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        //Clique curto no listview
        lvProdutos.setOnItemClickListener(cliqueCurto());
    }
    private AdapterView.OnItemClickListener cliqueCurto(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProduto.getItem(position);
                AlertDialog.Builder alerta= new AlertDialog.Builder(ProdutosActivity.this);
                alerta.setTitle("Deletando categoria");
                alerta.setMessage("Confirmar exclusão de " + produto.toString());
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            produtoDao.delete(produto);
                            adapterProduto.remove(produto);
                            produto = null;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alerta.show();
            }
        };
    }
    public void salvar(View v) throws SQLException {

        if(produto==null)
            produto = new Produto();

        produto.setNome(etNome.getText().toString());
        produto.setValor(parseDouble(etValor.getText().toString()));
        produto.setCategoria((Categoria)spProdutos.getSelectedItem());
        Dao.CreateOrUpdateStatus res = produtoDao.createOrUpdate(produto);

        if(res.isCreated()) {
            adapterProduto.add(produto);
            Toast.makeText(this, "Cadastrado", Toast.LENGTH_SHORT).show();
        }else {
            adapterProduto.notifyDataSetChanged();
            Toast.makeText(this, "Editado", Toast.LENGTH_SHORT).show();
        }

        produto = null;
        etNome.setText("");
        etValor.setText("");
    }
}
