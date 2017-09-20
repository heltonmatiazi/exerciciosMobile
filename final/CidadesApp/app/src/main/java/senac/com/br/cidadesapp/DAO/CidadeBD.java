package senac.com.br.cidadesapp.DAO;

import java.util.ArrayList;

import senac.com.br.cidadesapp.interfaces.CRUD;
import senac.com.br.cidadesapp.modelos.Cidade;

/**
 * Created by Renato on 10/04/2017.
 */
public class CidadeBD implements CRUD<Cidade> {
    @Override
    public boolean cadastrar() {
        return false;
    }

    @Override
    public boolean excluir() {
        return false;
    }

    @Override
    public boolean editar() {
        return false;
    }

    @Override
    public ArrayList<Cidade> listar() {
        return null;
    }
}
