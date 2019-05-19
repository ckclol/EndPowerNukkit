package cn.nukkit.utils.spawners;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.BaseEntity;
import cn.nukkit.entity.passive.EntitySheep;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.AbstractEntitySpawner;
import cn.nukkit.utils.EntityUtils;
import cn.nukkit.utils.Spawner;
import cn.nukkit.utils.SpawnResult;

public class SheepSpawner extends AbstractEntitySpawner {

    public SheepSpawner(Spawner spawnTask) {
        super(spawnTask);
    }

    public SpawnResult spawn(Player player, Position pos, Level level) {
        SpawnResult result = SpawnResult.OK;

        if (level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z) != Block.GRASS) {
            result = SpawnResult.WRONG_BLOCK;
        } else if (pos.y > 255 || pos.y < 1) {
            result = SpawnResult.POSITION_MISMATCH;
        } else if (level.getName().equals("nether") || level.getName().equals("end")) {
            result = SpawnResult.WRONG_BIOME;
        } else {
            BaseEntity entity = this.spawnTask.createEntity("Sheep", pos.add(0, 1, 0));
            if (EntityUtils.rand(1, 20) == 1) {
                entity.setBaby(true);
            }
        }

        return result;
    }

    @Override
    public final int getEntityNetworkId() {
        return EntitySheep.NETWORK_ID;
    }
}