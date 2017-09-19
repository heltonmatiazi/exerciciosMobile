package senac.com.br.prova02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import java.util.ArrayList;

import senac.com.br.prova02.R;
import senac.com.br.prova02.helpers.AdapterListView;
import senac.com.br.prova02.helpers.ItemListView;
import senac.com.br.prova02.model.Pedido;
import senac.com.br.prova02.model.Produto;

/**
 * Created by helton on 19/09/2017.
 */

public class NovoProdutoActivity extends AppCompatActivity {

    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;
    private Spinner spinner;
    NumberPicker numberPicker;
    Integer qtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listViewCustomizado);
        numberPicker= (NumberPicker) findViewById(R.id.produtoPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        itens = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.ProdutoSpinner);

        ArrayList<Pedido> lista = new ArrayList<>();

        Produto prod1 = new Produto("heineken", (float) 5.90);
        Produto prod2 = new Produto("stella", (float) 5.20);
        Produto prod3 = new Produto("budweiser", (float) 4.90);

        Pedido item_1 = new Pedido(  prod1);
        Pedido item_2 = new Pedido( prod2);
        Pedido item_3 = new Pedido( prod3);

        lista.add(item_1);
        lista.add(item_2);
        lista.add(item_3);

        ArrayAdapter<Pedido> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    public void concluir(View view) {
        qtd = Integer.parseInt(String.valueOf(numberPicker.getValue()));
        Pedido p = (Pedido) spinner.getSelectedItem();
        p.setQuantidade(qtd);
        p.setSubtotal(qtd * p.getProduto().getValor());
        Intent it = new Intent();
        it.putExtra("PEDIDO", p);
        setResult(RESULT_OK, it);
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}
