package Logic;

import Setup.FontBuilder;
import Setup.MapBuilder;
import Setup.PlayerBuilder;
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

    private PlayerBuilder player;

    public GameManager() {
        renderer = new L3_Render();
        logic = new ItemLogic();

        Font = new FontBuilder(100);
        map = new MapBuilder();

        player = new PlayerBuilder();

        logic.player = player;
    }

    public void updateLogic() {
        logic.Logic();
    }

    public void render() {
        renderer.apply_viewport();
        renderer.setProjectionMatrix();

        renderer.renderStart();

        for(int j = 0; j < map.height; j++) {
            for (int i = 0; i < map.width; i++) {
                if(map.grid[j][i] == 1) {
                    renderer.render(map.box, i * 100, j * 100);
                }
            }
       }
        renderer.renderPlayerShape(player);
        renderer.render(Font, String.valueOf(map.height), CenterX,CenterY);





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
