package game;

public class Effect {
    private String effectName;
    private Boolean oneTime;

    public Effect(){
        effectName = "This is the effect name";
        oneTime = false;
    }

    private Boolean isOneTime(){
        return oneTime;
    }
}
