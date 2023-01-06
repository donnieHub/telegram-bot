package telegram.bot;

public enum BotCommand {

    START("/start"),
    CHOOSE_CITY("/choose_city"),
    GAME("/game");

    private String command;

    BotCommand(String command){
        this.command = command;
    }
    public String getCommand(){ return command;}

    @Override
    public String toString(){
        return command;
    }
}
