package com.example.missensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSensores;
    private Button btnActualizar;
    private SensorManager gestorSensores;
    private SensorAdapter sensorAdapter;
    private List<SensorInfo> listaSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el SensorManager
        gestorSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Inicializar vistas
        recyclerViewSensores = findViewById(R.id.recyclerViewSensores);
        btnActualizar = findViewById(R.id.btnActualizar);
        listaSensores = new ArrayList<>();

        // Configurar RecyclerView con GridLayout (2 columnas)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewSensores.setLayoutManager(gridLayoutManager);
        
        // Configurar adapter
        sensorAdapter = new SensorAdapter(listaSensores);
        recyclerViewSensores.setAdapter(sensorAdapter);

        // Cargar sensores inicialmente
        cargarSensores();

        // Configurar botón de actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarSensores();
                Toast.makeText(MainActivity.this, "Lista de sensores actualizada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarSensores() {
        List<Sensor> sensores = gestorSensores.getSensorList(Sensor.TYPE_ALL);
        listaSensores.clear();

        for (Sensor sensor : sensores) {
            // Mejorar el fabricante
            String fabricante = sensor.getVendor();
            if (fabricante == null || fabricante.isEmpty() || fabricante.equals("The Android Open Source Project")) {
                fabricante = "Sistema Android";
            }
            
            SensorInfo sensorInfo = new SensorInfo(
                sensor.getName(),
                obtenerTipoSensor(sensor.getType()),
                fabricante,
                "v" + sensor.getVersion()
            );
            listaSensores.add(sensorInfo);
        }

        // Actualizar el adapter
        sensorAdapter.updateSensores(listaSensores);
    }

    private String obtenerTipoSensor(int tipo) {
        switch (tipo) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Acelerómetro";
            case Sensor.TYPE_GYROSCOPE:
                return "Giróscopo";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Campo Magnético";
            case Sensor.TYPE_ORIENTATION:
                return "Orientación";
            case Sensor.TYPE_TEMPERATURE:
                return "Temperatura";
            case Sensor.TYPE_PROXIMITY:
                return "Proximidad";
            case Sensor.TYPE_LIGHT:
                return "Luz";
            case Sensor.TYPE_PRESSURE:
                return "Presión";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Humedad";
            case Sensor.TYPE_GRAVITY:
                return "Gravedad";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Aceleración Lineal";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Vector de Rotación";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Rotación de Juego";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Rotación Geomagnética";
            case Sensor.TYPE_STEP_COUNTER:
                return "Contador de Pasos";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Detector de Pasos";
            case Sensor.TYPE_HEART_RATE:
                return "Ritmo Cardíaco";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "Temperatura Ambiente";
            default:
                return "Sensor Tipo " + tipo;
        }
    }
}