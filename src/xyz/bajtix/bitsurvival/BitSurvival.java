package xyz.bajtix.bitsurvival;

import processing.core.PApplet;
import processing.core.PImage;
import xyz.bajtix.bitsurvival.core.Item;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Vector2;

public class BitSurvival extends PApplet {
    public Tile[][] map;
    Vector2 playerPos;
    Vector2 camPos;
    float actionDelay = 0;

    public static void main(String[] args) {
        String[] processingArgs = {"BitSurvival"};
        BitSurvival bitSurvival = new BitSurvival();
        PApplet.runSketch(processingArgs,bitSurvival);
    }

    public void settings() {
        size(480,480);
    }

    public void setup()
    {
        surface.setIcon(loadImage("data/player.png"));
        playerPos = new Vector2(64,64);
        loadSprites();
        loadMap();
    }

    PImage player;
    void loadSprites() {
        player = loadImage("data/player.png");

        loadTiles();
    }

    Item
    wood = new Item("WOOD____",64);


    Tile
    dirt,
    snow,
    tree,
    water;

    void loadTiles() {
        dirt = new Tile(0,loadImage("data/dirt.png"),false);
        snow = new Tile(1,loadImage("data/snow.png"),false);
        tree = new Tile(2,loadImage("data/tree.png"),true);
        water = new Tile(3,loadImage("data/water.png"),true);
    }

    public void draw() {
        processInput();
        camPos = new Vector2(playerPos.x-7,playerPos.y-7);

        actionDelay-=1; //TODO: Make it work based on time, and not on frame

        background(0);
        renderTiles();
        image(player,playerPos.x * 32 - camPos.x*32,playerPos.y*32 - camPos.y*32);
    }

    void loadMap() {
        map = new Tile[128][128];

        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {
                if(noise(x*0.1f,y*0.1f) > 0.63f) {
                    map[x][y] = dirt;
                    if(noise(x*0.1f,y*0.1f) > 0.65f)
                        map[x][y] = water;
                }
                else {
                    map[x][y] = snow;
                    if(noise(x*0.2f,y*0.2f) > 0.55f)
                        map[x][y] = tree;
                }
            }
        }

    }

    void renderTiles()
    {
        for(int x = 0; x < 15; x++) {
            for(int y = 0; y < 15; y++) {
                image(map[x+camPos.x][y+camPos.y].graphic,x*32,y*32, 32,32);
            }
        }
    }




    void processInput()
    {
        if(actionDelay <= 0)
        {
            boolean moved = false;
            if(key=='a')
            {
                if(map[playerPos.x - 1][playerPos.y].collision) return;
                playerPos.x -=1;
                moved = true;
            }
            if(key=='d') {
                if(map[playerPos.x + 1][playerPos.y].collision) return;
                playerPos.x +=1;
                moved = true;
            }
            if(key=='w') {
                if(map[playerPos.x][playerPos.y-1].collision) return;
                playerPos.y -=1;
                moved = true;
            }
            if(key=='s') {
                if(map[playerPos.x][playerPos.y+1].collision) return;
                playerPos.y +=1;
                moved = true;
            }
            if(moved)
                actionDelay=20;
        }
        key = ' ';
    }

}
