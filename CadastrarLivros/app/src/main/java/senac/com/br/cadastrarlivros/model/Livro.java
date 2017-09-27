package senac.com.br.cadastrarlivros.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by helton on 27/09/2017.
 */
@DatabaseTable(tableName="Livro")
public class Livro {
    @DatabaseField(allowGeneratedIdInsert = true,generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false,columnName = "titulo",width = 100)
    private String titulo;
    @DatabaseField(foreign = true)
    private Autor autor;
    @DatabaseField(canBeNull = false,columnName = "numeroPaginas")
    private Integer numeroPaginas;

    public Livro(){};

    public Livro(String titulo, Autor autor, Integer numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public Livro(Integer id, String titulo, Autor autor, Integer numeroPaginas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", numeroPaginas=" + numeroPaginas +
                '}';
    }
}
