package Model;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();

    }

    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345","1111", 5000,"Juan Pérez"));
        cuentas.put("67890", new Cuenta("67890","2222", 37892.21,"Roberto Garnica"));
        cuentas.put("121221", new Cuenta("121221","3333", 100000,"Julian Quiñones"));
    }

    public boolean autenticar(String numCuenta, String pin ) {
        Cuenta cuenta = cuentas.get(numCuenta);
        if(cuenta != null && cuenta.validarPin(pin)){
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    public double consultarSaldo(){
        return this.cuentaActual != null ? this.cuentaActual.getSaldo() : 0;
    }

    public boolean realizarRetiro(double cantidad){
        return cuentaActual != null && this.cuentaActual.retirar(cantidad);
    }

    public boolean realizarDeposito(double cantidad){
        if(cuentaActual != null && cantidad > 0){
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    public boolean cuentaExiste(String numCuenta){
        return cuentas.containsKey(numCuenta);
    }

    //Tarea: definir el metodo para transferir
}
