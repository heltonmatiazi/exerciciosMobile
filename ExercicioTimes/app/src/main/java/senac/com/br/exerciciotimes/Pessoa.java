package senac.com.br.exerciciotimes;

/**
 * Created by helton on 11/09/2017.
 */

public class Pessoa {
    private String nome;
    private int idade;
    private Time time;

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, Time time) {
        this.nome = nome;
        this.idade = idade;
        this.time = time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", time=" + time.getNome();
    }
}
