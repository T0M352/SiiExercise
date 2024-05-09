package tomasz.morgas.siiZadanie.exceptions;

public class WrongPromocodeException extends RuntimeException{
    public WrongPromocodeException(String message) {
        super(message);
    }

}