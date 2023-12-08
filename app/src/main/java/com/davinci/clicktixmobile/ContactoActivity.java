package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        ImageView back_home = findViewById(R.id.btn_volver_tickets);
        ImageView back_home_logo = findViewById(R.id.id_logo_home);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);

        GeoPoint location = new GeoPoint(-34.60434687294662, -58.39603239097312);

        IMapController mapController = mapView.getController();
        mapController.setZoom(16.0);
        mapController.setCenter(location);

        Marker marker = new Marker(mapView);
        marker.setPosition(location);
        mapView.getOverlays().add(marker);

        back_home_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void openMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}