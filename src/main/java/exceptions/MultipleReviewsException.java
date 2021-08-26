package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MultipleReviewsException extends RuntimeException {
    private final String message;
}
