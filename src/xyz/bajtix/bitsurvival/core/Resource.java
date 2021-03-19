package xyz.bajtix.bitsurvival.core;

public interface Resource {
    /**
     * What tile should be placed after this one breaks?
     * @return The tile to place
     */
    Tile getBackupTile();

    /**
     * Deals damage to tile
     * @param damage How much damage to deal
     * @return The drop after the hit
     */
    ItemStack hit(float damage, Player player, World world);

    /**
     * What tool should damage the tile
     * @return The tag
     */
    String getTag();

    /**
     * What tool tier should break the tile?
     * @return The tool tier harvest level.
     */
    int getHarvestLevel();
}
