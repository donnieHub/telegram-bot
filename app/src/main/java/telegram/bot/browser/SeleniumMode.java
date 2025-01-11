package telegram.bot.browser;

public enum SeleniumMode {
    REMOTE ("remote"),
    LOCAL ("local");

    private String mode;

    SeleniumMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}