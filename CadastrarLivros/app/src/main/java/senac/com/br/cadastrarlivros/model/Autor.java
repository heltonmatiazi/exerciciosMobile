package senac.com.br.cadastrarlivros.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by helton on 27/09/2017.
 */
@DatabaseTable(tableName="Autor")
public class Autor {
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false,columnName = "nome",width = 100)
    private String nome;
    public Autor(){};

    public Autor(String nome) {
        this.nome = nome;
    }

    public Autor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return " nome='" + nome;
    }
}
