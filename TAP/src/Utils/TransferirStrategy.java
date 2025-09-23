package Utils;

import Model.CajeroModel;
import Views.CajeroView;

public class TransferirStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(CajeroModel model, CajeroView view) {
        String destino = view.solicitarDestinatario();
        double monto = view.solicitarCantidad("Transferir");
        if (model.realizarTransferencia(destino, monto)) {
            view.mostrarMensaje("Transferencia exitosa por " + monto);
            return true;
        } else {
            view.mostrarMensaje("No se pudo realizar la transferencia");
            return false;
        }
    }
}

