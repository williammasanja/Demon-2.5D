package Setup;

import com.badlogic.gdx.Gdx;

import java.util.Map;

public class PlayerBuilder extends  itemBuilder{
    public float hitboxradius;
    public float screen_distance;
    public float FOV;
    public float HALFFOV;
    public float DeltaAngle;
    public float HalfRays;
    public int NumofRays;
    public float Scale;
    public int Depth;



    public PlayerBuilder(){
        super("Player", 50, 50, 150,150);

        hitboxradius = 25;

        FOV = (float) (Math.PI/3f);
        HALFFOV = FOV/2f; //radians
        NumofRays = 200;
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


}
