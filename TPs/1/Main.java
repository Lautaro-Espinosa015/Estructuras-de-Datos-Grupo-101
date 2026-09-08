import java.util.Locale; //permite usar acentos etc
import java.util.Scanner; // ingresar texto

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { //try catch + iniciar el objeto del scanner
            Paciente paciente = new Paciente(); // lo mismo del paciente

            paciente.setNombre(leerTextoNoVacio(scanner, "Ingrese el nombre: "));
            paciente.setDni(leerTextoNoVacio(scanner, "Ingrese el DNI: "));
            paciente.setPeso(leerNumeroPositivo(scanner, "Ingrese el peso en kg: "));
            paciente.setAltura(leerNumeroPositivo(scanner, "Ingrese la altura en metros: "));
            // ingresar datos del paciente
            double imc = paciente.calcularImc(); // asignar imc con el metodo del paciente calcularImc()
            System.out.println("\n--- Datos del paciente ---");
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("DNI: " + paciente.getDni());
            System.out.printf(Locale.US, "IMC: %.2f%n", imc);
            System.out.println("Estado nutricional: " + paciente.obtenerEstadoNutricional());
            // salida
        }
    }

    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El valor no puede estar vacío. Intente nuevamente.");
        } // funcion para que detecte texto vacio
    }

    private static double leerNumeroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().replace(',', '.');

            try {
                double numero = Double.parseDouble(entrada);
                if (Double.isFinite(numero) && numero > 0) {
                    return numero;
                }
                System.out.println("Ingrese un número positivo mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un valor numérico válido.");
            } // funcion que evita que el usuario coloque numeros negativos
        }
    }
}

