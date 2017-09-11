package senac.com.br.exemplolayoutcustomizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by helton on 11/09/2017.
 */

public class AdapterListView extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ItemListView> itens;

    public AdapterListView(Context context,ArrayList<ItemListView> itens) {
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
      ItemListView item = itens.get(position);
      view = inflater.inflate(R.layout.item_listview,null);
      TextView tvTexto = (TextView) view.findViewById(R.id.textView);
      ImageView ivImagem = (ImageView) view.findViewById(R.id.imageView);

      tvTexto.setText(item.getTexto());
      ivImagem.setImageResource(item.getImageResId());

        return view;
    };

}
