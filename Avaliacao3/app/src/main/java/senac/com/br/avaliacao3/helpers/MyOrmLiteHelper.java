package senac.com.br.avaliacao3.helpers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import senac.com.br.avaliacao3.model.Pedido;
import senac.com.br.avaliacao3.model.Produto;

/**
 * Created by helton on 17/10/2017.
 */

public class MyOrmLiteHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="avaliacao3.db";
    private static final int DATABASE_VERSION = 1;

    private static MyOrmLiteHelper mInstance;
    private Dao<Pedido, Integer> pedidoDao=null;
    private Dao<Produto,Integer> produtoDao = null;

    public MyOrmLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyOrmLiteHelper getInstance(Context ctx){
        if(mInstance == null)
            mInstance = new MyOrmLiteHelper(ctx);
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Pedido.class);
            TableUtils.createTable(connectionSource, Produto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Pedido.class, true);
            TableUtils.dropTable(connectionSource, Produto.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<Pedido, Integer> getCategoriaDao() throws SQLException {
        if(pedidoDao==null){
            try {
                pedidoDao = getDao(Pedido.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return pedidoDao;
    }
    public Dao<Produto, Integer> getProdutoDao() throws SQLException {
        if(produtoDao==null){
            try {
                produtoDao = getDao(Produto.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return produtoDao;
    }

}
