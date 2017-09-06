package senac.com.br.exemplolistviewestadocidade.model;

import java.io.Serializable;

/**
 * Created by helton on 06/09/2017.
 */

public class Estado implements Serializable {
    private String nome;
    private String sigla;

    public Estado() {

    }

    public Estado(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return getNome() + " (" + getSigla() + ")";
    }
}
