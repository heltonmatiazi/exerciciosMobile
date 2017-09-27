package senac.com.br.cadastrarlivros.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import senac.com.br.cadastrarlivros.DAO.LivroDao;
import senac.com.br.cadastrarlivros.DAO.AutorDao;
import senac.com.br.cadastrarlivros.model.Autor;
import senac.com.br.cadastrarlivros.model.Livro;

/**
 * Created by helton on 27/09/2017.
 */

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {
    //nome do banco
    private static final String DATABASE_NAME = "minhaBiblioteca";
    //versão do banco
    private static final int DATABASE_VERSION = 1;
    // caso queira controlar a quantidade de instancias do banco de dados que podem existir ao mesmo tempo
    private static MyORMLiteHelper mInstance = null;

    public MyORMLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public static MyORMLiteHelper getmInstance(Context context){
        if(mInstance == null){
            mInstance = new MyORMLiteHelper(context.getApplicationContext());
        }
        return mInstance;
    }
    private Dao<Autor, Integer> autorDao = null;
    private Dao<Livro, Integer> livroDao = null;

    public Dao<Livro,Integer> getLivroDao() throws SQLException{
        if(livroDao == null){
            livroDao = getDao(Livro.class);
        }
        return livroDao;
    }
    public Dao<Autor,Integer> getAutorDao() throws SQLException{
        if(autorDao == null){
            autorDao = getDao(Autor.class);
        }
        return autorDao;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Livro.class);
            TableUtils.createTable(connectionSource, Autor.class);
        }catch (SQLException e){
            System.out.println("falha ao criar a tabela");
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try{
            TableUtils.dropTable(connectionSource,Livro.class,true);
            TableUtils.dropTable(connectionSource,Autor.class,true);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("falha ao atualizar a tabela");
        }
        onCreate(sqLiteDatabase,connectionSource);
    }
}
