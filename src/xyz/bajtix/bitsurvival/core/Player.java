package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.Tiles;

public class Player {
    public Vector2 position;
    public float health;
    public float energy;

    public float actionDelay;

    public Inventory inventory;
    public int selectedItem = 0;

    public float heatResistance = 0;
    public float heat = 1;

    private float finalHeatLevel;

    public float heatLossSpeed = 0.4f;

    private PImage sprite;
    private World world;

    public Player(PImage graphic, Vector2 spawnPosition)
    {
        this.sprite = graphic;
        this.position = spawnPosition;
        this.world = BitSurvival.bitSurvival.world;
        this.health = 100;
    }

    public void update(char key){
        world = BitSurvival.bitSurvival.world;
        actionDelay -= Util.deltaTime();

        heat = Util.lerp(heat,world.getHeat(position),Util.deltaTime() / 1000 * heatLossSpeed);
        finalHeatLevel = heat + heatResistance;

        heatDamage();

        world.playerOn(position,this);
        movement(key);
        updateCamera();
        render();
    }

    public void heatDamage() {
        float dm = Util.clamp(15 - finalHeatLevel,0,15);
        damage(dm  * (Util.deltaTime()/1000) * 0.1f);
    }

    public void damage(float a) {
        health -= a;

        if(health <= 0){
            //die
            BitSurvival.bitSurvival.stop();
        }
    }

    private void updateCamera() {
        Render.cameraPosition = new Vector2(position.x-7,position.y-7);
    }

    private void render() {
        Render.renderImage(sprite,position.x,position.y);
    }

    private void movement(char key) {

        if(actionDelay <= 0)
        {
            boolean moved = false;
            if(key=='a')
            {
                if(world.collides(position.add(-1,0))) return;
                position.x -=1;
                moved = true;
            }
            if(key=='d') {
                if(world.collides(position.add(1,0))) return;
                position.x +=1;
                moved = true;
            }
            if(key=='w') {
                if(world.collides(position.add(0,-1))) return;
                position.y -=1;
                moved = true;
            }
            if(key=='s') {
                if(world.collides(position.add(0,1))) return;
                position.y +=1;
                moved = true;
            }
            if(key == 'e') {
                BitSurvival.bitSurvival.world.addTile(Tiles.bonfire, position.x, position.y);
                actionDelay = 300;
            }
            if(key == 'q') {
                BitSurvival.bitSurvival.world.addTile(Tiles.snow, position.x, position.y);
                actionDelay = 300;
            }
            if(moved) {
                actionDelay = 200;
                BitSurvival.bitSurvival.world.stepOn(position,this);
            }
        }
    }
}
