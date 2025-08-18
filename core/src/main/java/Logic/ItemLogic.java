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
        float rotationspeed = 0.05f;
        float speed = 5;
        float dy = 0;
        float dx = 0;

        // rotate
        if(inputKey.pressedA()) player.setRotationPosition(player.getRotation() + rotationspeed);
        if(inputKey.pressedD()) player.setRotationPosition(player.getRotation() - rotationspeed);

// move forward/backward

        if(inputKey.pressedW()) {
            dx += Math.cos(player.getRotation()) * speed;
            dy += Math.sin(player.getRotation()) * speed;
        }
        if(inputKey.pressedS()) {
            dx -= Math.cos(player.getRotation()) * speed;
            dy -= Math.sin(player.getRotation()) * speed;
        }

        player.updatePosition(dx, dy);

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










