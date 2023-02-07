package BattleCode;

public class Mail {
    private RobotController sender;
    private String message;

    public RobotController getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Mail(RobotController sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
