package Utils;

/**
 * Clase generica que guarda algun elemento y lo retorna como un String
 * @param <T> tipo generico
 */
public class Contenedor<T> {
    private T valor;

    public Contenedor(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public boolean estaVacio() {
        return valor == null;
    }

    @Override
    public String toString() {
        return (valor == null) ? "Contenedor vacío" : "" + valor;
    }
}
