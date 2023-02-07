package BattleCode;

public class Mail {
    private RobotInfo sender;
    private String message;

    public Mail(RobotInfo sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public RobotInfo getSender() {return sender;}

    public String getMessage() {
        return message;
    }
}
