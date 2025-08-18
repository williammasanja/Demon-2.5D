package Setup;

public class PlayerBuilder extends  itemBuilder{
    public float hitboxradius;
    public float lineradius;

    public PlayerBuilder(){
        super("Player", 50, 50, 0,0);
        hitboxradius = 25;
        lineradius = 100;
    }

    public float getCenterX(){
        return super.getX() + super.width/2f;
    }

    public float getCenterY(){
        return super.getY() + super.height/2f;
    }


}
