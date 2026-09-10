package ar.edu.unju.ed2026.TP4;
import java.util.Scanner;

/*

3) Un centro de atención telefónica está procesando una secuencia de llamadas recibidas. Cada llamada se
representa con el nombre del cliente y se almacena en una cola de tipo String. Se desea realizar un
procesamiento de las llamadas en función de la longitud del nombre del cliente.
A través de un programa se debe:
Procesar la cola de entrada para dividir las llamadas en tres nuevas colas:
● Una cola de clientes con nombres cortos (de uno a cuatro letras).
● Una cola de clientes con nombres medianos (de cinco a ocho letras).
● Una cola de clientes con nombres largos (nueve o más letras).
Para cada una de estas colas, se debe: Luego realizar lo siguiente
a) Calcular la cantidad de llamadas (clientes) que contiene cada cola.
b) Buscar y mostrar el nombre más largo de cada cola.
c) Unir las tres colas en una sola, primero la cola de nombres cortos, luego la de nombres medianos y al
final la cola de nombres largos. Mostrar la cola resultante.
d) Cuando se recorran los elementos de la cola esta debe mantenerse sin modificaciones.
En el programa principal (main) se debe:
a) Solicitar al usuario que ingrese una serie de nombres de clientes (hasta que ingrese una palabra clave
como "FIN").
b) Encolar las llamadas y ejecutar los métodos solicitados, mostrando por consola los resultados de cada
una de las tres colas.
Indicaciones:
Este ejercicio necesita del objeto scanner para ingresar datos por la consola o teclado, se espera que el
código controle los problemas que normalmente ocurren al operar con la consola o teclado.
Se espera una correcta modularización entre el código que realiza el ingreso y validación de los datos
respecto del código que hace lo que se solicita en el ejercicio.


*/

public class EJ03_TP4 {

    public static void main(String[] args) {
        // Capacidad inicial
        int capacidadMaxima = 100;

        // a) Solicitar al usuario que ingrese los nombres hasta "FIN"
        Queue<String> llamadas = ingresarLlamadas(capacidadMaxima);


        if (llamadas.isEmpty()) {
            System.out.println("No se registraron llamadas en el sistema.");
            return;
        }


        procesarLlamadas(llamadas);
    }

    /**
     * Módulo para ingresar y validar los datos usando Scanner.
     */
    public static Queue<String> ingresarLlamadas(int capacidad) {

        // Al usar varargs, Java internamente pasa un String[] vacío como 'dummy' de forma segura.
        Queue<String> colaLlamadas = new Queue<String>(capacidad);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE ATENCIÓN TELEFÓNICA ===");
        System.out.println("Ingrese los nombres de los clientes (escriba 'FIN' para terminar):");

        try {
            while (true) {
                System.out.print("> Nombre: ");
                String entrada = scanner.nextLine().trim();

                // Condición de salida
                if (entrada.equalsIgnoreCase("FIN")) {
                    break;
                }

                // Validación simple de datos nulos o vacíos
                if (entrada.isEmpty()) {
                    System.out.println("Error: El nombre no puede estar vacío. Intente de nuevo.");
                    continue;
                }

                try {
                    // Intentamos encolar. Si falla por capacidad, saltará IllegalStateException
                    colaLlamadas.add(entrada);
                } catch (IllegalStateException e) {
                    System.out.println("Advertencia: " + e.getMessage() + " Capacidad máxima alcanzada.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado al leer la consola: " + e.getMessage());
        } finally {
            scanner.close();
        }

        return colaLlamadas;
    }

    /**
     * Módulo para categorizar las llamadas por longitud de nombre.
     */
    public static void procesarLlamadas(Queue<String> llamadasOriginal) {
        // Creamos las tres sub-colas con tamaño máximo igual al de la cola original
        int maxSize = llamadasOriginal.size();
        Queue<String> cortos = new Queue<String>(maxSize);
        Queue<String> medianos = new Queue<String>(maxSize);
        Queue<String> largos = new Queue<String>(maxSize);

        // Recorremos la cola original sin modificarla gracias al QueueIterator
        for (String cliente : llamadasOriginal) {
            int longitud = cliente.length();

            if (longitud >= 1 && longitud <= 4) {
                cortos.add(cliente);
            } else if (longitud >= 5 && longitud <= 8) {
                medianos.add(cliente);
            } else { // 9 o más letras
                largos.add(cliente);
            }
        }

        // a y b) Analizar y mostrar información de cada cola
        System.out.println("\n=== ANÁLISIS DE LAS COLAS ===");
        analizarYMostrar("Nombres Cortos (1 a 4 letras)", cortos);
        analizarYMostrar("Nombres Medianos (5 a 8 letras)", medianos);
        analizarYMostrar("Nombres Largos (9+ letras)", largos);


        Queue<String> colaResultante = cortos.union(medianos).union(largos);

        System.out.println("\n=== RESULTADO DE LA UNIÓN DE COLAS ===");
        System.out.println("Cola Final unida: " + colaResultante.toString());

        // Verificación del punto d)
        System.out.println("\n(Verificación) Cola Original intacta: " + llamadasOriginal.toString());
    }

    /**
     * Módulo auxiliar para realizar los cálculos por cada cola sin repetir código.
     */
    public static void analizarYMostrar(String titulo, Queue<String> cola) {
        System.out.println("\n--- " + titulo + " ---");

        // a) Calcular y mostrar la cantidad
        System.out.println("Cantidad de clientes (llamadas): " + cola.size());

        // b) Buscar el nombre más largo
        String nombreMasLargo = "";
        for (String nombre : cola) {
            if (nombre.length() > nombreMasLargo.length()) {
                nombreMasLargo = nombre;
            }
        }

        if (cola.isEmpty()) {
            System.out.println("Nombre más largo: [N/A - Cola Vacía]");
        } else {
            System.out.println("Nombre más largo: " + nombreMasLargo + " (" + nombreMasLargo.length() + " letras)");
        }
    }

}

/*

a) Al dividir en tres colas, la cola original ¿debería quedar vacía al final del proceso o mantener sus
elementos? Justifique basándose en el comportamiento estándar de desencolado.

Al sacar elementos de la cola la estamos "destruyendo" por lo que al final del proceso si "queda" vacia (pero en el programa la reconstruimos)

b) ¿Qué sucede si no hay clientes con nombres cortos (1-4 letras)? ¿Cómo maneja el programa el cálculo
del "nombre más largo" si la cola resultante está vacía para evitar una excepción?

Para el codigo que hemos resuelto en caso de que encuentre una cola vacia simplemente es capturado el caso de uso y se informa al usuario mediante
un println

c) Según lo planteado el punto c) ¿Cómo quedan las tres colas? ¿Hace falta usar colas auxiliares?

Si hemos necesitado una cola auxiliar para poder armar la union entre las colas.

*/