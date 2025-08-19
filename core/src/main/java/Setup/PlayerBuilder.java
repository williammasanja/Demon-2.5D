package Setup;

import com.badlogic.gdx.Gdx;

public class PlayerBuilder extends  itemBuilder{
    public float hitboxradius;
    public float lineradius;

    public float FOV;
    public float HALFFOV;
    public float DeltaAngle;
    public float HalfRays;
    public int NumofRays;

    public int Depth;



    public PlayerBuilder(){
        super("Player", 50, 50, 150,150);

        hitboxradius = 25;
        lineradius = 600;
        FOV = (float) (Math.PI/3f);
        HALFFOV = FOV/2f; //radians
        NumofRays = 100;
        HalfRays = NumofRays/2f;
        DeltaAngle = FOV/NumofRays;
        Depth = 20;
    }

    public float getCenterX(){
        return super.getX() + super.width/2f;
    }

    public float getCenterY(){
        return super.getY() + super.height/2f;
    }


}
