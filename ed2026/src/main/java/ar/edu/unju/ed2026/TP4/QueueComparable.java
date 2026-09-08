package ar.edu.unju.ed2026.TP4;

//
// Created by Julio Tentor <jtentor@fi.unju.edu.ar>
//

public class QueueComparable<ELEMENT extends Comparable<ELEMENT>> extends Queue<ELEMENT> {

    //region Constructors

    public QueueComparable() {
        super();
    }

    public QueueComparable(int dimension) {
        super(dimension);
    }

    //endregion

    @SuppressWarnings("unchecked")
    public int compareTo(Queue<ELEMENT> queue2) {

        Queue<ELEMENT> queue1 = this;

        int cta1 = queue1.size();
        int pos1 = queue1.head;

        int cta2 = queue2.size();
        int pos2 = queue2.head;

        int val = 0;

        while (cta1 > 0 || cta2 > 0) {
            if (cta1 > 0) {
                if (cta2 > 0) {
                    val = ((Comparable<ELEMENT>) queue1.data[pos1]).compareTo((ELEMENT) queue2.data[pos2]);

                    if (val > 0) {
                        return 1; // queue1 > queue2
                    }
                    if (val < 0) {
                        return -1; // queue1 < queue2
                    }

                    --cta1;
                    pos1 = queue1.next(pos1);
                    --cta2;
                    pos2 = queue2.next(pos2);
                    continue;
                }
                return 1; // queue1 > queue2
            }
            if (cta2 > 0) {
                return -1; // queue1 < queue2
            }
        }
        return 0; // queue1 == queue2
    }
}