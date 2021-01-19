package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.content.Tiles;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class World {

    public Tile[][] map;
    public float[][] baseHeat;
    public float[][] heat;

    public float noiseScale = 0.12f;
    public float seaLevel = 0.60f;

    public static boolean renderTemp = true;

    protected ArrayList<HeatEmitter> heatEmitters;

    public World() {
        map = new Tile[128][128];
        baseHeat = new float[128][128];
        heat = new float[128][128];
        heatEmitters = new ArrayList<>();

        int i = 0;
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                generate(x,y);

                i++;
            }
            GameLogger.debug("Generating tile (" + i + "/" + (map.length * map[0].length) + ")");
        }
    }

    /**
     * Refreshes the heat level for every tile on the map
     */
    public void updateHeat() {
        for(int i = 0; i < map.length;  i++) {
            for(int j = 0; j < map[0].length;  j++) {
                heat[i][j] = baseHeat[i][j];
            }
        }


        for (HeatEmitter e : heatEmitters) {

            Vector2 heatCenter = e.getHeatCenter();
            float heatPower = e.getHeatLevel();
            float heatRange = e.getHeatRange();

            for(int i = Util.round(-heatRange); i < heatRange*2;  i++) {
                for(int j = Util.round(-heatRange); j < heatRange*2;  j++) {
                    if(Util.inbounds(i + heatCenter.x,128) && Util.inbounds(j + heatCenter.y,128)) {

                        float d = Vector2.distance(new Vector2(i,j),new Vector2());
                        if(d < heatRange) {
                            float hv = Util.lerp(heatPower,baseHeat[i + heatCenter.x][j + heatCenter.y],Util.pow(d/heatRange,2f));
                            heat[i + heatCenter.x][j + heatCenter.y] = Util.max(heat[i + heatCenter.x][j + heatCenter.y],hv);
                        }
                    }
                }
            }
        }
    }

    /**
     * Registers a heat emitter
     * @param emitter The emitter object to register
     */
    public void addHeatEmitter(SoftReference<HeatEmitter> emitter) {
        GameLogger.debug("Adding a heat source @ " + emitter.get().getHeatCenter());
        heatEmitters.add(emitter.get());
        updateHeat();
    }

    /**
     * Removes a heat emitter
     * @param at The emitter to remove
     */
    public void removeHeatEmitter(SoftReference<HeatEmitter> at) {
        GameLogger.debug("Removing a heat source @ " + at.get().getHeatCenter());
        heatEmitters.remove(at.get());
        updateHeat();
    }

    /**
     * Generates tile at position
     * @param x Position X
     * @param y Position Y
     */
    private void generate(int x, int y) {
        float noiseValue = Util.pow(Util.noise(x*noiseScale,y*noiseScale),0.5f);
        float tempValue = Util.noise(x * noiseScale * 0.7f + 43,y * noiseScale * 0.7f + 75);

        //Temperature value for tile
        baseHeat[x][y] = 8*tempValue;
        // 0 = -6
        // 6 = 0
        heat[x][y] = baseHeat[x][y];

        Tile tile;
        if(noiseValue <= seaLevel) {
            if(baseHeat[x][y] > 4.5f) { // -1.5deg
                tile = Tiles.water;
            }
            else {
                tile = Tiles.ice;
            }
        }
        else {
            if(baseHeat[x][y] > 5.5f) { //-0.5deg
                tile = Tiles.dirt;
            }
            else {
                if(tempValue < 0.3 || noiseValue > seaLevel+0.2f) {
                    tile = Tiles.tree;
                }
                else {
                    tile = Tiles.snow;

                    if(Util.random(100) <= 2) {
                        tile = Tiles.bush;
                    }
                }
            }
        }
        addTile(tile,x,y);
    }

    /**
     * Place a tile at position
     * @param t The tile to instantiate
     * @param pos The position where to place it
     */
    public void addTile(Tile t, Vector2 pos) {
        addTile(t,pos.x,pos.y);
    }

    /**
     * Place a tile at position
     * @param t The tile to instantiate
     * @param x The position X
     * @param y The position Y
     */
    public void addTile(Tile t, int x, int y) {
        if(!Util.inbounds(x,map.length) || !Util.inbounds(y,map[0].length)) {
            GameLogger.warning("Attempted to add a tile outside of the existing world");
            return;
        }

        Tile previous = null;
        if(map[x][y] != null) {
            map[x][y].onReplace();
            previous = map[x][y];
        }
        map[x][y] = Tiles.getTileById(t.id).clone();
        map[x][y].world = new SoftReference<>(this);
        map[x][y].pos = new Vector2(x,y);
        map[x][y].onPlace(previous);
    }

    /**
     * Returns the heat at given position
     * @param pos The position where to probe the temperature
     * @return The temperature
     */
    public float getHeat(Vector2 pos) {
        return heat[pos.x][pos.y];
    }

    /**
     * Renders tiles
     * @param camPos camera position
     */
    public void renderTiles(Vector2 camPos) {
        for(int x = -1; x < 16; x++) {
            for(int y = -1; y < 16; y++) {
                if(Util.inbounds(x+camPos.x,128) && Util.inbounds(y+camPos.y,128)) {
                    map[x + camPos.x][y + camPos.y].updateAnimation();
                    Render.renderImage(map[x + camPos.x][y + camPos.y].graphic, x + camPos.x, y + camPos.y, 32, 32);
                    if(renderTemp) {
                        Render.fill(255,64,0,Util.round(heat[x+camPos.x][y+camPos.y] * 4));
                        Render.renderRect(x+camPos.x,y+camPos.y,32,32);
                    }
                }
            }
        }

        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                map[i][j].update();
            }
        }
    }

    /**
     * Checks if the tile at position has collision
     * @param position The tile position
     * @return The object's collision?
     */
    public boolean collides(Vector2 position) {
        return map[position.x][position.y].collision;
    }

    /**
     * Triggers stepOn interaction on a tile
     * @param pos Where to trigger the interaction
     * @param p The player
     */
    public void stepOn(Vector2 pos,Player p) {
        map[pos.x][pos.y].onStepOn(p);
    }

    /**
     * Triggers playerOn interaction on a tile
     * @param pos Where to trigger the interaction
     * @param p The player
     */
    public void playerOn(Vector2 pos,Player p) {
        map[pos.x][pos.y].onPlayerUpdate(p);
    }

    /**
     * Triggers interaction on a tile
     * @param pos Where to trigger the interaction
     * @param p The player
     */
    public void interact(Vector2 pos,Player p) {
        map[pos.x][pos.y].interact(p);
    }
}
