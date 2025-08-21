package Logic;

import Setup.MapBuilder;
import Setup.PlayerBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

public class ItemLogic {
    InputKey inputKey;
    public  PlayerBuilder player;
    public MapBuilder map;

    public ItemLogic() {
        inputKey = new InputKey();
    }

    public void Logic() {
        float centerX = Gdx.graphics.getWidth() / 2f;


        float rotationspeed = 2f;
        float speed = 5;
        float dy = 0;
        float dx = 0;

        // key-rotation
        //if(inputKey.pressedA()) player.setRotationPosition(player.getRotationDegrees() + rotationspeed);
        //if(inputKey.pressedD()) player.setRotationPosition(player.getRotationDegrees() - rotationspeed);

        float pointerdelta = Gdx.input.getDeltaX();
        float pointer_rotation = -(Gdx.input.getX()-(Gdx.graphics.getWidth()/2f)) * ((float) 720/Gdx.graphics.getWidth()/2f);
        player.setRotationPosition(pointer_rotation);





        //Strafing mechanics

        float deg = player.getRotationDegrees();          // if 0° faces UP, use (deg - 90)
        float rad = deg * MathUtils.degreesToRadians;
        float s = MathUtils.sin(rad), c = MathUtils.cos(rad);
        float v = speed;

        // A = left, D = right
        if (inputKey.pressedA()){
            dx -= s * v;
            dy += c * v;
        }
        if (inputKey.pressedD()){
            dx += s * v;
            dy -= c * v;
        };

        if (inputKey.pressedW()) {
            dx += (float) (Math.cos(player.getRad()) * speed);
            dy += (float) (Math.sin(player.getRad()) * speed);
        }

        if (inputKey.pressedS()) {
            dx -= (float) (Math.cos(player.getRad()) * speed);
            dy -= (float) (Math.sin(player.getRad()) * speed);
        }

        if(inputKey.pressed(Input.Keys.SPACE)){
            player.setRotationPosition(90);
        }

        //slides on walls
        float oldX = player.getX();
        float oldY = player.getY();

        // Move X
        player.updatePosition(dx, 0);
        player.touching = false;
        for(int j = 0; j < map.height; j++) {
            for(int i = 0; i < map.width; i++) {
                if(player.TouchingBox(map.getMapHitbox(i, j))) {
                    player.touching = true;
                    // push player slightly away from wall
                   if(dx > 0) player.setX(map.getMapHitbox(i, j).x - player.width);
                    else if(dx < 0) player.setX(map.getMapHitbox(i, j).x + map.getMapHitbox(i, j).width);
                    break; // stop checking more tiles
                }
            }
            if(player.touching) break;
        }
        if(player.touching) player.setX(oldX);

        // Move Y
        player.updatePosition(0, dy);
        player.touching = false;
        for(int j = 0; j < map.height; j++) {
            for(int i = 0; i < map.width; i++) {
                if(player.TouchingBox(map.getMapHitbox(i, j))) {
                    player.touching = true;
                    if(dy > 0) player.setY(map.getMapHitbox(i, j).y - player.height);
                   else if(dy < 0) player.setY(map.getMapHitbox(i, j).y + map.getMapHitbox(i, j).height);
                    break;
                }
            }
            if(player.touching) break;
        }
        if(player.touching) player.setY(oldY);
    }


    public String test(){

        if(inputKey.pressedW()){
            return "W";
        } else if (inputKey.pressedA()) {
            return "A";
        }
        else if (inputKey.pressedS()) {
            return "S";
        }
        else if (inputKey.pressedD()) {
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










