package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright: DCatcher
 */
public class ItemEnderSlime extends Item {

    NBTTagCompound data;
    int id;


    public ItemEnderSlime(){
        super();
        setUnlocalizedName("enderSlime");
        setCreativeTab(Enderius.danTab);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("enderius:enderslime");
    }

    public void setData(NBTTagCompound nbt,int id){
        this.data = nbt;
        this.id = id;
    }

    public NBTTagCompound getData(){
        return data;
    }

    public void storeNBT(ItemStack stack, Entity entity){
        NBTTagCompound entityData = entity.getEntityData();


    }

    public int getID(){
        return id;
    }


}
