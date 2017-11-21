package senac.com.br.myimchelt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

@SuppressWarnings({"MissingPermission", "ResourceType"})
public class MapsActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSAO = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        localizacaoByProviderSigle(LocationManager.NETWORK_PROVIDER, "academia");
    }
    public void verificarPermissao() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSAO);
            return;
        }
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_PERMISSAO && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MapsActivity.this, getString(R.string.toast_ok) + "\n" + getString(R.string.toast_rep), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MapsActivity.this, getString(R.string.toast_negado), Toast.LENGTH_SHORT).show();
        }
    }


    public void localizacaoByProviderSigle(String provider, final String local) {
        verificarPermissao();
        /* for live devices
        Location location = null;
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Uri gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude+"?q=academia");
        */
        // for emulator
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=academia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}

