package ba.unsa.etf.rpr.p1;

public class WrongDeviceState extends Error {
    String message;
    public WrongDeviceState(String s) {
        message=s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
