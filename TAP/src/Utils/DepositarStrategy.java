package Utils;

import Model.CajeroModel;
import Views.CajeroView;

public class DepositarStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("Depositar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad de saldo");
            return false;
        }
        if (model.realizarDeposito(cantidad)) {
            view.mostrarMensaje("Deposito exitoso de " + cantidad);
            view.mostrarMensaje("Su saldo es de " + model.getCuentaActual().getSaldo());
            return true;
        }
        return false;
    }
}

