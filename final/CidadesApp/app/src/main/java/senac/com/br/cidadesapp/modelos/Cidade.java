package senac.com.br.cidadesapp.modelos;

import java.io.Serializable;

/**
 * Created by Renato on 10/04/2017.
 */
public class Cidade implements Serializable {
    private int id;
    private String nome;
    private int habitantes;
    private Estado estado;

    public Cidade() {
    }

    public Cidade(int id, String nome, int habitantes, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.habitantes = habitantes;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nome + " - " + estado.getSigla() + "("+habitantes+")";
    }
}
