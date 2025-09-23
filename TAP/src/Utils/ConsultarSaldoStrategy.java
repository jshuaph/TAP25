package Utils;

import Model.CajeroModel;
import Views.CajeroView;

public class ConsultarSaldoStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(CajeroModel model, CajeroView view) {
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
        return true;
    }
}

