package com.uelbosque.subsidiojoven.modelo;

public class ParametrosModelo {
    private int edad;
    private double ingresos;
    private int puntajeCrediticio;
    private double valorInmueble;
    private final double salarioMinimo = 1_160_000;

    public ParametrosModelo(
        String edad, 
        String ingresos,
        String puntaje,
        String precio) {
        
        double valor = Double.parseDouble(edad);
        this.edad = valor > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)valor;
        this.ingresos = Double.parseDouble(ingresos);

        valor = Double.parseDouble(puntaje);
        this.puntajeCrediticio = valor > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)valor;
        this.valorInmueble = Double.parseDouble(precio);
    }

    public int getEdad() {
        return edad;
    }
    
    public double getIngresos() {
        return ingresos / salarioMinimo;
    }
    
    public int getPuntajeCrediticio() {
        return puntajeCrediticio;
    }
    
    public double getValorInmueble() {
        return valorInmueble / salarioMinimo;
    }

    @Override
    public String toString() {
        return "ParametrosModelo [edad=" + edad + ", ingresos=" + (ingresos / salarioMinimo) + ", puntajeCrediticio=" + puntajeCrediticio
                + ", valorInmueble=" + (valorInmueble / salarioMinimo) + ", salarioMinimo=" + salarioMinimo + "]";
    }

    
}
