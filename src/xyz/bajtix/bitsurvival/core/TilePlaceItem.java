package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.content.Tiles;

public class TilePlaceItem extends Item{
    protected Tile toPlace;
    protected Tile[] placeOn;

    public TilePlaceItem(String name, int maxStackSize, Tile tileToPlace, Tile... placeOn) {
        super(name, maxStackSize);
        this.toPlace = tileToPlace;
        this.placeOn = placeOn;
    }

    @Override
    public void interact(Vector2 pos, ItemStack in, World world, Player player) {
        if(Tiles.isTileInTiles(world.map[pos.x][pos.y],placeOn)) {
            world.addTile(toPlace, pos);
            in.count--;
        }
    }
}
