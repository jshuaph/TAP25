package Utils;

import Model.CajeroModel;
import Views.CajeroView;

public interface OperacionStrategy {
    boolean ejecutar(CajeroModel model, CajeroView view);
}
