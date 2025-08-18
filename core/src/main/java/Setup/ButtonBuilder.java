package Setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ButtonBuilder extends itemBuilder{

    public ButtonBuilder(){
        this(1);
    }

    public ButtonBuilder(int i){
        super("Button", 600/1.7f, 173/1.7f);

        switch(i){
            case 1:
                super.texture = new Texture(Gdx.files.internal("ButtonAssets/ActivateButton.png"));
                break;
            case 2:
                super.texture = new Texture(Gdx.files.internal("ButtonAssets/EndTurnButton.png"));
                break;
        }

        super.moveable = false;
        super.showbox = false;
        super.showtexture = false;
        //super.boxon = false;
        super.updatePosition((Gdx.graphics.getWidth()/2f) -(width/2f), 25);
    }
}
