package xyz.bajtix.bitsurvival.core;

public class ToolItem extends Item {

    protected int useDelay;
    protected int baseDmg;
    protected String destroyTag;
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
    public void interact(Vector2 pos, ItemStack in, World world, Player player) {
        if(world.map[pos.x][pos.y] instanceof Resource) {

            if(((Resource)world.map[pos.x][pos.y]).getTag() == destroyTag) {


                if (((Resource) world.map[pos.x][pos.y]).getHarvestLevel() <= harvestLevel) {
                    ItemStack s = ((Resource) world.map[pos.x][pos.y]).hit(baseDmg, player, world);
                    player.inventory.insert(s);
                }

                player.actionDelay = useDelay;
            }
        }
    }
}
