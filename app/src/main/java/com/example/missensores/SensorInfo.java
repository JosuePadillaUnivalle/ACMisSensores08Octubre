package com.example.missensores;

public class SensorInfo {
    private String nombre;
    private String tipo;
    private String fabricante;
    private String version;

    public SensorInfo(String nombre, String tipo, String fabricante, String version) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.version = version;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getFabricante() { return fabricante; }
    public String getVersion() { return version; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public void setVersion(String version) { this.version = version; }
}
