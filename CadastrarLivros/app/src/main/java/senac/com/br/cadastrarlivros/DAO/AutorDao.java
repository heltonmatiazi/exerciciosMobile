package senac.com.br.cadastrarlivros.DAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import senac.com.br.cadastrarlivros.model.Autor;
import senac.com.br.cadastrarlivros.model.Livro;

/**
 * Created by helton on 27/09/2017.
 */

public class AutorDao extends BaseDaoImpl<Autor, Integer> {
    public AutorDao(ConnectionSource cs) throws SQLException {
        super(Autor.class);
        setConnectionSource(cs);
        initialize();
    }
}