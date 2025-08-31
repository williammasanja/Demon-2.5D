package Setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class TextureMap {
    ArrayList<Texture> Textures;

    public TextureMap(){
        Textures = new ArrayList<>();
        addTexture(Gdx.files.internal("assets/textures/Stone.png"));
        addTexture(Gdx.files.internal("assets/textures/Brick.png"));
        addTexture(Gdx.files.internal("assets/textures/Hello.png"));
    }

    public void addTexture(FileHandle File){
        Texture texture = new Texture(File);
        Textures.add(texture);
    }

    public Texture returnTexture(int index){
        return Textures.get(index);
    }
}
