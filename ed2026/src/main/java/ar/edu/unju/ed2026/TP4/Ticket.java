package ar.edu.unju.ed2026.TP4;

/**
 * Clase que representa un ticket con un ID, un departamento y un nivel de urgencia
 */
public class Ticket {
    private String idTicket;
    private String departament;
    private int levelUrgency;
    
    public Ticket(){
        idTicket = "";
        departament = "";
        levelUrgency = 0;
    }

    public Ticket(String idTicket, String departament, int levelUrgency) {
        setIdTicket(idTicket);
        setDepartament(departament);
        setLevelUrgency(levelUrgency);
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        if(idTicket.length() == 0){
            throw new IllegalArgumentException("El id del ticket no puede estar vacío.");
        }
        this.idTicket = idTicket;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        if(departament.length() < 5  ||  departament.isEmpty()){
            throw new IllegalArgumentException("El departamento no puede estar vacío.");
        }
        this.departament = departament;
    }

    public int getLevelUrgency() {
        return levelUrgency;
    }

    public void setLevelUrgency(int levelUrgency) {
        if(levelUrgency < 0){
            throw new IllegalArgumentException("El nivel de urgencia no puede ser negativo.");
        }
        if(levelUrgency > 5){
            throw new IllegalArgumentException("El nivel de urgencia no puede ser mayor a 5.");
        }
        this.levelUrgency = levelUrgency;
    }

    @Override
    public String toString() {
            return "Ticket [idTicket=" + idTicket + ", departament=" + departament + ", levelUrgency=" + levelUrgency + "]";
    }
}
