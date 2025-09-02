package Views;

import java.util.Scanner;

public class CajeroView {
    private Scanner sc;

    public CajeroView() {
        sc = new Scanner(System.in);
    }

    public void mostrarBienvenida(){
        System.out.println("=========== Bienvenidos ==========");
    }

    public String solicitarNumCuenta(){
        System.out.println("Ingresa tu numero de cuenta: ");
        return sc.nextLine();
    }

    public String solicitarPin(){
        System.out.println("Ingresa una pin: ");
        return sc.nextLine();
    }

    public void mostrarMenuPrincipal(String titular){
        System.out.println("=========== Bienvenido " + titular + " ===========");
        System.out.println("1. Consultar saldo");
        System.out.println("2.Retirar");
        System.out.println("3. Depositar");
        // definir los comportamientos restantes
        System.out.println("9. Salir");
    }

    public int leerOpcion(){
        try{
            return Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }

    public void mostrarSaldo(double saldo){
        System.out.println("=========== Saldo ===========");
        System.out.println("Tu saldo actual es: $" + saldo);
    }

    public double solicitarCantidad(String operacion){
        System.out.println("Ingresa la cantidad a " + operacion + ": ");
        try{
            return Double.parseDouble(sc.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje){
        System.out.println("=========== " + mensaje + " ===========");
    }

    //completar comportamientos restantes

    //Tarea: personalizar mensajes de error y exito
    //Tarea: metodo para salir cerrar el scanner
}
