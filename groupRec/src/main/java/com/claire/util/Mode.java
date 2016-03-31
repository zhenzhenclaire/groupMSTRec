package claire.util;

/**
 * Created by admin on 2016/3/31.
 */
public enum Mode {
    DRIVING("driving"), WALKING("walking"), BICYCLING("bicycling");
    private String value;
    Mode(String value){
        this.value = value;
    }
    public String value(){
        return this.value;
    }
}
