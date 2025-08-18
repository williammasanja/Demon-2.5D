package Logic;

import com.badlogic.gdx.Input;

public class ItemLogic {
    InputKey inputKey;

    public ItemLogic() {
        inputKey = new InputKey();
    }


    public void Logic() {

    }

    public String test(){
        if(inputKey.pressedW()){
            return "W";
        } else if (inputKey.pressedA()) {
            return "A";

        } else if (inputKey.pressedS()) {
            return "S";

        } else if (inputKey.pressedD()) {
            return "D";
        }
        else if (inputKey.pressedQ()) {
            return "Q";
        }
        else if (inputKey.pressedE()) {
            return "E";
        }
        return "NA";
    }

    }










