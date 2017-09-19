package senac.com.br.prova02.model;

import java.io.Serializable;

/**
 * Created by helton on 19/09/2017.
 */

public class Produto  implements Serializable {

    private String nome;
    private Float valor;

    public Produto(String nome, Float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
