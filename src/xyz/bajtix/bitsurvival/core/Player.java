package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.content.Items;

import java.lang.ref.SoftReference;

public class Player {
    public Vector2 position;
    public float health;
    public float energy;

    public float actionDelay;

    public Inventory inventory;
    public Inventory equipped;
    public int selectedItem = 0;

    public float heatResistance = 0;
    public float heat = 1;

    public float finalHeatLevel;

    private final float heatLossSpeed = 0.4f;
    public float speedMultiplier;

    private final PImage sprite;
    private World world;

    public Player(PImage graphic, Vector2 spawnPosition)
    {
        this.sprite = graphic;
        this.position = spawnPosition;
        this.world = BitSurvival.bitSurvival.world;
        this.health = 100;
        this.inventory = new Inventory(8);
        this.equipped = new Inventory(8);

        this.inventory.insert(new ItemStack(Items.woodAxe,1));
    }

    public void update(){

        world = BitSurvival.bitSurvival.world;
        actionDelay -= Util.deltaTime();

        heat = Util.lerp(heat,world.getHeat(position),Util.deltaTime() / 1000 * heatLossSpeed);
        finalHeatLevel = heat + heatResistance;

        heatDamage();
        inventory.validate();
        equipped.validate();

        world.playerOn(position,this);
        movement();
        updateCamera();
        render();
    }

    public void heatDamage() {
        float dm = Util.clamp(15 - finalHeatLevel,0,15);
        damage(dm  * (Util.deltaTime()/1000) * 0.1f, true);
    }

    public void damage(float a, boolean force) {
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

    private void useItem(Vector2 at) {
        if(inventory.get(selectedItem) != null && inventory.get(selectedItem).get() != null) {
            inventory.get(selectedItem).get().item.interact(at,inventory.get(selectedItem), new SoftReference<>(BitSurvival.bitSurvival.world), new SoftReference<>(this) );
        }
    }

    private void movement() {

        if(UIManager.getOpen().get() != GUIs.baseGameGUI) return;
        if(actionDelay <= 0)
        {
            boolean moved = false;
            if(Keys.isPressed('A'))
            {
                if(world.collides(position.add(-1,0))) return;
                position.x -=1;
                moved = true;
            }
            else if(Keys.isPressed('D')) {
                if(world.collides(position.add(1,0))) return;
                position.x +=1;
                moved = true;
            }
            else if(Keys.isPressed('W')) {
                if(world.collides(position.add(0,-1))) return;
                position.y -=1;
                moved = true;
            }
            else if(Keys.isPressed('S')) {
                if(world.collides(position.add(0,1))) return;
                position.y +=1;
                moved = true;
            }

            if(Keys.isDown('I')) {
                UIManager.open(new SoftReference<>(GUIs.inventoryGUI));
            }

            for (int i = 0; i < 8; i++) { //Select item number hotkeys
                if(Keys.isPressed(("" + (i+1)).charAt(0))) {
                    selectedItem = i;
                }
            }

            if(Keys.isDown('E')) {
                world.interact(position,this);
            }

            if(Keys.isDown(38)) { //up
                useItem(position.add(0,-1));
            }

            if(Keys.isDown(40)) { //down
                useItem(position.add(0,1));
            }

            if(Keys.isDown(37)) { //left
                useItem(position.add(-1,0));
            }

            if(Keys.isDown(39)) { //right
                useItem(position.add(1,0));
            }

            if(moved) {
                actionDelay = 200 * BitSurvival.bitSurvival.world.map[position.x][position.y].getPlayerSpeed();
                BitSurvival.bitSurvival.world.stepOn(position,this);
            }
        }
    }
}
