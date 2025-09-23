package Model;

import Utils.CuentaBuilder;
import Utils.OperacionStrategy;
import java.util.HashMap;
import java.util.Map;

/**
 * Modelo de dominio del cajero automático.
 * <p>Mantiene el conjunto de cuentas en memoria y gestiona la sesión
 * de la cuenta actualmente autenticada. Expone operaciones de
 * consulta de saldo, depósito, retiro, transferencia y cambio de PIN
 * delegando en la entidad {@link Cuenta}.</p>
 *
 */
public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    /**
     * Inicializa un conjunto de cuentas practicas.
     */
    private void inicializarCuentas() {
        cuentas.put("12345", new CuentaBuilder()
                .setNumCuenta("12345")
                .setPin("1111")
                .setSaldo(5000)
                .setTitular("Juan Pérez")
                .build());

        cuentas.put("67890", new CuentaBuilder()
                .setNumCuenta("67890")
                .setPin("2222")
                .setSaldo(37892.21)
                .setTitular("Roberto Garnica")
                .build());

        cuentas.put("121221", new CuentaBuilder()
                .setNumCuenta("121221")
                .setPin("3333")
                .setSaldo(100000)
                .setTitular("Julian Quiñones")
                .build());
    }

    /**
     * Validar que las credenciales sean correctas
     *
     * @param numCuenta numero de la cuenta
     * @param pin contraseña de la cuenta (4 digitos)
     * @return {@code true} si la cuenta existe, {@code false} si la cuenta no existe
     */
    public boolean autenticar(String numCuenta, String pin ) {
        Cuenta cuenta = cuentas.get(numCuenta);
        if(cuenta != null && cuenta.validarPin(pin)){
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    /**
     *
     * @return la cuenta actualmente identificada, o {@code null} si la sesion ha sido cerrada
     */
    public Cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    /**
     * Consulta el saldo de la cuenta en uso
     * @return saldo de la cuenta actual
     */
    public double consultarSaldo(){
        return this.cuentaActual != null ? this.cuentaActual.getSaldo() : 0;
    }

    /**
     * Se realiza un retiro del saldo de la cuenta en sesion
     * @param cantidad monto a retirar
     * @return {@code true} si el retiro fue exitoso, {@code false} si el retiro no se completo
     */
    public boolean realizarRetiro(double cantidad){
        return cuentaActual != null && this.cuentaActual.retirar(cantidad);
    }

    /**
     * Depositar fondos a la cuenta en sesion
     * @param cantidad monto a ingresar a la cuenta, debe ser &gt;0
     * @return {@code true} si el deposito fue exitoso. {@code false} si el deposito falló
     */
    public boolean realizarDeposito(double cantidad){
        if(cuentaActual != null && cantidad > 0){
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    /**
     * Verificar que la cuenta existe en la base de datos
     * @param numCuenta numero de la cuenta
     * @return {@code true} si la cuenta existe, {@code false} si la cuenta no fue encontrada
     */
    public boolean cuentaExiste(String numCuenta){
        return cuentas.containsKey(numCuenta);
    }

    /**
     * Realiza una transferencia de fondos a una cuenta diferente
     * @param numCuentaDestino numero de cuenta del destinatario
     * @param cantidad monto a transferir, debe ser &gt;0 y &le;saldo
     * @return {@code true} si la transferencia se realizó, {@code false} si la tranferencia no fue exitosa
     */
    public boolean realizarTransferencia(String numCuentaDestino, double cantidad){
        if(cuentaActual == null) return false;

        Cuenta destino = cuentas.get(numCuentaDestino);
        if(destino == null) return false;
        if(destino.getNumCuenta().equals(cuentaActual.getNumCuenta())) return false;

        return cuentaActual.transferir(cantidad,destino);
    }

    /**
     * Cambiar el pin de la cuenta en sesion
     * @param pinActual pin actual de la cuenta
     * @param pinNuevo nuevo pin deseado
     * @return {@code true} si el pin fue actualizado, {@code false} si el pin no se pudo actualizar
     */
    public boolean cambiarPin(String pinActual, String pinNuevo ){
        if(cuentaActual == null) return false;
        return cuentaActual.cambiarPin(pinActual,pinNuevo);
    }


    //Tarea: definir el metodo para transferir
}
