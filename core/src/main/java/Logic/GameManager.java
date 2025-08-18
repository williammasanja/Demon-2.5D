package Logic;

import Setup.FontBuilder;
import Setup.MapBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameManager {

    private ItemLogic logic;
    private L3_Render renderer;

    private FontBuilder Font;

    private MapBuilder map;

    public final float CenterX = Gdx.graphics.getWidth()/2f;
    public final float CenterY = Gdx.graphics.getHeight()/2f;

    public GameManager() {
        renderer = new L3_Render();
        logic = new ItemLogic();

        Font = new FontBuilder(100);
        map = new MapBuilder();
    }

    public void updateLogic() {
        logic.Logic();
    }

    public void render() {
        renderer.apply_viewport();
        renderer.setProjectionMatrix();

        renderer.renderStart();
        renderer.render(Font, String.valueOf(map.height), CenterX,CenterY);
        for(int j = 0; j < map.height; j++) {
            for (int i = 0; i < map.width; i++) {
               renderer.render(map.box, i * 100, j * 100);
            }
       }




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
