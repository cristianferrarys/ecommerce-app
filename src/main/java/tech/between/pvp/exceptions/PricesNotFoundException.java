package tech.between.pvp.exceptions;



public class PricesNotFoundException extends PricesException {
    public PricesNotFoundException(String message) {
        super(message);
    }
    public PricesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
