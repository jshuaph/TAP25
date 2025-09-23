package Utils;

import Model.CajeroModel;
import Views.CajeroView;

public class RetirarStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("Retirar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad de saldo");
            return false;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Retiro exitoso de " + cantidad);
            return true;
        } else {
            view.mostrarMensaje("Saldo insuficiente");
            return false;
        }
    }
}

