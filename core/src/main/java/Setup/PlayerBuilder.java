package Setup;

import com.badlogic.gdx.Gdx;

public class PlayerBuilder extends  itemBuilder{
    public float hitboxradius;
    public float lineradius;

    float FOV;
    int NumofRays;
    float DeltaAngle;


    public PlayerBuilder(){
        super("Player", 50, 50, 0,0);
        hitboxradius = 25;
        lineradius = 100;
        FOV = (float) (Math.PI/3f);
        NumofRays = Gdx.graphics.getWidth()/2;
        DeltaAngle = FOV/NumofRays;


    }

    public float getCenterX(){
        return super.getX() + super.width/2f;
    }

    public float getCenterY(){
        return super.getY() + super.height/2f;
    }


}
