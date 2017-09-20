package senac.com.br.exemplolayoutcustomizado;

/**
 * Created by helton on 11/09/2017.
 */

public class ItemListView {
    private String texto;
    private int imageResId;
    public ItemListView(){};
    public ItemListView(String texto, int imageResId) {
        this.texto = texto;
        this.imageResId = imageResId;
    };
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public int getImageResId() {
        return imageResId;
    }
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
