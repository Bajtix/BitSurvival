package xyz.bajtix.bitsurvival.core;

import java.lang.ref.SoftReference;

public class ToolItem extends Item {

    protected int useDelay = 100;
    protected int baseDmg = 100;
    protected String destroyTag = "";
    protected  int meleeDmg;
    protected int harvestLevel;

    public ToolItem(String name, int maxStackSize, String destroys, int baseDmg, int meleeDmg, int harvestLevel, int useDelay) {
        super(name, maxStackSize);
        this.useDelay = useDelay;
        this.baseDmg = baseDmg;
        this.destroyTag = destroys;
        this.meleeDmg = meleeDmg;
        this.harvestLevel = harvestLevel;
    }

    @Override
    public void interact(Vector2 pos, SoftReference<ItemStack> in, SoftReference<World> world, SoftReference<Player> player) {
        if(world.get().map[pos.x][pos.y] instanceof Resource) {

            if(((Resource)world.get().map[pos.x][pos.y]).getTag() == destroyTag) {


                if (((Resource) world.get().map[pos.x][pos.y]).getHarvestLevel() <= harvestLevel) {
                    ItemStack s = ((Resource) world.get().map[pos.x][pos.y]).hit(baseDmg, player, world);
                    player.get().inventory.insert(s);
                }

                player.get().actionDelay = useDelay;
            }
        }
    }
}
