package senac.com.br.retornarcepapp.activities;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import senac.com.br.retornarcepapp.R;

public class MainActivity extends AppCompatActivity {
    EditText logradouro,complemento,bairro,localidade,uf,unidade,ibge,gia;
    TextView statusMessage;
    EditText cep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cep = (EditText) findViewById(R.id.cep);
        logradouro = (EditText) findViewById(R.id.logradouro);
        complemento = (EditText) findViewById(R.id.complemento);
        bairro = (EditText) findViewById(R.id.bairro);
        localidade = (EditText) findViewById(R.id.localidade);
        uf = (EditText) findViewById(R.id.UF);
        unidade = (EditText) findViewById(R.id.unidade);
        ibge = (EditText) findViewById(R.id.ibge);
        gia = (EditText) findViewById(R.id.gia);
        statusMessage = (TextView) findViewById(R.id.statusMessage);
    }
    public void enviarConsulta(View v){
        String cepCliente = cep.getText().toString();
        String url = "http://viacep.com.br/ws/"+cepCliente+"/json/";

        //Requisição HTTP Asyncrona
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(MainActivity.this, "Iniciando Requisiçao", Toast.LENGTH_SHORT).show();
                statusMessage.setText("Carregando...");
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                //Resgatar o retorno do http
                String res = new String(bytes);

                //Converter para objeto Endereco
                Gson gson = new Gson();
                Endereco endereco = gson.fromJson(res, Endereco.class);
                logradouro.setText(endereco.getLogradouro());
                complemento.setText(endereco.getComplemento());
                bairro.setText(endereco.getBairro());
                localidade.setText(endereco.getLocalidade());
                uf.setText(endereco.getUf());
                unidade.setText(endereco.getUnidade());
                ibge.setText(endereco.getIbge());
                gia.setText(endereco.getGia());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                statusMessage.setText("Erro na requisição "+i);
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                statusMessage.setText("Tentativa: "+retryNo);
            }
        });
    }
}
