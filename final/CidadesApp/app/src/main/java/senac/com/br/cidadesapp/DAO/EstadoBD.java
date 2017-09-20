package senac.com.br.cidadesapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import senac.com.br.cidadesapp.interfaces.CRUD;
import senac.com.br.cidadesapp.modelos.Estado;

/**
 * Created by Renato on 10/04/2017.
 */
public class EstadoBD implements CRUD<Estado> {

    private Estado estado;
    private SQLiteDatabase banco;
    private static final String BANCO_NOME = "cidadesApp.bd";
    private int lastInserId;

    //Modo de acesso
    // 0 -> Modo privado
    // 1 -> Outros apps podem ler
    // 2 -> Outros apps podem ler e escrever
    private static final int ACESSO_BANCO = 0;

    //Código sql para criação da tabela estados
    private static final String SQL_TABELA = "" +
            "CREATE TABLE IF NOT EXISTS estados(" +
            "id integer not null primary key autoincrement," +
            "nome varchar(45) not null," +
            "sigla char(2) not null)";

    //SQL para selecionar todos os estados
    private static final String SQL_SELECT_ALL = "" +
            "SELECT nome, id, sigla FROM estados ORDER BY id DESC";

    public EstadoBD(Context ctx){
        this.banco = ctx.openOrCreateDatabase(BANCO_NOME, ACESSO_BANCO, null);
        this.banco.execSQL(SQL_TABELA);
        this.estado = new Estado();
    }



    @Override
    public boolean cadastrar() {
        ContentValues valores = new ContentValues();
        valores.put("nome", estado.getNome());
        valores.put("sigla", estado.getSigla());

        //Recebe o retorno do insert. -1 errado ou o id da nova cidade
        long res = this.banco.insert("estados", null, valores);
        if(res==-1){
            return false;
        }else{
            this.lastInserId = (int)res;
            return true;
        }

    }

    @Override
    public boolean excluir() {
        String[] valoresWhere = new String[]{String.valueOf(estado.getId())};
        long res = this.banco.delete("estados", "id=?", valoresWhere);
        if(res==-1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean editar() {
        ContentValues valores = new ContentValues();
        valores.put("nome", estado.getNome());
        valores.put("sigla", estado.getSigla());

        String[] valoresWhere = new String[]{String.valueOf(estado.getId())};
        long res = this.banco.update("estados", valores, "id=?", valoresWhere);
        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public ArrayList<Estado> listar() {
        ArrayList<Estado> listEstados = new ArrayList<>();
        Cursor cursor = this.banco.rawQuery(SQL_SELECT_ALL, null);
        while(cursor.moveToNext()){
            estado.setId(cursor.getInt(cursor.getColumnIndex("id")));
            estado.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            estado.setSigla(cursor.getString(cursor.getColumnIndex("sigla")));
            listEstados.add(estado);
            estado = new Estado();
        }

        return listEstados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getLastInserId() {
        return lastInserId;
    }
}
