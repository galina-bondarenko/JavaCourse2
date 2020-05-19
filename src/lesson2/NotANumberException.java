package lesson2;

public class NotANumberException extends Exception {
    public NotANumberException(String value) {
        super("Невозможно преобразовать в число следующее значение: " + value);
    }
}
