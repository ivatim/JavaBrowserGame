package util.sax;

public class SettingsFileNotFoundException extends RuntimeException {
    public SettingsFileNotFoundException(Exception e) {
        super(e);
    }
}
