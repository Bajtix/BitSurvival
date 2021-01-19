package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.content.Tiles;

import java.lang.ref.SoftReference;

public class TilePlaceItem extends Item{
    protected SoftReference<Tile> toPlace;
    protected Tile[] placeOn;

    public TilePlaceItem(String name, int maxStackSize, SoftReference<Tile> tileToPlace, Tile... placeOn) {
        super(name, maxStackSize);
        this.toPlace = tileToPlace;
        this.placeOn = placeOn;
    }

    @Override
    public void interact(Vector2 pos, SoftReference<ItemStack> in, SoftReference<World> world, SoftReference<Player> player) {
        if(Tiles.isTileInTiles(world.get().map[pos.x][pos.y],placeOn)) {
            world.get().addTile(toPlace.get(), pos);
            in.get().count--;
        }
    }
}
