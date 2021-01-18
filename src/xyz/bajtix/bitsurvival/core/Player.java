package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.content.Tiles;

import java.lang.ref.SoftReference;

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
        this.inventory = new Inventory(8);


    }

    public void update(char key){
        world = BitSurvival.bitSurvival.world;
        actionDelay -= Util.deltaTime();

        heat = Util.lerp(heat,world.getHeat(position),Util.deltaTime() / 1000 * heatLossSpeed);
        finalHeatLevel = heat + heatResistance;

        heatDamage();
        inventory.validate();
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

    public SoftReference<ItemStack> getSelectedItemStack(){
        return inventory.get(selectedItem);
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
            if(key == 't') {
                getSelectedItemStack().get().item.interact(position.add(0,1),getSelectedItemStack(),new SoftReference<>(BitSurvival.bitSurvival.world));
                actionDelay = 300;
            }
            if(key == 'r') {
                inventory.insert(new ItemStack(Items.bonfire,1));
                actionDelay = 300;
            }


            //TODO: fix this part
            if(key == '1') {
                selectedItem = 0;
                actionDelay = 300;
            }
            if(key == '2') {
                selectedItem = 1;
                actionDelay = 300;
            }
            if(key == '3') {
                selectedItem = 2;
                actionDelay = 300;
            }
            if(key == '4') {
                selectedItem = 3;
                actionDelay = 300;
            }
            if(key == '5') {
                selectedItem = 4;
                actionDelay = 300;
            }
            if(key == '6') {
                selectedItem = 5;
                actionDelay = 300;
            }
            if(key == '7') {
                selectedItem = 6;
                actionDelay = 300;
            }
            if(key == '8') {
                selectedItem = 7;
                actionDelay = 300;
            }

            if(moved) {
                actionDelay = 200;
                BitSurvival.bitSurvival.world.stepOn(position,this);
            }
        }
    }
}
