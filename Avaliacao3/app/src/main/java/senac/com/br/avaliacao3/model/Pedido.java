package senac.com.br.avaliacao3.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



/**
 * Created by helton on 17/10/2017.
 */
@DatabaseTable(tableName = "Pedido")
public class Pedido{
    @DatabaseField( allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(foreign = true,foreignAutoRefresh = true,foreignAutoCreate = true)
    private Produto itemPedido;
    @DatabaseField(columnName = "quantidade",canBeNull = false)
    private int quantidade;

    public Pedido() {
    }

    public Pedido(Produto itemPedido, int quantidade) {
        this.itemPedido = itemPedido;
        this.quantidade = quantidade;
    }

    public Produto getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(Produto itemPedido) {
        this.itemPedido = itemPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void addMais1(){
        quantidade += 1;
    }

    public void removMenos1(){
        if(quantidade>1)
            quantidade -= 1;
    }

    public double getSubtotal(){
        return getItemPedido().getValor()*getQuantidade();
    }

}