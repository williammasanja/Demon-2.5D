package Setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class PlayerBuilder extends  itemBuilder{

    /*
    NO MATTER WHAT DONT CONVERT THE FLOATS
    TO INTS, YOULL HAVE LOSS OF INFROMATION THAT PILES UP
    OVER THE CODE AND DISRUPTS MAP PROJECTION
     */

    public float hitboxradius;
    public float screen_distance;
    public float FOV;
    public float HALFFOV;
    public float DeltaAngle;
    public float HalfRays;
    public int NumofRays;
    public float Scale;
    public int Depth;

    public float x;
    public float y;

    public PlayerBuilder(){
        super("Player", 30, 30, 150,150);
        hitboxradius = 15f;
        x =  (super.getX() + super.width/2f);
        y =  (super.getY() + super.height/2f);
        hitboxradius = 25;

        FOV = (float) (Math.PI/3f);
        HALFFOV = FOV/2f; //radians
        NumofRays = 100;
        HalfRays = NumofRays/2f;
        DeltaAngle = FOV/NumofRays;
        Depth = 30; //Boxes to check

        screen_distance = (float) (MapBuilder.half_width * MapBuilder.unit / Math.tan(HALFFOV));
        Scale = (float) (MapBuilder.width * MapBuilder.unit) / NumofRays;
    }

    public float getCenterX(){
        return super.getX() + super.width/2f;
    }

    public float getCenterY(){
        return super.getY() + super.height/2f;
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

    public void setX(float x){
        this.x = x+width/2f;
        Hitbox.x = x;}

    public void setY(float y){
        this.y = y+height/2f;
        Hitbox.y = y;
    }

}
