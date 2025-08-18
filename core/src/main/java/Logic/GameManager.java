package Logic;

import Setup.FontBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameManager {



    private ItemLogic logic;
    private L3_Render renderer;

    private FontBuilder Font;

    public GameManager() {
        renderer = new L3_Render();
        logic = new ItemLogic();

        Font = new FontBuilder(100);
    }

    public void updateLogic() {
        logic.Logic();
    }

    public void render() {
        renderer.apply_viewport();
        renderer.setProjectionMatrix();

        renderer.renderStart();
        renderer.render(Font, "YOP", 0,0);
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
