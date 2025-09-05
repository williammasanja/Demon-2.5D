package Logic;

import Setup.FontBuilder;
import Setup.MapBuilder;
import Setup.PlayerBuilder;
import Setup.TextureMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public class GameManager {

    private ItemLogic logic;
    private L3_Render renderer;

    private FontBuilder Font;

    private MapBuilder map;

    private TextureMap textureMap;


    public final float CenterX = Gdx.graphics.getWidth()/2f;
    public final float CenterY = Gdx.graphics.getHeight()/2f;

    private PlayerBuilder player;

    public GameManager() {
        Gdx.input.setCursorCatched(true);

        renderer = new L3_Render();
        logic = new ItemLogic();

        Font = new FontBuilder(100);
        map = new MapBuilder();

        player = new PlayerBuilder();
        textureMap = new TextureMap();

        logic.player = player;
        logic.map = map;


        renderer.map = map;
        renderer.textureMap = textureMap;

        Pixmap pm = new Pixmap(4, 4, Pixmap.Format.RGBA8888);
        pm.setColor(0, 0, 0, 0);   // RGBA (fully transparent)
        pm.fill();

        Cursor transparentCursor = Gdx.graphics.newCursor(pm, 0, 0);
        Gdx.graphics.setCursor(transparentCursor);
        pm.dispose();
    }

    public void updateLogic() {
        logic.Logic();
    }

    public void render() {
        renderer.apply_viewport();
        renderer.setProjectionMatrix();

        renderer.renderStart();

        renderer.renderMaprojection(player);
        //renderer.renderTextureSplit(10);
        //renderer.render(Font, String.valueOf(renderer.test), 350, 100);
        //renderer.rendermap();
        //renderer.render2dplayer(player);
        //renderer.renderraycast(player);

        renderer.renderEnd();
    }

    public void resize(int width, int height){
        renderer.resize(width, height);
    }

    public ItemLogic getLogic() {
        return logic;
    }

    public L3_Render getRenderer() {
            return renderer;
    }


}
