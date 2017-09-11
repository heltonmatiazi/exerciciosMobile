package senac.com.br.exerciciotimes;

/**
 * Created by helton on 11/09/2017.
 */

public class Time {
    private String nome;
    private int imageResId;

    public Time() {
    }

    public Time(String nome, int imageResId) {
        this.nome = nome;
        this.imageResId = imageResId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
