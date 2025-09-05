package Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputKey {

    public InputKey(){

    }

    public boolean justpresset(int Key){
        return Gdx.input.isKeyJustPressed(Key);
    }

    public boolean pressed(int Key){
        return Gdx.input.isKeyPressed(Key);
    }

    public boolean pressedW(){
        return pressed(Input.Keys.W);
    }

    public boolean pressedA(){
        return pressed(Input.Keys.A);
    }

    public boolean pressedS(){
        return pressed(Input.Keys.S);
    }

    public boolean pressedD(){
        return pressed(Input.Keys.D);
    }

    public boolean pressedQ(){
        return  pressed(Input.Keys.Q);
    }

    public boolean pressedE(){
        return pressed(Input.Keys.E);
    }



}
