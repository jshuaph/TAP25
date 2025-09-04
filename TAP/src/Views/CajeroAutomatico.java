package Views;

import Controllers.CajeroController;
import Model.CajeroModel;

/**
 * Punto de entrada de la aplicación de consola del cajero automático.
 */
public class CajeroAutomatico {
    public static void main(String[] args) {
        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        CajeroController controller = new CajeroController(model, view);

        controller.inicarCajero();
        
    }
}
