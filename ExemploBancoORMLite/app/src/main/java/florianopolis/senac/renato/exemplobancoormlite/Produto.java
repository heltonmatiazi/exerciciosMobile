package florianopolis.senac.renato.exemplobancoormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by helton on 04/10/2017.
 */
@DatabaseTable(tableName = "produtos")
public class Produto {
    @DatabaseField( allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "nome", canBeNull = false)
    private String nome;
    @DatabaseField(columnName = "valor", canBeNull = false)
    private double valor;
    @DatabaseField(foreign = true,foreignAutoRefresh = true,foreignAutoCreate = true)
    private Categoria categoria;



    public Produto(){};

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(Integer id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return getId()+ " - "+ getNome();
    }
}
