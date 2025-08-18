package Logic;

import Setup.PlayerBuilder;
import com.badlogic.gdx.Input;

public class ItemLogic {
    InputKey inputKey;
    public  PlayerBuilder player;





    public ItemLogic() {
        inputKey = new InputKey();
    }



    public void Logic() {
        float speed = 5;
        float dy = 0;
        float dx = 0;
        if(inputKey.pressedW()) dy += 1;
        if(inputKey.pressedS()) dy -= 1;
        if(inputKey.pressedA()) dx = 1;
        if(inputKey.pressedD()) dx = -1;

        float length = (float) Math.sqrt(dx*dx + dy*dy);
        if(length != 0) {
            dx = dx / length * speed;
            dy = dy / length * speed;
        }

        player.updatePosition(dx, dy);


        float rotationSpeed = 0.05f; // radians per frame (adjust)
        if(dx != 0){
        player.setRotationPosition((player.getRotation() +  dx * rotationSpeed));
        }


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










