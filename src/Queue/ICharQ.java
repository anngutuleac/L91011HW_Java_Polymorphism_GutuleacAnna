package Queue;

// Интерфейс для очереди символов
interface ICharQ {
    // Поместить символ в очередь
    void put(char ch);

    // Извлечь символ из очереди
    char get();
}