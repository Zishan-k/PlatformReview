package exceptions;


public class MultipleReviewsException extends RuntimeException {
    String message;

    public MultipleReviewsException(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
