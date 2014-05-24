package net.dcatcher.enderius.common;

import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class EntityEnderSlime extends EntitySlime implements IMob{

    public EntityEnderSlime(World world) {
        super(world);
        int slimesize = 1 << this.rand.nextInt(3);
        setSlimeSize(slimesize);
    }

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }

    @Override
    protected void collideWithEntity(Entity entity) {
            double x = entity.posX;
            double y = entity.posY;
            double z = entity.posZ;
            Random random = new Random();

            entity.setPosition(x+ (this.rand.nextInt(10) - 5), y + this.rand.nextInt(5), z + (this.rand.nextInt(10) - 5));
            entity.worldObj.playSound(x, y, z, "mob.endermen.portal", 1f, 1f, true);
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
    protected void dropFewItems(boolean par1, int par2) {
        if(this.rand.nextInt(10) < 1){
            this.dropItem(ItemHandler.enderSlime, 1);
        }
    }

    @Override
    protected String getSlimeParticle() {
        return "portal";
    }

    @Override
    protected String getJumpSound() {
        return "mob.slime.big";
    }

    @Override
    protected String getDeathSound() {
        return "mob.endermen.death";
    }

    protected EntityEnderSlime createInstance() {
        return new EntityEnderSlime(worldObj);
    }

    @Override
    public void setDead(){
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(5);

            for (int k = 0; k < j; ++k)
            {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                EntityEnderSlime entity = this.createInstance();
                entity.setSlimeSize(i / 2);
                entity.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entity);
            }
        }

        super.setDead();
    }

}



