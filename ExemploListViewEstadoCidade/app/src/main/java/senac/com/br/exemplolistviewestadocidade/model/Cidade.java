package senac.com.br.exemplolistviewestadocidade.model;

import java.io.Serializable;

/**
 * Created by helton on 06/09/2017.
 */

public class Cidade implements Serializable {
    public String nome;
    public String habitantes;
    public String estado;

    public Cidade() {
    }

    public Cidade(String nome, String habitantes, String estado) {
        this.nome = nome;
        this.habitantes = habitantes;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(String habitantes) {
        this.habitantes = habitantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "nome: " + getNome() + " "+ "\n Habitantes: " + getHabitantes() + "\n UF: " + getEstado();
    }
}
