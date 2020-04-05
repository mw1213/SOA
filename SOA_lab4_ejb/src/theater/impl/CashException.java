package theater.impl;

public class CashException extends Throwable {
    String message="";
    public CashException(String not_enough_cash) {
        message=not_enough_cash;
    }
    public void printMessage(){
        System.out.println(message);
    }
}
