package xyz.bajtix.bitsurvival;

import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.opengl.PGraphicsOpenGL;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.*;


public class BitSurvival extends PApplet {

    public static BitSurvival bitSurvival;

    public World world;
    public Player player;

    private int lastTime = 0; // avars used for deltaTime

    private PFont pixel;
    public int deltaTime = 0;

    public static void main(String[] args) {
        String[] processingArgs = {"BitSurvival"};
        bitSurvival = new BitSurvival();
        GameLogger.debug("Starting BitSurvival!");
        PApplet.runSketch(processingArgs,bitSurvival);
    }

    public void settings() {
        size(480,480);
        noSmooth(); // using this to set texture sampling to Nearest Neighbour
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        Keys.registerKey(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);

        Keys.unregisterKey(event.getKeyCode());
    }

    public void setup()
    {

        pixel = createFont("font.ttf",16);
        textFont(pixel);
        UIManager.initialize();

        UIManager.open(GUIs.loadingGUI);
        UIManager.update();
        surface.setIcon(loadImage("data/player.png"));
        Tiles.loadTiles();
        Items.regAll();
        EventSystem.initialize();
        world = new World();
        player = new Player(loadImage("data/player.png"),new Vector2(World.WORLD_SIZE/2,World.WORLD_SIZE/2));
        UIManager.initialize();
        UIManager.open(GUIs.baseGameGUI);

        GameLogger.debug("Finished setup.");

    }

    public void draw() {
        deltaTime = millis() - lastTime;
        lastTime = millis();

        background(0);
        textFont(pixel);
        textSize(24);textAlign(BitSurvival.LEFT,BitSurvival.BOTTOM);
        noStroke();

        Render.updateCamera();
        world.renderTiles(Render.cameraPosition);
        player.update();

        UIManager.update();
        key = ' ';

        Keys.frame();
    }





}
