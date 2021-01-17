package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.content.Tiles;

import java.lang.ref.SoftReference;

public class World {

    public Tile[][] map;
    public float[][] baseHeat;
    public float[][] heat;

    public float noiseScale = 0.12f;
    public float seaLevel = 0.60f;

    public World() {
        map = new Tile[128][128];
        baseHeat = new float[128][128];
        heat = new float[128][128];

        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                generate(x,y);
            }
        }
    }

    public void updateHeat() {
        //TODO: Make it actually update the heat level
    }

    private void generate(int x, int y) {
        float noiseValue = Util.pow(Util.noise(x*noiseScale,y*noiseScale),0.5f);
        float tempValue = Util.noise(x * noiseScale * 0.7f + 43,y * noiseScale * 0.7f + 75);

        Tile tile;
        if(noiseValue <= seaLevel) {
            if(tempValue > 0.5f) {
                tile = Tiles.water;
            }
            else {
                tile = Tiles.ice;
            }
        }
        else {
            if(tempValue > 0.65f) {
                tile = Tiles.dirt;
            }
            else {
                if(tempValue < 0.3 || noiseValue > seaLevel+0.2f)
                    tile = Tiles.tree;
                else
                    tile = Tiles.snow;
            }
        }

        //Temperature value for tile
        baseHeat[x][y] = 8*tempValue;

        heat[x][y] = baseHeat[x][y];
        addTile(tile,x,y);
    }

    public void addTile(Tile t, int x, int y) {
        map[x][y] = t.clone();
        map[x][y].world = new SoftReference<>(this);
        map[x][y].pos = new Vector2(x,y);
    }

    public float getHeat(Vector2 pos) {
        return heat[pos.x][pos.y];
    }

    public void renderTiles(Vector2 camPos) {
        for(int x = -1; x < 16; x++) {
            for(int y = -1; y < 16; y++) {
                if(Util.inbounds(x+camPos.x,128) && Util.inbounds(y+camPos.y,128)) {
                    map[x + camPos.x][y + camPos.y].updateAnimation();
                    Render.renderImage(map[x + camPos.x][y + camPos.y].graphic, x + camPos.x, y + camPos.y, 32, 32);
                }
            }
        }

        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                map[i][j].update();
            }
        }
    }

    public boolean collides(Vector2 position) {
        return map[position.x][position.y].collision;
    }

    public void stepOn(Vector2 pos,Player p) {
        map[pos.x][pos.y].onStepOn(p);
    }
    public void playerOn(Vector2 pos,Player p) {
        map[pos.x][pos.y].onPlayerUpdate(p);
    }
}
