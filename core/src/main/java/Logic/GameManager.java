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
        logic.map = map;
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
                    map.box.x = i*100;
                    map.box.y = (map.height - 1 - j) * 100;
                    renderer.render(map.box, (int) map.box.x, (int) map.box.y);
                }
            }
       }
        renderer.render(Font, String.valueOf(player.touching), CenterX, CenterY);
        renderer.renderPlayerShape(player);
        //renderer.render(Font, String.valueOf((int) player.getRotationDegrees()), CenterX,CenterY);

        renderer.render(player.Hitbox);
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
