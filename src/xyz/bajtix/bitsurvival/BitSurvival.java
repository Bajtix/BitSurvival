package xyz.bajtix.bitsurvival;

import processing.core.PApplet;
import processing.core.PImage;
import xyz.bajtix.bitsurvival.Content.Tiles;
import xyz.bajtix.bitsurvival.core.*;

public class BitSurvival extends PApplet {

    public static BitSurvival bitSurvival;

    public World world;
    public Player player;

    private int lastTime = 0;

    public int deltaTime = 0;

    public static void main(String[] args) {
        String[] processingArgs = {"BitSurvival"};
        bitSurvival = new BitSurvival();
        PApplet.runSketch(processingArgs,bitSurvival);
    }

    public void settings() {
        size(480,480);
    }

    public void setup()
    {
        surface.setIcon(loadImage("data/player.png"));
        Tiles.loadTiles();
        world = new World();
        player = new Player(loadImage("data/player.png"),new Vector2(64,64),world);
    }

    public void draw() {
        deltaTime = millis() - lastTime;
        lastTime = millis();

        background(0);
        Render.updateCamera();
        world.renderTiles(Render.cameraPosition);
        player.update(key);
        key = ' ';
    }





}
