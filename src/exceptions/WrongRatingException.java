package exceptions;

public class WrongRatingException extends Exception {
    String message;

    public WrongRatingException(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
