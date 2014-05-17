package net.dcatcher.enderius.client.gui;

import cpw.mods.fml.client.GuiConfirmation;
import net.dcatcher.enderius.common.tileentities.ContainerRepellent;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Copyright: DCatcher
 */
public class GuiRepellent extends GuiContainer {

    TileEntityRepellent tileEntity;

    private GuiTextField xCoord, yCoord, zCoord;


    public GuiRepellent(TileEntityRepellent te) {
        super(new ContainerRepellent(te));
        this.tileEntity = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        this.mc.renderEngine.bindTexture(new ResourceLocation("enderius", "textures/gui/repellent.png"));
        int x = (width - xSize) /2;
        int y = (height - ySize) /2;

        this.drawTexturedModalRect(x, y, 0,0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p1, int p2) {
        fontRendererObj.drawString("Position to teleport users out of: ", 5, 5, 4210752);

        xCoord = new GuiTextField(fontRendererObj, 5, 10, 40, 10);
        xCoord.drawTextBox();
        yCoord = new GuiTextField(fontRendererObj, 5, 20, 40, 10);
        yCoord.drawTextBox();

        zCoord = new GuiTextField(fontRendererObj, 5, 30, 40, 10);
        zCoord.drawTextBox();



    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }

    public void syncToTILEENTITY(GuiButton butt){
        //tileEntity.locX =
    }
}
