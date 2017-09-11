package senac.com.br.exemplolayoutcustomizado;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewCustomizado);
        createListView();
    }
    private void createListView(){
        itens = new ArrayList<ItemListView>();
        ItemListView item1 = new ItemListView("Júpiter",R.drawable.jupiter);
        ItemListView item2 = new ItemListView("Netuno",R.drawable.netuno);
        ItemListView item3 = new ItemListView("Mercúrio",R.drawable.mercurio);
        ItemListView item4 = new ItemListView("Saturno",R.drawable.saturno);

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);
        adapterListView = new AdapterListView(this,itens);
        listView.setAdapter(adapterListView);
        listView.setCacheColorHint(Color.TRANSPARENT);
    }

}
