package ar.edu.unju.ed2026.TP1;
import java.util.Scanner;

public class Punto1 {

    public static void main(String[] args) {
        String nombreEmpleado;
        int horasTrabajadas;
        double valorHora;

        try (Scanner entrada = new Scanner(System.in)) {
            System.out.print("Ingrese nombre del empleado: ");
            nombreEmpleado = entrada.nextLine().trim();

            System.out.print("Ingrese horas trabajadas en la semana: ");
            horasTrabajadas = entrada.nextInt();

            System.out.print("Ingrese valor de la hora trabajada: ");
            valorHora = entrada.nextDouble();
        }

        // Validación básica de entrada
        if (horasTrabajadas < 0 || valorHora < 0) {
            System.out.println("Error: Las horas y el valor hora deben ser valores positivos.");
            return;
        }

        // 1. Cálculo de Salario Bruto
        double salarioBruto = calcularSalarioBruto(horasTrabajadas, valorHora);

        // 2. Determinación del porcentaje de descuento
        double porcentajeDescuento;
        if (salarioBruto <= 1_000_000) {
            porcentajeDescuento = 9.0;
        } else if (salarioBruto <= 3_000_000) {
            porcentajeDescuento = 12.0;
        } else {
            porcentajeDescuento = 15.0;
        }

        // 3. Cálculo de Descuento y Salario Neto
        double montoDescuento = calcularMontoDescuento(salarioBruto, porcentajeDescuento);
        double salarioNeto = salarioBruto - montoDescuento;

        // 4. Salida por pantalla
        System.out.println("\n==========================================");
        System.out.println("Empleado: " + nombreEmpleado);
        System.out.printf("Salario Bruto: $%,.2f%n", salarioBruto);
        System.out.printf("Descuento aplicado (%.0f%%): $%,.2f%n", porcentajeDescuento, montoDescuento);
        System.out.printf("Salario Neto a cobrar: $%,.2f%n", salarioNeto);
        System.out.println("==========================================");
    }

    /**
     * Calcula el salario bruto considerando recargo del 50% para horas
     * excedentes a 40.
     */
    public static double calcularSalarioBruto(int horasTrab, double valorHora) {
        if (horasTrab > 40) {
            int horasExtras = horasTrab - 40;
            double pagoNormal = 40 * valorHora;
            double pagoExtra = horasExtras * (valorHora * 1.5);
            return pagoNormal + pagoExtra;
        } else {
            return horasTrab * valorHora;
        }
    }

    /**
     * Calcula el monto retenido en concepto de descuento según el porcentaje.
     */
    public static double calcularMontoDescuento(double sueldoBruto, double porcentaje) {
        return sueldoBruto * (porcentaje / 100.0);
    }
}
