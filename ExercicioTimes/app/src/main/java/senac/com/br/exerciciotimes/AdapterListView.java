package senac.com.br.exerciciotimes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by helton on 11/09/2017.
 */

public class AdapterListView extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Time> itens;
    private String nome;
    private int idade;
    private String resultado;
    public AdapterListView(Context context, ArrayList<Time> itens,String nome, int idade,String resultado) {
        this.itens = itens;
        this.nome = nome;
        this.idade = idade;
        this.resultado = resultado;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Time time = itens.get(position);
        view = inflater.inflate(R.layout.item_listview, null);
        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        TextView tvIdade = (TextView) view.findViewById(R.id.tvIdade);
        TextView tvResultado = (TextView) view.findViewById(R.id.resultado);
        ImageView ivImagem = (ImageView) view.findViewById(R.id.imageView);
        tvNome.setText(tvNome.getText().toString());
        tvIdade.setText(parseInt(tvIdade.getText().toString()));
        ivImagem.setImageResource(time.getImageResId());
        if(parseInt(tvIdade.getText().toString()) <= 18){
            tvResultado.setText(R.string.maior);
            tvResultado.setTextColor(Color.GREEN);
        }else{
            tvResultado.setText(R.string.menor);
            tvResultado.setTextColor(Color.RED);
        }
        return view;
    };
};