package senac.com.br.prova02.model;

import java.io.Serializable;

/**
 * Created by helton on 19/09/2017.
 */

public class Pedido implements Serializable {

    private  Integer quantidade;
    private float subtotal;
    private Produto produto;


    public Pedido(){}

    public Pedido( Integer quantidade, Produto produto) {

        this.produto = produto;
        this.quantidade = quantidade;
    }
    public Pedido( Produto produto) {

        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return  produto.getNome() +" "+produto.getValor();
    }
}
