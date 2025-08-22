package Setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class PlayerBuilder extends  itemBuilder{
    public float hitboxradius;

    public float FOV;
    public float HALFFOV;
    public float DeltaAngle;
    public float HalfRays;
    public float NumofRays;

    public float Depth;

    public float x;
    public float y;

    public PlayerBuilder(){
        super("Player", 50, 50, 100,100);
        x =  (super.getX() + super.width/2f);
        y =  (super.getY() + super.height/2f);
        hitboxradius = 25;

        FOV = (float) (Math.PI/3f);
        HALFFOV =  (FOV/2); //radians
        NumofRays = 100;
        HalfRays = NumofRays/2f;
        DeltaAngle = (FOV/NumofRays);
        Depth = 10; //Boxes to check
    }

    public float getCenterX(){
        return super.getX() + super.width/2f;
    }

    public float getCenterY(){
        return super.getY() + super.height/2f;
    }

    public boolean TouchingBox(Rectangle Box){
        if(boxactive) {
            if (Hitbox.overlaps(Box)) {
                touching = true;
                return true;
            }
        }
        return false;
    }

    public void updatePosition(float x, float y){
        this.x += x;
        this.y += y;
        Hitbox.x += x;
        Hitbox.y += y;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
        Hitbox.setPosition(x-width/2f, y-height/2f);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }


}
