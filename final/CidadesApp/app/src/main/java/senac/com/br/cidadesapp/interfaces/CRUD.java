package senac.com.br.cidadesapp.interfaces;

import java.util.ArrayList;

/**
 * Created by Renato on 10/04/2017.
 */
public interface CRUD<A> {
    boolean cadastrar();
    boolean excluir();
    boolean editar();
    ArrayList<A> listar();
}
