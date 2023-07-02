package QueueException;

// Интерфейс для очереди символов
interface ICharQ {
    // Поместить символ в очередь
    void put(char ch) throws QueueFullException;

    // Извлечь символ из очереди
    char get() throws QueueEmptyException;
}