package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;

public class Player {
    public Vector2 position;
    public float health;
    public float energy;
    public float actionDelay;

    private PImage sprite;
    private World world;

    public Player(PImage graphic, Vector2 spawnPosition,World world)
    {
        this.sprite = graphic;
        this.position = spawnPosition;
        this.world = world;
    }

    public void update(char key){
        actionDelay -= Util.deltaTime();
        movement(key);
        updateCamera();
        render();
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
            if(moved)
                actionDelay = 200;
        }
    }
}
