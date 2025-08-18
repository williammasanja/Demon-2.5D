package Logic;

import Setup.FontBuilder;
import Setup.PlayerBuilder;
import Setup.itemBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class L3_Render {
    private OrthographicCamera camera;
    private Viewport viewport;

    SpriteBatch L1; // OverLay Layer
    FontBuilder font;
    ArrayList<itemBuilder> Overlayitems;

    SpriteBatch L2; // Game Layer
    ArrayList<itemBuilder> itemList;

    ShapeRenderer L3; // Hitbox Layer



    public L3_Render(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(1200, 600, camera); // virtual world size

        //layer 1
        L1 = new SpriteBatch();
        font = new FontBuilder();
        Overlayitems = new ArrayList<itemBuilder>();

        //layer 2
        L2 = new SpriteBatch();
        itemList = new ArrayList<itemBuilder>();

        //Layer 3
        L3 = new ShapeRenderer();
    }

    public void add(itemBuilder item, int Layer){
        if(Layer == 1){
            Overlayitems.add(item);
        }
        if(Layer == 2){
            itemList.add(item);
        }
    }

    public void remove(itemBuilder item){
        Overlayitems.remove(item);
        itemList.remove(item);
    }

    public void render(FontBuilder font, String text, float x, float y){
        font.bitmap.draw(L1, text, x- font.getWidth(text)/2f, y+font.getHeight(text)/2f);
    }

    public void renderPlayerShape(PlayerBuilder player){

        L3.circle(player.getX()+player.hitboxradius, player.getY()+ player.hitboxradius, player.hitboxradius);
        L3.line(player.getCenterX(), player.getCenterY(), player.getCenterX() + player.getCos() * player.lineradius, player.getCenterY() + player.getSin() * player.lineradius);

    }


    public void render(int layer , Texture texture, float x, float y, float width, float height, Rectangle Hitbox, boolean drawbox){
        switch (layer){
           case 1:
               L1.draw(texture, x, y, width, height);
               break;
           case 2:
               L2.draw(texture, x, y, width, height);
               break;
       }
       if(drawbox){
           L3.rect(Hitbox.x, Hitbox.y, Hitbox.width, Hitbox.height);
       }
    }

    public void render(int layer, itemBuilder item){
        if(item != null && item.showtexture) {
            render(layer, item.returnTexture(), item.getX(), item.getY(), item.width, item.height, item.Hitbox, item.showbox);
        }

    }

    public void render(Rectangle Hitbox){
        if(Hitbox != null) {
            L3.rect(Hitbox.x, Hitbox.y, Hitbox.width, Hitbox.height);
        }
    }
    public void render(Rectangle Hitbox, int x, int y){
        if(Hitbox != null) {
            L3.rect(x, y, Hitbox.width, Hitbox.height);
        }
    }
    public void renderStart() {
        L1.begin();
        L2.begin();
        L3.begin(ShapeRenderer.ShapeType.Line);
    }

    public void renderEnd() {
        L1.end();
        L2.end();
        L3.end();
    }

    public void setProjectionMatrix(){
        camera.position.set(12 * 50, 6 * 50, 0);
        camera.update();
       L1.setProjectionMatrix(camera.combined);
       L2.setProjectionMatrix(camera.combined);
       L3.setProjectionMatrix(camera.combined);
    }


    public void apply_viewport(){
        viewport.apply();
    }
    public void resize(int width, int height){
        viewport.update(width, height);
    }


}
