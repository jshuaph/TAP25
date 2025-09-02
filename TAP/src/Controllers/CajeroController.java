package Controllers;

import Model.CajeroModel;
import Views.CajeroView;

public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

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

    private boolean autenticarUsuario(){
        String numeroCuenta = view.solicitarNumCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    public void consultarSaldo(){
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }

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

    public void depositarSaldo(){
        double cantidad = view.solicitarCantidad("Depositar");
        if(cantidad <= 0){
            view.mostrarMensaje("Error en la cantidad de saldo");
            return;
        }
        if(model.realizarDeposito(cantidad)){
            view.mostrarMensaje("Deposito exitoso de " + cantidad);
        }else{
            view.mostrarMensaje("Saldo insuficiente");
        }
    }

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
                case 9:
                    sessionActiva = false;
                    sistemaActivo = false;
                    break;
                default:
                    break;
            }
        }
    }
}
