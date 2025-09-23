package Model;

import Utils.OperacionStrategy;
import Views.CajeroView;

public class CajeroContext {
    private OperacionStrategy strategy;

    public void setStrategy(OperacionStrategy strategy) {
        this.strategy = strategy;
    }

    public void ejecutarOperacion(CajeroModel model, CajeroView view) {
        if (strategy != null) {
            strategy.ejecutar(model, view);
        } else {
            view.mostrarMensaje("No se ha seleccionado ninguna operación.");
        }
    }
}
