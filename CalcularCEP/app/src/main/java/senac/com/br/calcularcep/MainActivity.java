package senac.com.br.calcularcep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {
    AsyncHttpClient client = new AsyncHttpClient();
    String cep;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = (EditText)findViewById(R.id.text1);
        Button btn = (Button) findViewById(R.id.btnEnviar);
        tv = (TextView) findViewById(R.id.textReturn);
        cep = et.getText().toString();

        btn.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                   dispararRequest(v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void dispararRequest(View v){
        client.addHeader("uses-agent","Mozilla Chrome");
        client.get("http://viacep.com.br/ws/" + cep + "/json/", new AsyncHttpResponseHandler() {


            @Override
            public void onStart(){
                Toast.makeText(MainActivity.this, "Starting the request", Toast.LENGTH_SHORT).show();
            };
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

                String s = new String(bytes);
                Toast.makeText(getApplicationContext(), "Sucess:\n"+s,Toast.LENGTH_SHORT).show();
                tv.setText(s);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "bad request", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onRetry(int retryNo){
                Toast.makeText(MainActivity.this,"retrying",Toast.LENGTH_SHORT).show();
            }
        });
    };
}
