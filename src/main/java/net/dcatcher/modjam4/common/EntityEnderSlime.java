package net.dcatcher.modjam4.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class EntityEnderSlime extends EntitySlime {

    public EntityEnderSlime(World par1World) {
        super(par1World);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        player.attackEntityAsMob(this);
        double playerX = player.posX;
        double playerY = player.posY;
        double playerZ = player.posZ;
        Random random = new Random();

        player.setPosition(playerX + (random.nextDouble()*5), playerY + 2, playerZ + (random.nextDouble()*5));
    }

    @Override
    protected String getSlimeParticle() {
        return "portal";
    }
}



