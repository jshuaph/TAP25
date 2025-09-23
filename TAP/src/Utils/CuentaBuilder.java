package Utils;

import Model.Cuenta;

public class CuentaBuilder {
    private String numCuenta;
    private String pin;
    private double saldo;
    private String titular;

    public CuentaBuilder setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
        return this;
    }

    public CuentaBuilder setPin(String pin) {
        this.pin = pin;
        return this;
    }

    public CuentaBuilder setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public CuentaBuilder setTitular(String titular) {
        this.titular = titular;
        return this;
    }

    public Cuenta build() {
        return new Cuenta(numCuenta, pin, saldo, titular);
    }
}
