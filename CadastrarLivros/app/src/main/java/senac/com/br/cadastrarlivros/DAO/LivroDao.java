package senac.com.br.cadastrarlivros.DAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import senac.com.br.cadastrarlivros.model.Livro;

/**
 * Created by helton on 27/09/2017.
 */

public class LivroDao extends BaseDaoImpl<Livro, Integer> {
    public LivroDao(ConnectionSource cs) throws SQLException {
        super(Livro.class);
        setConnectionSource(cs);
        initialize();
    }
}
