public class Queue<E> {

    private Object[] elementos;
    private int head;
    private int tail;
    private int cantidad;

    // Constructor
    public Queue(int capacidad) {
        elementos = new Object[capacidad];

        head = 0;
        tail = 0;
        cantidad = 0;
    }

    // Agregar elemento
    public boolean offer(E elemento) {

        if (cantidad >= elementos.length) {
            return false;
        }

        elementos[tail] = elemento;

        tail = (tail + 1) % elementos.length;
        cantidad++;

        return true;
    }


    // Sacar primer elemento
    @SuppressWarnings("unchecked")
    public E pool() {

        if (cantidad == 0) {
            return null;
        }

        E elemento = (E) elementos[head];

        head = (head + 1) % elementos.length;
        cantidad--;

        return elemento;
    }

    // Consultar primer elemento
    @SuppressWarnings("unchecked")
    public E peek() {

        if (cantidad == 0) {
            return null;
        }

        return (E) elementos[head];
    }

    // Devuelve la posición de head
    public int head() {
        return head;
    }

    // Devuelve la posición de tail
    public int tail() {
        return tail;
    }

    // Saber si está vacía
    public boolean isEmpty() {
        return cantidad == 0;
    }

    // Cantidad de elementos
    public int size() {
        return cantidad;
    }

    @Override
    public String toString() {

    if (cantidad == 0) {
        return "[]";
    }

    String resultado = "[";

    int posicion = head;

    for (int i = 0; i < cantidad; i++) {

        resultado += elementos[posicion];

        if (i < cantidad - 1) {
            resultado += ", ";
        }

        posicion = (posicion + 1) % elementos.length;
    }

    resultado += "]";

    return resultado;
}
}