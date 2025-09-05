package Setup;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import sun.jvm.hotspot.gc.z.ZGranuleMapForForwarding;

public class MapBuilder {
    public int initalx;
    public int initaly;

    public static int width;
    public static int height;
    public static float half_height;
    public static float half_width;
    public static final int unit = 50;

    public Rectangle box;

   public int[][] grid = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };



    // Testing Grid
    /* public int[][] grid = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
     */


    public MapBuilder(){
        initalx = 0;
        initaly = 0;
        width = grid[0].length;   // number of columns
        height = grid.length;     // number of rows
        half_width = width /2f;
        half_height /= height /2f;
        box = new Rectangle(0,0,unit, unit);
    }

    public Vector2 getMapCoords(int x, int y){
        if(grid[height-1-y][x] != 0) {
            return new Vector2(x * unit, y * unit);
        }
        return new Vector2(-1, -1);
    }

    public int getGridNum(float x, float y){
        for(int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Rectangle rect = getMapHitbox(i, j);
                if(rect.contains(x, y)){
                    return grid[height-1-j][i];
                }
            }
        }
        return -1;
    }

    public Rectangle getMapHitbox(int x, int y){
        Vector2 coords = getMapCoords(x, y);
        if(grid[height-1-y][x] != 0) {  // Checks grid to see if there is a wall there dont use this
            return new Rectangle(coords.x, coords.y, unit, unit);
        }
        return new Rectangle(0, 0, -1,-1);
    }

    public  boolean isWall(int x, int y){
        if(grid[height-1-y][x] == 0){
            return false;
        }
        return true;
    }

    public int getMapWall(int x, int y) {
        if (grid[height - 1 - y][x] == 1) {
            return 1;
        }
        if (grid[height - 1 - y][x] == 2) {
            return 2;
        }
        return -1;
    }

    public boolean Wallhit(float x, float y) {
        for(int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Rectangle rect = getMapHitbox(i, j);
                if(rect.contains(x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public Rectangle WallHitRect(float x, float y){
        for(int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Rectangle rect = getMapHitbox(i, j);
                if(rect.contains(x, y)){
                    return rect;
                }
            }
        }
        return new Rectangle(-1, -1, 0,0);
    }



}
