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
        } catch (Exception e) {
            throw new IllegalArgumentException("Debe ingresar un valor valido para la edad", e);
        }
        if (valor < 0) {
            throw new IllegalArgumentException("Debe ingresar un valor valido para la edad");
        } else if (valor < 18 || valor > 28) {
            throw new IllegalArgumentException("El candidato debe ser mayor de edad y tener menos de 28 años");
        }
        this.edad = (int)valor;
        
        try {
            this.ingresos = Double.parseDouble(this.ingresosStr);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Debe ingresar un valor valido para los ingresos", ex);
        }
        if (this.ingresos < 0){
            throw new IllegalArgumentException("Debe ingresar un valor valido para los ingresos");
        } else if (this.ingresos < salarioMinimo) {
            throw new IllegalArgumentException("El candidato no tiene ingresos de por lo menos de " + String.format("$%,.0f", salarioMinimo));
        } else if (this.ingresos > 4 * salarioMinimo) {
            throw new IllegalArgumentException("El candidato posee ingresos mayores a " + String.format("$%,.0f", 4 * salarioMinimo) + ". \n\rLo cual le impide acceder al programa");
        }

        try {
            valor = Double.parseDouble(this.puntajeStr);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Debe ingresar un valor entre 0 y 900 para el puntaje crediticio", ex);
        }
        if (valor < 0 || valor > 900) {
            throw new IllegalArgumentException("Debe ingresar un valor entre 0 y 900 para el puntaje crediticio");
        }
        this.puntajeCrediticio = (int)valor;
        
        try {
            valor = Double.parseDouble(this.valorStr);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Debe ingresar un valor entre 0 y " + String.format("$%,.0f", salarioMinimo * 150) + " para el precio del inmueble", ex);
        }
        if (valor < (100 * salarioMinimo) || valor > (salarioMinimo * 150)) {
            throw new IllegalArgumentException("Debe ingresar un valor entre " + String.format("$%,.0f", salarioMinimo * 100) + " y " + String.format("$%,.0f", salarioMinimo * 150) + " para el precio del inmueble");
        }
        this.valorInmueble = valor;
    }

    public boolean esValido() {
        if (this.edad < 18 || this.edad > 28) {
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
