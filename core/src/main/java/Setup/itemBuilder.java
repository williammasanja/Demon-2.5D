package Setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class itemBuilder {
    public String Name;
    public Rectangle Hitbox;
    public Texture texture;

    public static int totalcreated = 0;
    public int ID;
    public float width;
    public float height;
    public float degree;

    public boolean moveable;
    public boolean showbox;
    public boolean showtexture;
    public boolean boxactive;



    public itemBuilder(String name){
        this(name,(500/2.4f), (700/2.4f));
    }

    public itemBuilder(String name, float width, float height){
        this(name, width, height, 0, 0);
    }

    public itemBuilder(String name, float width, float height, float x, float y){
        Name = name;
        totalcreated++;
        ID = totalcreated;

        Hitbox = new Rectangle(x,y, width, height);
        this.width = Hitbox.width;
        this.height = Hitbox.height;
        degree = 90;

        showtexture = true;
        showbox = true;
        moveable = true;
        boxactive = true;
    }

    public itemBuilder(itemBuilder original) {
        // Copy relevant properties
        this.texture = original.texture;
        this.Hitbox = new Rectangle(original.Hitbox);
        this.ID = original.ID;
        this.moveable = original.moveable;

    }


    public void setPosition(float x, float y){
        Hitbox.setPosition(x, y);
    }
    public void setRotationPosition(float degree){ this.degree = degree;}

    public float getX(){
        return Hitbox.x;
    }

    public float getY(){
        return Hitbox.y;
    }

    public float getSin(){return (float) Math.sin(degree);}

    public float getCos(){ return (float) Math.cos(degree);}

    public float getRotation(){ return degree;}

    public void updatePosition(float x, float y){
        Hitbox.setPosition(Hitbox.x+x, Hitbox.y+y);
    }

    public void updateRotationPosition(float degree){
        degree += degree;
    }

    public String returnID(){
        return Name;
    }


    public boolean Touched(){
            if (Gdx.input.isTouched() && Hitbox.contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
                return true;
            }
        return false;
    }

    public boolean justTouched(){
        if (Gdx.input.justTouched() && Hitbox.contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
            return true;
        }
        return false;
    }

    public void changeWidth(float width){
        this.width = width;
        Hitbox.width = width;
    }

    public void changeHeight(float height){
        this.height = height;
        Hitbox.height = height;

    }

    public boolean Touchingitem(itemBuilder item){
        if(boxactive) {
            if (Hitbox.overlaps(item.Hitbox)) {
                return true;
            }
        }
        return false;
    }

    public Texture returnTexture(){
        return texture;
    }

}
