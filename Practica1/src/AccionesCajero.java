import java.util.ArrayList;
import java.util.Scanner;

public class AccionesCajero {
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioActual;
    private Scanner sc;
    private int intentos;
    private boolean salir;

    public AccionesCajero() {
        usuarios = new ArrayList<>();
        sc = new Scanner(System.in);
        intentos = 0;
        salir = false;

        usuarios.add(new Usuario("1234", "Juan", 1000.0));
        usuarios.add(new Usuario("5678", "Maria", 2500.0));
    }

    public void iniciar() {
        autenticarUsuario();
        while (!salir && usuarioActual != null) {
            mostrarMenu();
        }
    }

    private void autenticarUsuario() {
        while (intentos < 3 && usuarioActual == null) {
            System.out.print("Ingrese su PIN: ");
            String pin = sc.nextLine();

            for (Usuario u : usuarios) {
                if (u.getPin().equals(pin)) {
                    usuarioActual = u;
                    break;
                }
            }

            if (usuarioActual == null) {
                System.out.println("PIN incorrecto.");
                intentos++;
            }
        }

        if (intentos >= 3) {
            System.out.println("Demasiados intentos fallidos.");
        }
    }

    private void mostrarMenu() {
        System.out.println("\nBienvenido, " + usuarioActual.getNombre());
        System.out.println("1. Ver saldo");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Depositar dinero");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Su saldo es: $" + usuarioActual.getSaldo());
                break;
            case 2:
                System.out.print("Ingrese cantidad a retirar: ");
                double retiro = sc.nextDouble();
                sc.nextLine();
                if (usuarioActual.retirar(retiro)) {
                    System.out.println("Retiro exitoso. Nuevo saldo: $" + usuarioActual.getSaldo());
                } else {
                    System.out.println("Fondos insuficientes.");
                }
                break;
            case 3:
                System.out.print("Ingrese cantidad a depositar: ");
                double deposito = sc.nextDouble();
                sc.nextLine();
                usuarioActual.depositar(deposito);
                System.out.println("Depósito exitoso. Nuevo saldo: $" + usuarioActual.getSaldo());
                break;
            case 4:
                salir = true;
                System.out.println("Gracias por usar el cajero.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
}