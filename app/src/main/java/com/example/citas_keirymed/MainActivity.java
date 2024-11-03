package com.example.citas_keirymed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public LinearLayout Citas;
    public static final int REQUEST_CODE_DATE_TIME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Citas = findViewById(R.id.Citas);
        Button Btn_Cita = findViewById(R.id.Btn_Cita);

        Btn_Cita.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SeleccionCita.class);
            startActivityForResult(intent, REQUEST_CODE_DATE_TIME);
        });
    }

    @Override
    protected void onActivityResult(int Solicitud, int Resultado, @Nullable Intent data) {
        super.onActivityResult(Solicitud, Resultado, data);
        if (Solicitud == REQUEST_CODE_DATE_TIME && Resultado == RESULT_OK && data != null) {
            String Fecha = data.getStringExtra("Fecha");
            String Hora = data.getStringExtra("Hora");
            View citaView = LayoutInflater.from(this).inflate(R.layout.cita, Citas, false);
            TextView CitaTitulo = citaView.findViewById(R.id.CitaTitulo);
            TextView CitaHora = citaView.findViewById(R.id.CitaHora);
            ImageView BtnEliminar = citaView.findViewById(R.id.Btn_Eliminar);
            CitaTitulo.setText("Cita Médica");
            CitaHora.setText(Hora);

            BtnEliminar.setOnClickListener(v -> {
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.izquierda);
                citaView.startAnimation(anim);

                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Citas.removeView(citaView);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            });

            Citas.addView(citaView);
        }
    }
}
