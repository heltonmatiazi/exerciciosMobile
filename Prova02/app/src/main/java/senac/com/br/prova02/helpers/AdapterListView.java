package senac.com.br.prova02.helpers;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import senac.com.br.prova02.R;
import senac.com.br.prova02.model.Pedido;

/**
 * Created by helton on 19/09/2017.
 */

public class AdapterListView extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Pedido> itens;

    public AdapterListView(Context context,ArrayList<Pedido> itens) {
        this.itens = itens;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount(){
        return itens.size();
    }
    @Override
    public Object getItem(int position){
        return itens.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        Pedido item = itens.get(position);
        view = inflater.inflate(R.layout.item_listview,null);

        TextView tvTexto = (TextView) view.findViewById(R.id.textQtd);
        TextView tvDesc = (TextView) view.findViewById(R.id.txtProduto);
        TextView tvValor = (TextView) view.findViewById(R.id.txtvalor);
        TextView tvValorUnitario = (TextView) view.findViewById(R.id.valorProdutoUnitario);


        tvTexto.setText(item.getQuantidade().toString());
        tvDesc.setText(item.getProduto().getNome());
        tvValor.setText("Valor Unitário: "+item.getProduto().getValor().toString());
        tvValorUnitario.setText("Valor Total:  "+ String.valueOf(item.getSubtotal()));
        return view;
    };

}
