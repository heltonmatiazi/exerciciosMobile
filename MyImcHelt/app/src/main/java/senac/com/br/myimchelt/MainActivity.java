package senac.com.br.myimchelt;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int PICTURE_RESULT = 1;
    String mCurrentPhotoPath;
    ContentValues values;
    private Uri file;
    private ImageView userImage;
    Bitmap help1;
    ThumbnailUtils thumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userImage = (ImageView) findViewById(R.id.userImage);
        values = new ContentValues();

    }

    public void novaImagem(View v) {
        // intent que chama a camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // pegando a URI do arquivo
        file = Uri.fromFile(getFile());

        // adicionando a URI ao intent
        intent.putExtra(MediaStore.EXTRA_OUTPUT,file);

        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent, PICTURE_RESULT);
        }
    }
    // criando e retornando o caminho para a imagem
    private File getFile() {
        File folder = Environment.getExternalStoragePublicDirectory("/From_camera/imagens");// file path

        // se o folder não existir, ele será criado
        // pq aparentemente é uma ideia boa pra caralho rodar shell no meio do java
        if(!folder.exists())
        {folder.mkdir();}

        // gerando nomes únicos para as imagens
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+ timeStamp + "_";
        File image_file = null;
        // criando o arquivo com o nome gerado anteriormente
        try {
            image_file = File.createTempFile(imageFileName,".jpg",folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // setando o caminho absoluto das imagens
        mCurrentPhotoPath = image_file.getAbsolutePath();
        return image_file;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICTURE_RESULT) {
            if(resultCode == Activity.RESULT_OK) {
                try {
                    help1 = MediaStore.Images.Media.getBitmap(getContentResolver(),file);
                    userImage.setImageBitmap( thumbnail.extractThumbnail(help1,help1.getWidth(),help1.getHeight()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onPause() {

        super.onPause();
    }

    public void calcularImc(View v) {
    }

    public void consultarAcademias(View v) {
        Intent i = new Intent(this, PesquisarAcademia.class);
        startActivity(i);
    }


}
