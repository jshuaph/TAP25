package Views;

import java.util.Scanner;

/**
 * Vista de consola para interactuar con el usuario.
 * <p>Encapsula la entrada/salida estándar (System.in/out) y provee
 * métodos para solicitar datos y mostrar mensajes.</p>
 *
 */
public class CajeroView {
    private Scanner sc;

    public CajeroView() {
        sc = new Scanner(System.in);
    }

    /**
     * Mensaje de bienvenida al usuario
     */
    public void mostrarBienvenida(){
        System.out.println("=========== Bienvenidos ==========");
    }

    /**
     * Solicita el numero de la cuenta del usuario
     * @return numero de cuenta ingresado, sin espacios
     */
    public String solicitarNumCuenta(){
        System.out.print("Ingresa tu numero de cuenta: ");
        return sc.nextLine();
    }

    /**
     * Solicita la contraseña de la cuenta
     * @return contraseña ingresada, sin espacios
     */
    public String solicitarPin(){
        System.out.print("Ingresa tu pin: ");
        return sc.nextLine();
    }

    /**
     * Desplega el menu de operaciones
     * @param titular Nombre del dueño de la cuenta
     */
    public void mostrarMenuPrincipal(String titular){
        System.out.println("=========== Bienvenido " + titular + " ===========");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar");
        System.out.println("3. Depositar");
        System.out.println("4. Transferir");
        System.out.println("5. Cambiar PIN");
        System.out.println("9. Salir");
        System.out.print("Ingresa una opcion: ");
    }

    /**
     * Lee la opcion del menu ingresada por consola
     * @return opcion numerica, -1 si la opcion no existe dentro del menu
     */
    public int leerOpcion(){
        try{
            return Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }

    /**
     * Muestra el saldo de la cuenta
     * @param saldo fondos de la cuenta
     */
    public void mostrarSaldo(double saldo){
        System.out.println("=========== Saldo ===========");
        System.out.println("Tu saldo actual es: $" + saldo);
    }

    /**
     * Solicita por la cantidad y el movimiento a realizar
     * @param operacion movimiento a realizar, ej. "Retirar", "Depositar", "Transferir"
     * @return monto como double, -1 si la entrada no es valida
     */
    public double solicitarCantidad(String operacion){
        System.out.print("Ingresa la cantidad a " + operacion + ": ");
        try{
            return Double.parseDouble(sc.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }

    /**
     * Mensaje generico por consola
     * @param mensaje entrada del mensaje a desplegar
     */
    public void mostrarMensaje(String mensaje){
        System.out.println("=========== " + mensaje + " ===========");
    }

    //completar comportamientos restantes

    //Tarea: personalizar mensajes de error y exito
    //Tarea: metodo para salir cerrar el scanner

    /**
     * Preguntar por el numero de cuenta destinatario al hacer una transferencia
     * @return numero de cuenta sin espacios
     */
    public String solicitarDestinatario(){
        System.out.print("Escriba el numero de cuenta del destinatario: ");
        return sc.nextLine().trim();
    }

    /**
     * Preguntar por el pin actual de la cuenta al hacer cambio de pin
     * @return pin ingresado sin espacios
     */
    public String solicitarPinActual(){
        System.out.print("Digite su pin actual: ");
        return sc.nextLine().trim();
    }

    /**
     * Preguntar por el pin nuevo de la cuenta al hacer cambio de pin
     *@return pin ingresado sin espacios
     */
    public String solicitarPinNuevo(){
        System.out.print("Digite su pin Nuevo: ");
        return sc.nextLine().trim();
    }

    /**
     * Cierra el scanner
     */
    public void cerrar(){
        sc.close();
    }
}
