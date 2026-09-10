package ar.edu.unju.ed2026.TP4;
import java.util.Random;
import java.util.Scanner;
/*

Realizar el ingreso de N enteros (simulando niveles de señal) en una cola. Además, pedir al usuario el
ingreso de un número que será considerado como umbral máximo. Los elementos de la cola que excedan el
valor umbral deberán ser retirados y reubicados al final de la misma. Mostrar los elementos de la cola
después de realizada la reubicación. Contar y mostrar la cantidad de números de la cola que no superen al
umbral ingresado por el usuario. Finalmente, crear una nueva cola que solo contenga aquellos números que
no superen dicho umbral.
En el programa principal (main) se debe:
a) Solicitar al usuario la cantidad de elementos N y generar los números aleatoriamente.
b) Solicitar el valor del umbral máximo.
c) Ejecutar las operaciones indicadas y mostrar el estado de la cola original tras la reubicación, la cantidad
de elementos por debajo del umbral, y los elementos de la nueva cola generada.
d) Cuando se recorran los elementos de la cola esta debe mantenerse sin modificaciones.

*/
public class EJ01_TP4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int cantidad = 0;
        
        do {
            System.out.print("Ingrese el tamaño de la cola (de 1 a 100): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Error: debe ingresar un número entero. Intente nuevamente: ");
                scanner.next();
            }
            cantidad = scanner.nextInt();

            if (cantidad < 1 || cantidad > 100) {
                System.out.println("Error: la cantidad debe estar entre 1 y 100.");
            }
        } while (cantidad < 1 || cantidad > 100);

        Queue<Integer> colaOriginal = new Queue<>(cantidad);
        System.out.println("\nSe creo una cola con " + cantidad + " espacios.");
        
        // Generamos los números aleatoriamente (ej: del 1 al 100)
        for (int i = 0; i < cantidad; i++) {
            int numeroAleatorio = random.nextInt(100) + 1;
            colaOriginal.offer(numeroAleatorio);
        }
        System.out.println("Estado de la cola original: " + colaOriginal.toString());


        System.out.print("\nIngrese el número umbral máximo: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Error: debe ingresar un número entero. Intente de nuevo: ");
            scanner.next();
        }
        int umbral = scanner.nextInt();


        Queue<Integer> colaNueva = new Queue<>(cantidad);
        Queue<Integer> colaTemporal = new Queue<>(cantidad); 
        int contadorMenores = 0;

       
        for (int i = 0; i < cantidad; i++) {
            int elementoActual = colaOriginal.pool(); 

            if (elementoActual <= umbral) {
             
                contadorMenores++;
                colaOriginal.offer(elementoActual); 
                colaNueva.offer(elementoActual);    
            } else {
               
                colaTemporal.offer(elementoActual);
            }
        }

    
        while (!colaTemporal.isEmpty()) {
            colaOriginal.offer(colaTemporal.pool());
        }

        System.out.println("\n--- RESULTADOS FINALES ---");
        System.out.println("Cola original tras reubicar mayores al final: " + colaOriginal.toString());
        System.out.println("Cantidad de elementos que NO superan el umbral: " + contadorMenores);
        System.out.println("Nueva cola generada (solo menores o iguales): " + colaNueva.toString());

        scanner.close();
    }




}

/*

a) Si la cola original tiene los elementos [10, 50, 20, 5] (siendo 10 el primero) y el umbral es 15. Sin
ejecutar el programa: ¿Cuál será el orden exacto de la cola después de la reubicación?

[10,5,50,20]

b) ¿Qué ocurre si todos los elementos de la cola superan el umbral máximo? ¿El programa entra en un
bucle infinito o se reordena correctamente? Justifique cómo evitar un bucle infinito basándose en el
tamaño de la cola.

Use una comparacion <= por lo cual al todos los numeros superar el umbral la cola se reconstruye quedando exactamente igual

c) Para "reubicar al final", se debe sacar el elemento y volver a meterlo. ¿Por qué es importante usar un
bucle controlado por el tamaño original de la cola?

Porque al reinsertar elementos en la misma cola, su tamaño cambia dinámicamente. Un bucle controlado 
por el tamaño original evita que entremos en un bucle infinito al volver a procesar los elementos que ya fueron reubicados al final.

d) Si ahora se pidiera que los elementos que superan el umbral se reubiquen en una cola nueva en lugar
de al final de la misma cola, ¿qué cambios se deberían hacer en la lógica del algoritmo?

cuando se detecte que el elemento supera el umbral (previamente inicializar una cola de los que superan el umbral) y sumarlos alli

*/