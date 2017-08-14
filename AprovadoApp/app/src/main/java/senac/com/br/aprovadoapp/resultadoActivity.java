package senac.com.br.aprovadoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class resultadoActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        tv1 = (TextView) findViewById(R.id.res1);
        tv2 = (TextView) findViewById(R.id.res2);
        tv3 = (TextView) findViewById(R.id.res3);

        Bundle param = getIntent().getExtras();
        String nomeDisciplina1 = param.getString("nomedisciplina1");
        int n1d1 = param.getInt("nota1discipina1");
        int n2d1 = param.getInt("nota2discipina1");
        int n3d1 = param.getInt("nota3discipina1");
        double media1 = (n1d1 + n2d1 + n3d1)/3;

        String nomeDisciplina2 = param.getString("nomedisciplina2");
        int n1d2 = param.getInt("nota1disciplina2");
        int n2d2 = param.getInt("nota2disciplina2");
        int n3d2 = param.getInt("nota3disciplina2");
        double media2 = (n1d2 + n2d2 + n3d2)/3;

        String situacao1,situacao2,situacaoFinal;
        if(media1 >= 6){
            situacao1 = "aprovado";
        }else{
            situacao1 = "reprovado";
        }
        if(media2 >=6){
            situacao2 = "aprovado";
        }else{
            situacao2 = "reprovado";
        }

        double mediaFinal = (media1+media2)/2;
        if(mediaFinal>6){
            situacaoFinal = "aprovado";
        }else{
            situacaoFinal = "reprovado";
        }
        tv1.setText("Disciplina 1 : "+ nomeDisciplina1+ "\n"+ "Média: " + media1 + "\n" + "situação: " + situacao1);
        tv2.setText("Disciplina 2 : "+ nomeDisciplina2+ "\n"+ "Média: "+ media2 + "\n" + "situação: " + situacao2);
        tv3.setText("Média geral: "+ mediaFinal+"\n"+ "Situação final: "+ situacaoFinal);
    }
}
