package Controllers;

import Model.CajeroModel;
import Views.CajeroView;

/**
 * Controlador principal intermediario entre la vista
 * y el modelo del cajero automático.
 *
 * <p>Gestiona el flujo:
 * <ol>
 *   <li>Autenticación</li>
 *   <li>Menú de operaciones</li>
 *   <li>Ejecución de acciones (saldo, retiro, depósito, transferencia, cambio de PIN)</li>
 * </ol>
 * </p>
 *
 */
public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    /**
     * Crea las instancias de las clases model y view
     * @param model instancia de {@link CajeroModel}
     * @param view instancia de {@link CajeroView}
     */
    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    /**
     * Inicia el flujo del cajero: bienvenida, autenticación y menú principal.
     */
    public void inicarCajero(){
        view.mostrarBienvenida();
        while(sistemaActivo){
            if(autenticarUsuario()){
                ejecutarMenuPrincipal();
            }else{
                view.mostrarMensaje("Creedenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }

    /**
     * Validar la existencia del usuario
     * @return {@code true} si la cuenta existe, {@code false} si las cuenta no fue encontrada
     */
    private boolean autenticarUsuario(){
        String numeroCuenta = view.solicitarNumCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    /**
     * Muetsra el saldo de la cuenta en sesion
     */
    public void consultarSaldo(){
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }

    /**
     * Retiro de fondos de la cuenta, si es que hay fondos suficientes
     */
    public void retirarSaldo(){
        double cantidad = view.solicitarCantidad("Retirar");
        if(cantidad <= 0){
            view.mostrarMensaje("Error en la cantidad de saldo");
            return;
        }
        if(model.realizarRetiro(cantidad)){
            view.mostrarMensaje("Retiro exitoso de " + cantidad);
        }else{
            view.mostrarMensaje("Saldo insuficiente");
        }
    }

    /**
     * Depositar fondos a la cuenta
     */
    public void depositarSaldo(){
        double cantidad = view.solicitarCantidad("Depositar");
        if(cantidad <= 0){
            view.mostrarMensaje("Error en la cantidad de saldo");
            return;
        }
        if(model.realizarDeposito(cantidad)){
            view.mostrarMensaje("Deposito exitoso de " + cantidad);
            view.mostrarMensaje("Su saldo es de " + model.getCuentaActual().getSaldo());
        }
    }

    /**
     * Realizar una transferencia a otra cuenta
     */
    public void realizarTransferencia(){
        String destino = view.solicitarDestinatario();
        double monto = view.solicitarCantidad("Transferir");
        if(model.realizarTransferencia(destino, monto)){
            view.mostrarMensaje("Transferencia exitosa por " + monto);
        }else{
            view.mostrarMensaje("No se pudo realizar la transferencia");
        }
    }

    /**
     * Cambia el pin de la cuenta en sesion
     */
    public void cambiarPin(){
        String actual = view.solicitarPinActual();
        String nuevo = view.solicitarPinNuevo();
        if(model.cambiarPin(actual, nuevo)){
            view.mostrarMensaje("Cambio de pin exitoso");
        }else{
            view.mostrarMensaje("No se pudo cambiar el pin. Verifique los datos y formato");
        }
    }

    /**
     *Bucle de menú principal: atiende la opción seleccionada por el usuario
     * y ejecuta la operación correspondiente hasta que se elija salir.
     */
    private void ejecutarMenuPrincipal(){
        boolean sessionActiva = true;
        while(sessionActiva){
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch(opcion){
                case 1:
                    consultarSaldo();
                break;
                case 2:
                    retirarSaldo();
                break;
                case 3:
                    depositarSaldo();
                break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    cambiarPin();
                    break;
                case 9:
                    view.cerrar();
                    sessionActiva = false;
                    sistemaActivo = false;
                    break;
                default:
                    view.mostrarMensaje("Opcion invalida");
                    break;
            }
        }
    }
}
