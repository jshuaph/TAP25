package Model;

public class Cuenta {
    private String numCuenta;
    private String pin;
    private double saldo;
    private String titular;

    public Cuenta(String numCuenta, String pin, double saldoInicial, String titular) {
        this.numCuenta = numCuenta;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    // Reglas del negocio
    public boolean validarPin(String pinIngresado){
        return this.pin.equals(pinIngresado);
    }

    public boolean retirar(double cantidad){
        if(cantidad > 0 && cantidad <= saldo){
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    public void depositar(double cantidad){
        if(cantidad > 0){
            saldo += cantidad;
        }
    }

    //Tarea: Diseñar los comportamientos restantes: Transferir, cambiar pin...
}
