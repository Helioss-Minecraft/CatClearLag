package me.time6628.clag.sponge.handlers;

import me.time6628.clag.sponge.CatClearLag;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.hanging.Hanging;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ConstructEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.world.Chunk;

import java.util.Optional;

/**
 * Created by TimeTheCat on 1/15/2017.
 */
public class MobEventHandler {
    private final CatClearLag plugin = CatClearLag.instance;

    @Listener
    public void onMobSpawn(ConstructEntityEvent.Pre event, @Root Entity entity) {
        if (entity instanceof Living || entity instanceof Hanging) {
            Optional<Chunk> chunk = entity.getWorld().getChunk(entity.getLocation().getChunkPosition());
            chunk.ifPresent(chunk1 -> {
                if (chunk1.getEntities().stream().filter(entity1 -> entity1 instanceof Living || entity1 instanceof Hanging).count() >= plugin.getMobLimitPerChunk()) {
                    event.setCancelled(true);
                }
            });
        }
    }
}
