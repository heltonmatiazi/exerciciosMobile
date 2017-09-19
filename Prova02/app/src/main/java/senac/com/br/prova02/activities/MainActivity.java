package senac.com.br.prova02.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import senac.com.br.prova02.R;
import senac.com.br.prova02.helpers.AdapterListView;
import senac.com.br.prova02.model.Pedido;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Pedido pedido;
    AdapterListView adapterListView;
    ArrayList<Pedido> itens = new ArrayList<>();
    TextView tvDescricao, tvValor;
    double valorTotal = 0.0;
    Bundle param;
    DecimalFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewCustomizado);
        tvDescricao = (TextView) findViewById(R.id.totalPagar);
        tvValor = (TextView) findViewById(R.id.valor);
        df = new DecimalFormat("0.00");
        createListView();
    }
    private void createListView(){
        if( itens != null ) {
            adapterListView = new AdapterListView(this, itens);
            listView.setAdapter(adapterListView);
            listView.setCacheColorHint(Color.TRANSPARENT);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                    pedido   =  (Pedido) adapterListView.getItem(i);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setTitle(R.string.excluirPedido);
                    alerta.setMessage(R.string.desejaExcluirPedido + pedido.getProduto().getNome() + " ?");
                    alerta.setIcon(android.R.drawable.ic_menu_delete);
                    alerta.setPositiveButton(R.string.sair,null);
                    alerta.setNeutralButton("-1", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int x = pedido.getQuantidade();
                            if( pedido.getQuantidade() <= 1 ){
                                itens.remove(pedido);
                            }
                            pedido.setQuantidade( --x);
                            valorTotal = valorTotal - pedido.getProduto().getValor();
                            pedido.setSubtotal(  pedido.getSubtotal() - pedido.getProduto().getValor());
                            tvDescricao.setText("R$: "+ df.format(valorTotal));
                            pedido = null;
                            adapterListView.notifyDataSetChanged();
                        }
                    });
                    alerta.setNegativeButton("+1", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int x = pedido.getQuantidade();
                            pedido.setQuantidade( ++x);
                            valorTotal =  valorTotal+ pedido.getProduto().getValor();
                            pedido.setSubtotal(   pedido.getSubtotal() + pedido.getProduto().getValor());
                            tvDescricao.setText("R$: "+ df.format(valorTotal));
                            pedido = null;
                            adapterListView.notifyDataSetChanged();
                        }
                    });
                    alerta.show();
                    return true;
                }
            });
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
       if( itens != null && itens.size() > 0){
            tvValor.setText("Total a Pagar");
            adapterListView.notifyDataSetChanged();
            valorTotal = 0.0;
            for (int i = 0; i < itens.size(); i++) {
                valorTotal +=   itens.get(i).getSubtotal();
            }
            tvDescricao.setText("R$ "+ df.format(valorTotal));
        }
    }
    public void realizarPedido(View v) {
        Intent it = null;
        int codRequisicao = 0;
        switch (v.getId()){
            case R.id.pedir:
                it = new Intent(this, NovoProdutoActivity.class);
                codRequisicao = 10;
                break;
        }
        if(it != null)
            startActivityForResult(it, codRequisicao);
    }
    public void fecharConta(View v){

       Double valor = valorTotal;
        if(valor > 0.1 ){
            Intent i = new Intent(this,FecharContaActivity.class);
            i.putExtra("valorConta",valorTotal);
            startActivity(i);
        }else{
            Toast.makeText(this,"Insira ao menos um pedido para fechar a conta",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==10){
                param = data.getExtras();
                Pedido p = (Pedido)  param.getSerializable("PEDIDO");
                itens.add(p);
            }else if (resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Ação Cancelada", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, " Cancelado ", Toast.LENGTH_SHORT).show();
        }
    }
}
