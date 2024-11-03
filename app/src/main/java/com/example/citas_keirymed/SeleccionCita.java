package com.example.citas_keirymed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
public class SeleccionCita extends AppCompatActivity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private final Calendar calendario = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_cita);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        Button BtnOk = findViewById(R.id.Btn_Ok);
        Button BtnCancelar = findViewById(R.id.Btn_Cancelar);

        final int AñoActual = calendario.get(Calendar.YEAR);
        datePicker.init(AñoActual, calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                (view, año, mes, dia) -> {
                        calendario.set(año, mes, dia);
                        int Semana = calendario.get(Calendar.DAY_OF_WEEK);
                        if (Semana == Calendar.SATURDAY || Semana == Calendar.SUNDAY || año != AñoActual) {
                            datePicker.updateDate(AñoActual, calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));
                        }
        });

        BtnOk.setOnClickListener(v -> {
            int Año = datePicker.getYear();
            int Mes = datePicker.getMonth() + 1;
            int Dia = datePicker.getDayOfMonth();
            int Hora = timePicker.getHour();
            int Minuto = timePicker.getMinute();

            String SeleccionFecha = Año + "-" + Mes + "-" + Dia;
            String SeleccionHora = String.format("%02d:%02d", Hora, Minuto);

            Intent resultadoIntent = new Intent();
            resultadoIntent.putExtra("Fecha", SeleccionFecha);
            resultadoIntent.putExtra("Hora", SeleccionHora);
            setResult(Activity.RESULT_OK, resultadoIntent);
            finish();
        });

        BtnCancelar.setOnClickListener(v -> {
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
