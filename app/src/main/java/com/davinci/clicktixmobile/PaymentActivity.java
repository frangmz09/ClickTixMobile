package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.davinci.clicktixmobile.model.Ticket;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {

    private TextInputLayout numeroTarjetaLayout;
    private TextInputLayout nombreTitularLayout;
    private TextInputLayout codigoSeguridadLayout;
    private TextInputLayout fechaVencimientoLayout;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);





        Intent intent = getIntent();
        Ticket ticket = (Ticket) intent.getSerializableExtra("TICKET");
        System.out.println(ticket.toStringTicket());

        db = FirebaseFirestore.getInstance();







        // Inicializar los TextInputLayout
        numeroTarjetaLayout = findViewById(R.id.numeroTarjeta);
        nombreTitularLayout = findViewById(R.id.nombreTitular);
        codigoSeguridadLayout = findViewById(R.id.codigoSeguridad);
        fechaVencimientoLayout = findViewById(R.id.fechaVencimiento);

        ImageView back_home = findViewById(R.id.id_logo_home);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backButton = findViewById(R.id.btn_volver_tickets);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, FuncionesActivity.class);
                startActivity(intent);
            }
        });

        Button btnConfirmarPago = findViewById(R.id.btnConfirmarPago);
        btnConfirmarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    realizarPago();

                    crearDocumento(ticket);

                    Intent intent = new Intent(PaymentActivity.this, CompraFinalActivity.class);
                    intent.putExtra("TICKET", ticket);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validarNumeroTarjeta() {
        String numeroTarjeta = Objects.requireNonNull(numeroTarjetaLayout.getEditText()).getText().toString().trim();

        if (numeroTarjeta.isEmpty()) {
            numeroTarjetaLayout.setError("Ingrese el número de tarjeta");
            return false;
        } else if (!numeroTarjeta.matches("\\d{16}")) {
            numeroTarjetaLayout.setError("El número de tarjeta debe tener 16 dígitos");
            return false;
        } else {
            numeroTarjetaLayout.setError(null);
            return true;
        }
    }

    private boolean validarNombreTitular() {
        String nombreTitular = Objects.requireNonNull(nombreTitularLayout.getEditText()).getText().toString().trim();

        if (nombreTitular.isEmpty()) {
            nombreTitularLayout.setError("Ingrese el nombre del titular");
            return false;
        } else if (!nombreTitular.matches("[a-zA-Z ]+")) {
            nombreTitularLayout.setError("El nombre del titular solo debe contener letras");
            return false;
        } else {
            nombreTitularLayout.setError(null);
            return true;
        }
    }

    private boolean validarCodigoSeguridad() {
        String codigoSeguridad = Objects.requireNonNull(codigoSeguridadLayout.getEditText()).getText().toString().trim();

        if (codigoSeguridad.isEmpty()) {
            codigoSeguridadLayout.setError("Ingrese el código de seguridad");
            return false;
        } else if (!codigoSeguridad.matches("\\d{3}")) {
            codigoSeguridadLayout.setError("El código de seguridad debe tener 3 dígitos");
            return false;
        } else {
            codigoSeguridadLayout.setError(null);
            return true;
        }
    }

    private boolean validarFechaVencimiento() {
        String fechaVencimiento = Objects.requireNonNull(fechaVencimientoLayout.getEditText()).getText().toString().trim();

        if (fechaVencimiento.isEmpty()) {
            fechaVencimientoLayout.setError("Ingrese la fecha de vencimiento");
            return false;
        } else if (!fechaVencimiento.matches("\\d{2}/\\d{2}")) {
            fechaVencimientoLayout.setError("El formato de fecha debe ser MM/YY");
            return false;
        } else {
            fechaVencimientoLayout.setError(null);
            return true;
        }
    }

    private boolean validarCampos() {
        return validarNumeroTarjeta() && validarNombreTitular() && validarCodigoSeguridad() && validarFechaVencimiento();
    }

    private boolean validarCampo(TextInputLayout layout, String campo) {
        String valor = Objects.requireNonNull(layout.getEditText()).getText().toString().trim();
        if (valor.isEmpty()) {
            layout.setError("Ingrese " + campo);
            return false;
        } else {
            layout.setError(null);
            return true;
        }
    }

    private void realizarPago() {
        Toast.makeText(this, "¡Su pago ha sido confirmado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PaymentActivity.this, CompraFinalActivity.class);
        startActivity(intent);
    }
    private void crearDocumento(Ticket ticket) {

        Map<String, Object> datosDocumento = new HashMap<>();
        datosDocumento.put("cantidad_butacas", ticket.getCantidadButacas());
        datosDocumento.put("dimension", ticket.getDimension());
        datosDocumento.put("email", ticket.getEmail());
        datosDocumento.put("fecha", ticket.getFecha());
        datosDocumento.put("horario", ticket.getHorario());
        datosDocumento.put("idioma", ticket.getIdioma());
        datosDocumento.put("pelicula_id", ticket.getPeliculaId());
        datosDocumento.put("fecha_compra", LocalDate.now().toString());


        db.collection("tickets")
                .add(datosDocumento)
                .addOnSuccessListener(documentReference -> {
                    String idDocumento = documentReference.getId();
                    Log.d("TAG", "Documento creado con ID: " + idDocumento);
                })
                .addOnFailureListener(e -> {
                    Log.e("TAG", "Error al crear el documento", e);
                });
    }

    private Bitmap generarCodigoQR(String contenido) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(contenido, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);                }
            }

            return bmp;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}