package ar.edu.unju.ed2026.TP4;

import ar.edu.unju.ed2026.Utils.ArrayUtils;
import ar.edu.unju.ed2026.Utils.Helper;
import ar.edu.unju.ed2026.Utils.QueueUtils;

/**
 * Ejercicio 4: Gestión de Tickets de Soporte Técnico — Clase Ticket
Escribir un programa para gestionar una cola de tickets de soporte técnico. Cada ticket debe
representarse mediante un objeto con los campos: idTicket, departamento (ej. "Redes",
"Software", "Hardware") y nivelUrgencia (entre 1 y 5).
Consigna:
• a) A partir de una cola que contiene N tickets, generar una cola que contenga todos aquellos
tickets cuyo departamento coincida con uno indicado por el usuario.
• b) Calcular el nivel promedio de urgencia de todos los tickets de la cola original.
• c) Identificar y mostrar (si existe) el primer ticket crítico de nivel 5.
• d) Generar un arreglo con los IDs de los tickets que tengan un nivel de urgencia 4 o 5.
• e) Cuando se recorran los elementos de la cola esta debe mantenerse sin modificaciones.
 */

public class EJE4_T4 {
    public static void main(String[] args) {

        int cantidadTickets = Helper.nextInteger("Ingrese la cantidad de tickets a procesar", 0);
        Queue<Ticket> queueTickets = new Queue<>(cantidadTickets);

        for (int i = 0; i < cantidadTickets; i++) {
            System.out.println("Ingrese los datos del ticket " + (i + 1) + ":");
            Ticket ticket = getTicket();
            queueTickets.add(ticket);
        }

        System.out.println("Mostrar los tickets ingresados:");
        QueueUtils.displayQueue(queueTickets);
        System.out.println("--------------------------------------------------");
        System.out.println("Filtrar tickets por departamento:");
        String department = Helper.nextString("Ingrese el departamento a filtrar");
        Queue<Ticket> filteredQueue = getTicketsByDepartment(queueTickets, department);
        QueueUtils.displayQueue(filteredQueue);
        System.out.println("Promedio del nivel de urgencia: " + String.format("%.2f", getAverageUrgencyLevel(queueTickets)));
        
        System.out.println("Primer ticket con nivel de urgencia 5: " + getFirstTicketFiveUrgencyLevel(queueTickets));

        ArrayUtils.showArrayOneDimensionGeneric(getIdTicketUrgencyLevel(queueTickets), "ID de los tickets con nivel de urgencia mayor o igual a 4:");
    }

    /**
     * Metodo que solicita al usuario los datos de un ticket y devuelve un objeto Ticket
     * @return Ticket
     */
    public static Ticket getTicket(){
        String idTicket = Helper.nextString("Ingrese el ID del ticket");
        String departament = Helper.nextString("Ingrese el departamento");
        int levelUrgency = Helper.nextInteger("Ingrese el nivel de urgencia (0-5)", 0, 5);
        return new Ticket(idTicket, departament, levelUrgency); 
    }

    /**
     * Metodo que filtra los tickets por departamento y devuelve una nueva cola con los tickets filtrados
     * @param queueTickets Cola de tickets a filtrar
     * @param department Departamento por el cual se filtraran los tickets
     * @return Cola de tickets filtrados
     */
    public static Queue<Ticket> getTicketsByDepartment(Queue<Ticket> queueTickets, String department) {
        Queue<Ticket> filteredQueue = new Queue<>(queueTickets.size());
        for (Ticket ticket : queueTickets) {
            if (ticket.getDepartament().equalsIgnoreCase(department)) {
                filteredQueue.add(ticket);
            }
        }
        return filteredQueue;
    }

    /**
     * Metodo que calcula el promedio del nivel de urgencia de los tickets en la cola
     * @param queueTickets Cola de tickets
     * @return Promedio del nivel de urgencia
     */
    public static double getAverageUrgencyLevel(Queue<Ticket> queueTickets){
        double totalUrgencyLevel = 0;
        if (queueTickets.size() == 0) {
            return 0;
        }
        for (Ticket ticket: queueTickets ){
            totalUrgencyLevel += ticket.getLevelUrgency();
        }

        return  totalUrgencyLevel /  (double) queueTickets.size();
    }

    /**
     * Metodo que devuelve el primer ticket con nivel de urgencia 5
     * @param queueTickets Cola de tickets
     * @return Primer ticket con nivel de urgencia 5
     */
    public static Ticket getFirstTicketFiveUrgencyLevel(Queue<Ticket> queueTickets){
        Ticket ticketFirstFiveUrgencyLevel = new Ticket();
        for (Ticket ticket : queueTickets) {
            if(ticket.getLevelUrgency() == 5){
                ticketFirstFiveUrgencyLevel = ticket;
                break;
            }
        }
        return ticketFirstFiveUrgencyLevel;
    } 

    /**
     * Metodo que devuelve un arreglo con los ID de los tickets con nivel de urgencia mayor o igual a 4
     * @param queueTickets Cola de tickets
     * @return Arreglo con los ID de los tickets con nivel de urgencia mayor o igual a 4
     */
    public static String[] getIdTicketUrgencyLevel(Queue<Ticket> queueTickets){
        String[] arrIdTicketUrgencyLevel = new String[queueTickets.size()];
        int i = 0;
        for (Ticket ticket : queueTickets) {
            if (ticket.getLevelUrgency() >= 4) {
                arrIdTicketUrgencyLevel[i] = ticket.getIdTicket();
                i++;
            }
        }
        return arrIdTicketUrgencyLevel;
    }



}
