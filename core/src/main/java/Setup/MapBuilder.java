package Setup;

import com.badlogic.gdx.math.Rectangle;

public class MapBuilder {
    public int initalx;
    public int initaly;

    public int width;
    public int height;

    public Rectangle box;

    public int[][] grid = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
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


}
