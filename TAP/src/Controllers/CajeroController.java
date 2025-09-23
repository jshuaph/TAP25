package Controllers;

import Model.CajeroContext;
import Model.CajeroModel;
import Model.Cuenta;
import Utils.*;
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
    private CajeroContext cajero;
    private boolean sistemaActivo;

    /**
     * Crea las instancias de las clases model y view
     * @param model instancia de {@link CajeroModel}
     * @param view instancia de {@link CajeroView}
     */
    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.cajero = new CajeroContext();
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
     * Obtiene el nombre del titular haciendo uso de la clase generica
     * @return nombre del titular como un String
     */
    public String obtenerTitular(){
        Contenedor<String> titular = new Contenedor<>(model.getCuentaActual().getTitular());
        return titular.toString();
    }

    public void mostrarBienvenida(){
        view.mostrarBienvenida();
    }

    /**
     * Muetsra el saldo de la cuenta en sesion
     */
    public void consultarSaldo(){
        cajero.setStrategy(new ConsultarSaldoStrategy());
        cajero.ejecutarOperacion(model, view);
    }

    /**
     * Retiro de fondos de la cuenta, si es que hay fondos suficientes
     */
    public void retirarSaldo(){
        cajero.setStrategy(new RetirarStrategy());
        cajero.ejecutarOperacion(model, view);
    }

    /**
     * Depositar fondos a la cuenta
     */
    public void depositarSaldo(){
        cajero.setStrategy(new DepositarStrategy());
        cajero.ejecutarOperacion(model, view);
    }

    /**
     * Realizar una transferencia a otra cuenta
     */
    public void realizarTransferencia(){
        cajero.setStrategy(new TransferirStrategy());
        cajero.ejecutarOperacion(model, view);
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
            view.mostrarMenuPrincipal(obtenerTitular());
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
