package BattleCode;

public class Mail {
    private Robot sender;
    private String message;

    public Robot getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Mail(Robot sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
