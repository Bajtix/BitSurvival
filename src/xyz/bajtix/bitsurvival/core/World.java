package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.Content.Tiles;

public class World {
    public Tile[][] map;


    public World() {
        map = new Tile[128][128];

        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                generate(x,y);
            }
        }
    }

    private void generate(int x, int y)
    {
        if(Util.noise(x*0.1f,y*0.1f) > 0.63f) {
            map[x][y] = Tiles.dirt;
            if(Util.noise(x*0.1f,y*0.1f) > 0.65f)
                map[x][y] = Tiles.water;
        }
        else {
            map[x][y] = Tiles.snow;
            if(Util.noise(x*0.2f,y*0.2f) > 0.55f)
                map[x][y] = Tiles.tree;
        }
    }

    public void renderTiles(Vector2 camPos) {
        for(int x = -1; x < 16; x++) {
            for(int y = -1; y < 16; y++) {
                if(Util.inbounds(x+camPos.x,128) && Util.inbounds(y+camPos.y,128))
                    Render.renderImage(map[x+camPos.x][y+camPos.y].graphic,x+camPos.x,y+camPos.y, 32,32);
            }
        }
    }

    public boolean collides(Vector2 position) {
        return map[position.x][position.y].collision;
    }
}
