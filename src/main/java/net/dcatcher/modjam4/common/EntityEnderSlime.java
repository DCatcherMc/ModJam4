package net.dcatcher.modjam4.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class EntityEnderSlime extends EntitySlime {

    public EntityEnderSlime(World world) {
        super(world);
        int slimesize = 1 << this.rand.nextInt(5);
        setSlimeSize(slimesize);
        if(this.rand.nextInt(100) > 2){
            this.ridingEntity = new EntityEnderman(world);
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        double playerX = player.posX;
        double playerY = player.posY;
        double playerZ = player.posZ;
        Random random = new Random();

        player.setPosition(playerX + (this.rand.nextInt(10) - 5), playerY + this.rand.nextInt(5), playerZ + (this.rand.nextInt(10) - 5));
        player.playSound("mob.endermen.portal", 1f, 1f);
    }

    @Override
    protected String getSlimeParticle() {
        return "portal";
    }

    @Override
    protected String getJumpSound() {
        return "mob.endermen.idle";
    }
}



