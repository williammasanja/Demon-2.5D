package Logic;

import Setup.FontBuilder;
import Setup.MapBuilder;
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
    MapBuilder map;

    public String test = "YOP";

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
        //L3.line(player.getCenterX(), player.getCenterY(), player.getCenterX() + player.getCos() * map.unit  , player.getCenterY() + player.getSin() * map.unit);

        float ray_angle = (float) Math.toRadians(player.getRotationDegrees()) - player.HALFFOV;

        float xvert, yvert, dx, dy, xhor, yhor, deltadepth, depthvert, depthhort;
        for(int range = 0; range < player.NumofRays; range++){
            float sin_a = (float) Math.sin(ray_angle);
            float cos_a = (float) Math.cos(ray_angle);

            //Horizontal
            if(sin_a > 0){
               yhor =(float) (Math.ceil(player.getY()/100) * 100);
               dy = map.unit;
            }
            else{
                yhor = (float) (Math.floor(player.getY()/100) * 100);
                dy = -map.unit;
            }
            depthhort  = (yhor - player.getY())/sin_a;
            xhor = player.getX() + depthhort * cos_a;
            deltadepth = dy/sin_a;
            dx = deltadepth * cos_a;

            for(int i = 0; i < player.Depth; i++){
                int tilex = (int) xhor;
                int tiley = (int) yhor;
                if(map.Wallhit(tilex, tiley)){
                    break;
                }
                xhor += dx;
                yhor += dy;
                depthhort += deltadepth;
            }

            //Vertical
            if(cos_a > 0){
                xvert = (float) (Math.ceil(player.getX()/100) * 100);
                dx = map.unit;

            }
            else{
                xvert = (float) (Math.floor(player.getX()/100) * 100);
                dx = -map.unit;
            }


            depthvert = (xvert - player.getX())/cos_a;
            // Make sure its in the unit

            yvert = player.getY() + depthvert * sin_a;
            test = String.valueOf(yvert);
            deltadepth = dx/cos_a;
            dy = deltadepth * sin_a;

            for(int i = 0; i < player.Depth; i++){
                int tilex = (int) xvert;
                int tiley = (int) yvert;
                if(map.Wallhit(tilex, tiley)){
                    break;
                }
                xvert += dx;
                yvert += dy;
                depthvert += deltadepth;
            }

            float depth = Math.min(depthvert, depthhort);

            L3.line(player.getCenterX(), player.getCenterY(), player.getCenterX()-(player.width/2f) + cos_a * depth, player.getCenterY()-(player.height/2f) + sin_a * depth);
            ray_angle += player.DeltaAngle;
        }
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
