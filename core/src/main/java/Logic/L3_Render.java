package Logic;

import Setup.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    TextureMap textureMap;

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
        L3.setAutoShapeType(true);
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

    public void rendermapprojection(PlayerBuilder player){

        float ray_angle = (float) Math.toRadians(player.getRotationDegrees()) - player.HALFFOV;

        float xvert, yvert, dx, dy, xhor, yhor, deltadepth, depthvert, depthhort, offset;
        Rectangle texturehor = new Rectangle();
        Rectangle texturevert = new Rectangle();
        Rectangle texturetile;

        for(int range = 0; range < player.NumofRays; range++){
            float sin_a = (float) Math.sin(ray_angle);
            float cos_a = (float) Math.cos(ray_angle);

            //limiits so angle dosent get closer to 0 thus ray casting errors arrrive
            if (Math.abs(sin_a) < 1e-30) sin_a = 1e-30f;
            if (Math.abs(cos_a) < 1e-30) cos_a = 1e-30f;

            //Horizontal
            if(sin_a > 0){
               yhor = (int)(player.getY() / map.unit) * map.unit + map.unit;
               dy = map.unit;
            }
            else{
                yhor = (int)(player.getY() / map.unit) * map.unit - 0.000000001f; // just above current gridline
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
                    texturehor = map.WallHitRect(tilex, tiley);
                    break;
                }
                xhor += dx;
                yhor += dy;
                depthhort += deltadepth;
            }

            //Vertical
            if(cos_a > 0){
                xvert = (int)(player.getX() / map.unit) * map.unit + map.unit;
                dx = map.unit;

            }
            else{
                xvert = (int)(player.getX() / map.unit) * map.unit - 0.000000001f;
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
                    texturevert = map.WallHitRect(tilex, tiley);
                    break;
                }
                xvert += dx;
                yvert += dy;
                depthvert += deltadepth;
            }
            float depth = 0;
            if(depthvert < depthhort){
                depth = depthvert;
                texturetile = texturevert;
                yvert %= 1;
                offset = (cos_a > 0) ? yvert : (1 - yvert);
            }
            else{
                depth = depthhort;
                texturetile = texturehor;
                xhor %= 1;
                offset = (sin_a > 0) ? xhor: (1-xhor);
            }


            float proj_height = MapBuilder.unit * player.screen_distance / (depth + 0.0001f);
            test = String.valueOf(player.screen_distance);
            float x = (MapBuilder.width * MapBuilder.unit) - (range * player.Scale);
            float y = (MapBuilder.half_height * MapBuilder.unit) - proj_height / 2f;
            //---projection-----------

            // By Rectangles
            //depth by brightness (not used for textiles so this is canceled out for now)
            //float shade = (float) (128f/Math.pow(depth, 0.9)+ 0.01);
            //L3.setColor(shade, shade, shade, 1f);  // RGB grayscale, full alpha
            //L3.rect(x, y + 250, player.Scale, proj_height+ 50);

            // By Texture
            renderTextureWall(player, proj_height, offset, x, y);
            ray_angle += player.DeltaAngle;

        }
    }



    public void renderTextureWall(PlayerBuilder player, float projheight, float offset, float x, float y){
        Texture texture = textureMap.returnTexture(1);

        TextureRegion wallcolumn = new TextureRegion(texture, (int)(offset * texture.getWidth() - player.Scale), 0,(int) player.Scale, (int)texture.getHeight());
        L2.draw(wallcolumn, (int)x, (int)y+ 250, (int)player.Scale, (int)projheight);
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
        L3.begin(ShapeRenderer. ShapeType.Line);
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

    public void rendermap(){
        for(int j = 0; j < map.height; j++) {
            for (int i = 0; i < map.width; i++) {
                if(map.grid[j][i] == 1) {
                    map.box.x = i*map.unit;
                    map.box.y = (map.height - 1 - j) * map.unit;
                    render(map.box, (int) map.box.x, (int) map.box.y);
                }
            }
        }
    }

    public void render2dplayer(PlayerBuilder player){
        L3.circle(player.getX()+player.hitboxradius, player.getY()+ player.hitboxradius, player.hitboxradius);
        L3.line(player.getCenterX(), player.getCenterY(), player.getCenterX() + player.getCos() * map.unit  , player.getCenterY() + player.getSin() * map.unit);
    }

    public void renderraycast(PlayerBuilder player) {
        float ray_angle = (float) Math.toRadians(player.getRotationDegrees()) - player.HALFFOV;

        float xvert, yvert, dx, dy, xhor, yhor, deltadepth, depthvert, depthhort;
        for (int range = 0; range < player.NumofRays; range++) {
            float sin_a = (float) Math.sin(ray_angle);
            float cos_a = (float) Math.cos(ray_angle);

            //limits so angle dosent get closer to 0 thus ray casting errors arrrive
            if (Math.abs(sin_a) < 1e-30) sin_a = 1e-30f;
            if (Math.abs(cos_a) < 1e-30) cos_a = 1e-30f;

            //Horizontal
            if (sin_a > 0) {
                yhor = (int) (player.getY() / map.unit) * map.unit + map.unit;
                dy = map.unit;
            } else {
                yhor = (int) (player.getY() / map.unit) * map.unit - 0.000000001f; // just above current gridline
                dy = -map.unit;
            }
            depthhort = (yhor - player.getY()) / sin_a;
            xhor = player.getX() + depthhort * cos_a;
            deltadepth = dy / sin_a;
            dx = deltadepth * cos_a;

            for (int i = 0; i < player.Depth; i++) {
                int tilex = (int) xhor;
                int tiley = (int) yhor;
                if (map.Wallhit(tilex, tiley)) {
                    break;
                }
                xhor += dx;
                yhor += dy;
                depthhort += deltadepth;
            }

            //Vertical
            if (cos_a > 0) {
                xvert = (int) (player.getX() / map.unit) * map.unit + map.unit;
                dx = map.unit;

            } else {
                xvert = (int) (player.getX() / map.unit) * map.unit - 0.000000001f;
                dx = -map.unit;
            }


            depthvert = (xvert - player.getX()) / cos_a;
            // Make sure its in the unit

            yvert = player.getY() + depthvert * sin_a;
            test = String.valueOf(yvert);
            deltadepth = dx / cos_a;
            dy = deltadepth * sin_a;

            for (int i = 0; i < player.Depth; i++) {
                int tilex = (int) xvert;
                int tiley = (int) yvert;
                if (map.Wallhit(tilex, tiley)) {
                    break;
                }
                xvert += dx;
                yvert += dy;
                depthvert += deltadepth;
            }

            float depth = Math.min(depthvert, depthhort);

            L3.line(player.getCenterX(), player.getCenterY(), player.getCenterX() - (player.width / 2f) + cos_a * depth, player.getCenterY() - (player.height / 2f) + sin_a * depth);
            ray_angle += player.DeltaAngle;
        }

    }
}
