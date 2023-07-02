package Queue;

class CircularQueue implements ICharQ {
    private char q[]; // массив для хранения элементов очереди
    private int putloc, getloc; // индексы вставляемых и

    // извлекаемых элементов
// Создать пустую очередь заданного размера
    public CircularQueue(int size) {
        q = new char[size + 1]; // выделить память для очереди
        putloc = getloc = 0;
    }
    // Поместить символ в очередь
    public void put(char ch) {
/* Очередь считается полной, если индекс putloc на единицу
меньше индекса getloc или если индекс putloc указывает
на конец массива, а индекс getloc - на его начало */
        if (putloc + 1 == getloc | ((putloc == q.length - 1) & (getloc == 0))) {
            System.out.println(" - Очередь заполнена");
            return;
        }
        putloc++;
        if (putloc == q.length) putloc = 0; // перейти в начало массива
        q[putloc] = ch;
    }
    // Извлечь символ из очереди
    public char get () {
        if (getloc == putloc) {
            System.out.println(" - Очередь пуста");
            return (char) 0;
        }
        getloc++;
        if (getloc == q.length) getloc = 0;
        return q[getloc];
    }
}
