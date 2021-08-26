package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlatformNotReadyException extends RuntimeException{
    private final String message;
}
