package senac.com.br.prova02.helpers;

import java.io.Serializable;

import senac.com.br.prova02.model.Pedido;

/**
 * Created by helton on 19/09/2017.
 */

public class ItemListView implements Serializable {
    private String texto;
    private float valor;
    private Integer quantidade;

    public ItemListView() {}

    public ItemListView(Pedido p) {
        this.texto = p.getProduto().getNome();
        this.valor = p.getProduto().getValor();
        this.quantidade = p.getQuantidade();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
