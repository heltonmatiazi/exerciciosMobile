package senac.com.br.cadastrarlivros.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import senac.com.br.cadastrarlivros.R;

/*
* Esse exercício explica as bases de banco de dados ORMLite para android
*
* Desenvolver uma aplicação onde você possa cadastrar livros
com: titulo; autor; numero páginas.
• O Autor deve ser selecionado em um Spinner e os dados virão
do banco de dados.
• Deve haver a possibilidade de cadastrar e editar o autor e o livro.
• Disponibilizar a listagem de livros por autor. Ao clicar em um
autor, mostrar opções em Dialog, um das opções deverá ser:
    Ver Livros.
* */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // cadastrar livro
    public void cadastrarLivro(View v){
        Intent intent=new Intent(this,CadastrarLivroActivity.class);
        startActivity(intent);
    }
    // editar livro\autor
    public void editarLivro(View v){
        Intent intent = new Intent(this,EditarLivroActivity.class);
        startActivity(intent);
    }
    // listar livros
    public void listarLivros(View v){
        Intent intent = new Intent(this,ListaActivity.class);
        startActivity(intent);
    }
}
