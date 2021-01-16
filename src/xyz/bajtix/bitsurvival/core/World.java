package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.Content.Tiles;

public class World {
    public Tile[][] map;

    public float noiseScale = 0.12f;
    public float seaLevel = 0.60f;

    public World() {
        map = new Tile[128][128];

        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                generate(x,y);
            }
        }
    }

    private void generate(int x, int y) {
        float noiseValue = Util.pow(Util.noise(x*noiseScale,y*noiseScale),0.5f);
        float tempValue = Util.noise(x * noiseScale * 0.7f + 43,y * noiseScale * 0.7f + 75);

        if(noiseValue <= seaLevel) {
            if(tempValue > 0.5f)
                map[x][y] = Tiles.water;
            else
                map[x][y] = Tiles.ice;
        }
        else {
            if(tempValue > 0.65f) {
                map[x][y] = Tiles.dirt;
            }
            else {
                if(tempValue < 0.3 || noiseValue > seaLevel+0.2f)
                    map[x][y] = Tiles.tree;
                else
                    map[x][y] = Tiles.snow;
            }
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
