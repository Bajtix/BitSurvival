package xyz.bajtix.bitsurvival.core;

import java.lang.ref.SoftReference;

public class TilePlaceItem extends Item{
    protected SoftReference<Tile> toPlace;

    public TilePlaceItem(String name, int maxStackSize, SoftReference<Tile> tileToPlace) {
        super(name, maxStackSize);
        this.toPlace = tileToPlace;
    }

    @Override
    public void interact(Vector2 pos, SoftReference<ItemStack> in, SoftReference<World> world) {
        world.get().addTile(toPlace.get(),pos);
        in.get().count--;
    }
}
