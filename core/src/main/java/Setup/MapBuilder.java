package Setup;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MapBuilder {
    public int initalx;
    public int initaly;

    public int width;
    public int height;
    public final int unit = 100;

    public Rectangle box;

    public int[][] grid = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };


    public MapBuilder(){
        initalx = 0;
        initaly = 0;
        width = grid[0].length;   // number of columns
        height = grid.length;     // number of rows

        box = new Rectangle(0,0,100, 100);
    }

    public Vector2 getMapCoords(int x, int y){
        if(grid[height-1-y][x] == 1) {
            return new Vector2(x * 100, y * 100);
        }
        return new Vector2(-1, -1);
    }

    public Rectangle getMapHitbox(int x, int y){
        Vector2 coords = getMapCoords(x, y);
        return new Rectangle(coords.x, coords.y, 100, 100);
    }

    /*public boolean Wallhit(int x, int y){
        int newx = x/unit;
        int newy = y/unit;

        if(grid[height-1-newy][newx] == 1){
            return true;
        }
        return false;
    }*/

    public boolean Wallhit(int x, int y) {
        int newx = x / unit;
        int newy = y / unit;

        // check bounds
        if (newx < 0 || newx >= width || newy < 0 || newy >= height) return false;

        // access the grid safely
        return grid[height - 1 - newy][newx] == 1;
    }


}
