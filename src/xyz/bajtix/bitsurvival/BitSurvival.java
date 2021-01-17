package xyz.bajtix.bitsurvival;

import processing.core.PApplet;
import processing.core.PFont;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.*;

import java.lang.ref.SoftReference;

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
        player = new Player(loadImage("data/player.png"),new Vector2(64,64));
        UIManager.initialize();
        PFont pixel = createFont("font.ttf",64);
        textFont(pixel);
        noStroke();

        UIManager.open(new SoftReference<>(GUIs.baseGameGUI));
    }

    public void draw() {
        deltaTime = millis() - lastTime;
        lastTime = millis();

        background(0);
        Render.updateCamera();
        world.renderTiles(Render.cameraPosition);
        player.update(key);

        UIManager.update(key);
        key = ' ';
    }





}
