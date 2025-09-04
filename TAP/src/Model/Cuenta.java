package Model;

/**
 * Representa una cuenta bancaria básica con operaciones de depósito,
 * retiro, transferencia y cambio de PIN.
 *
 * <p>Incluye validaciones mínimas de negocio:
 * <ul>
 *   <li>Montos positivos</li>
 *   <li>Fondos suficientes para retiro/transferencia</li>
 *   <li>Formato de PIN de 4 dígitos y verificación del PIN actual</li>
 * </ul>
 *
 */
public class Cuenta {
    private String numCuenta;
    private String pin;
    private double saldo;
    private String titular;

    /**
     * Crea una cuenta nueva
     *
     * @param numCuenta numero de cuenta
     * @param pin contraseña para ingresar, con formato de 4 digitos
     * @param saldoInicial saldo inicial de la cuenta
     * @param titular nombre del dueño de la cuenta
     */
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

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    // Reglas del negocio

    /**
     *Valida que el pin sea correcto dentro de las cuentas existentes
     *
     * @param pinIngresado contraseña de la cuenta
     * @return {@code true} si <code>pin</code> es el mismo al <code>pinIngresado</code>,
     * {@code false} si <code>pin</code> y <code>pinIngresado</code> son diferentes
     */
    public boolean validarPin(String pinIngresado){
        return this.pin.equals(pinIngresado);
    }

    /**
     *Retira una cantidad de la cuenta si hay fondos suficientes
     *
     * @param cantidad monto a retirar, debe ser &gt;0
     * @return {@code true} si <code>cantidad</code> a retirar es valida dentro de las condiciones,
     * {@code false} si la cantidad a retirar es negativa o no hay fondos suficientes
     */
    public boolean retirar(double cantidad){
        if(cantidad > 0 && cantidad <= saldo){
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    /**
     * Deposita una cantidad positiva a la cuenta
     * @param cantidad monto a retirar, debe ser &gt;0
     */
    public void depositar(double cantidad){
        if(cantidad > 0){
            saldo += cantidad;
        }
    }

    //Tarea: Diseñar los comportamientos restantes: Transferir, cambiar pin...

    /**
     *Transfiere fondos desde la cuenta actual hacia un destinatario
     *
     * @param cantidad monto a transferir, debe ser &gt;0 y &le;saldo
     * @param destino numero de cuenta del destintario, no puede ser {@code null} ni la misma cuenta
     * @return {@code true} si la transferencia se realizo,
     * {@code false} si la transferencia no se pudo procesar
     */
    public boolean transferir(double cantidad, Cuenta destino){
        if(destino == null) return false;
        if(destino.getNumCuenta().equals(this.numCuenta))return false;
        if(cantidad <= 0 || cantidad > saldo) return false;

        this.saldo -= cantidad;
        destino.saldo += cantidad;
        return true;
    }

    /**
     * Cambia el pin de la cuenta actual validando el pin actual y el formato del nuevo pin
     *
     * @param pinActual Pin actual de la cuenta
     * @param pinNuevo Pin nuevo, debe ser de 4 digitos
     * @return {@code true} si el pin nuevo es valido, {@code false} si el pin nuevo no cumple con las condiciones
     */
    public boolean cambiarPin(String pinActual, String pinNuevo){
       if(pinActual == null || pinNuevo == null)return false;
       if(pinActual.equals(pinNuevo))return false;
       if(!this.pin.equals(pinActual))return false;
       if(!pinNuevo.matches("\\d{4}")) return false;

       this.pin = pinNuevo;
       return true;
    }
}
