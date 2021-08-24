package exceptions;

public class PlatformNotReadyException extends RuntimeException{
    String message;

    public PlatformNotReadyException(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
