package com.example.missensores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para el RecyclerView que muestra los sensores en formato de cards
 * con 4 elementos por sensor: Nombre, Tipo, Fabricante y Versión
 */
public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {
    
    private List<SensorInfo> sensores;
    
    /**
     * Constructor del adapter
     * @param sensores Lista de sensores a mostrar
     */
    public SensorAdapter(List<SensorInfo> sensores) {
        this.sensores = sensores;
    }
    
    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sensor, parent, false);
        return new SensorViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        SensorInfo sensor = sensores.get(position);
        
        // Los 4 elementos del sensor
        holder.tvSensorName.setText(sensor.getNombre());
        holder.tvSensorType.setText("Tipo: " + sensor.getTipo());
        
        // Mejorar el texto del fabricante
        String fabricante = sensor.getFabricante();
        if (fabricante == null || fabricante.isEmpty() || fabricante.equals("The Android Open Source Project")) {
            fabricante = "Sistema Android";
        }
        holder.tvManufacturer.setText("Fabricante: " + fabricante);
        
        holder.tvVersion.setText("Versión: " + sensor.getVersion());
    }
    
    @Override
    public int getItemCount() {
        return sensores.size();
    }
    
    /**
     * Actualiza la lista de sensores y notifica al RecyclerView
     * @param nuevosSensores Nueva lista de sensores
     */
    public void updateSensores(List<SensorInfo> nuevosSensores) {
        this.sensores = nuevosSensores;
        notifyDataSetChanged();
    }
    
    /**
     * Obtiene el sensor en una posición específica
     * @param position Posición del sensor
     * @return SensorInfo en esa posición
     */
    public SensorInfo getSensorAt(int position) {
        if (position >= 0 && position < sensores.size()) {
            return sensores.get(position);
        }
        return null;
    }
    
    /**
     * ViewHolder que contiene las vistas de cada item del sensor
     */
    static class SensorViewHolder extends RecyclerView.ViewHolder {
        TextView tvSensorName;
        TextView tvSensorType;
        TextView tvManufacturer;
        TextView tvVersion;
        
        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            
            tvSensorName = itemView.findViewById(R.id.tvSensorName);
            tvSensorType = itemView.findViewById(R.id.tvSensorType);
            tvManufacturer = itemView.findViewById(R.id.tvManufacturer);
            tvVersion = itemView.findViewById(R.id.tvVersion);
        }
    }
}
