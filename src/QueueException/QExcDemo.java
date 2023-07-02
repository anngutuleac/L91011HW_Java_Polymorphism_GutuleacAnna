package QueueException;

class QueueFullException extends Exception {
    int size;
    QueueFullException(int s) {
        size = s;
    }
    public String toString() {
        return "\nОчередь заполнена. Максимальный размер очереди: " + size;
    }
}
// Исключение, указывающее на исчерпание очереди
class QueueEmptyException extends Exception {
    public String toString() {
        return "\nОчередь пуста.";
    }
}
// Демонстрация исключений при работе с очередью
class QExcDemo {
    public static void main(String args[]) {
        FixedQueue q = new FixedQueue(10);
        char ch;
        int i;
        try {
// Переполнение очереди
            for (i = 0; i < 11; i++) {
                System.out.print("Пoпыткa сохранения: " + (char) ('A' + i));
                q.put((char) ('A' + i));
                System.out.println(" - ОК");
            }
            System.out.println();
        } catch (QueueFullException exc) {
            System.out.println(exc);
        }
        System.out.println();
        try {
// Попытка извлечь символ из пустой очереди
            for (i = 0; i < 11; i++) {
                System.out.print("Пoлyчeниe очередного символа: ");
                ch = q.get();
                System.out.println(ch);
            }
        } catch (QueueEmptyException exc) {
            System.out.println(exc);
        }
    }
}