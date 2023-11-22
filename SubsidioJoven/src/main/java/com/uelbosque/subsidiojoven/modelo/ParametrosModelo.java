package com.uelbosque.subsidiojoven.modelo;

public class ParametrosModelo {
    private int edad;
    private double ingresos;
    private int puntajeCrediticio;
    private double valorInmueble;
    private final double salarioMinimo = 1_160_000;
    private String edadStr;
    private String ingresosStr;
    private String puntajeStr;
    private String valorStr;
    private boolean recibioSubsidio;
    private boolean poseeInmuebles;

    public ParametrosModelo(
        String edad, 
        String ingresos,
        String puntaje,
        String precio,
        boolean recibioSubsidio,
        boolean poseeInmuebles) {
        this.edadStr = edad;
        this.ingresosStr = ingresos;  
        this.puntajeStr = puntaje;
        this.valorStr = precio;  
        this.recibioSubsidio = recibioSubsidio;
        this.poseeInmuebles = poseeInmuebles;    
    }

    public void validarDatos() throws IllegalArgumentException {
        double valor = 0;
        try {
            valor = Double.parseDouble(this.edadStr);
            if (valor < 0) {
                throw new IllegalArgumentException("Debe ingresar un valor valido para la edad");
            }
            this.edad = (int)valor;
        } catch (Exception e) {
            throw new IllegalArgumentException("Debe ingresar un valor valido para la edad", e);
        }
        
        try {
            this.ingresos = Double.parseDouble(this.ingresosStr);
            if (this.ingresos < 0){
                throw new IllegalArgumentException("Debe ingresar un valor valido para los ingresos");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Debe ingresar un valor valido para los ingresos", ex);
        }

        try {
            valor = Double.parseDouble(this.puntajeStr);
            if (valor < 0 && valor > 1000) {
                throw new IllegalArgumentException("Debe ingresar un valor entre 0 y 1000 para el puntaje crediticio");
            }
            this.puntajeCrediticio = valor > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)valor;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Debe ingresar un valor entre 0 y 1000 para el puntaje crediticio", ex);
        }
        
        try {
            valor = Double.parseDouble(this.valorStr);
            if (valor < 0 && valor > (salarioMinimo * 150)) {
                throw new IllegalArgumentException("Debe ingresar un valor entre 0 y " + (salarioMinimo * 150) + " para el precio del inmueble");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Debe ingresar un valor entre 0 y " + (salarioMinimo * 150) + " para el precio del inmueble", ex);
        }
    }

    public boolean esValido() {
        if (this.edad < 18 && this.edad > 28) {
            return false;
        }
        else if (this.recibioSubsidio || this.poseeInmuebles) {
            return false;
        }
        return true;
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
