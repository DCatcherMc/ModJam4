package net.dcatcher.enderius.client.gui;

import cpw.mods.fml.client.GuiConfirmation;
import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.network.PacketRepellent;
import net.dcatcher.enderius.common.tileentities.ContainerRepellent;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import scala.Int;
import scala.tools.nsc.Global;

/**
 * Copyright: DCatcher
 */
public class GuiRepellent extends GuiContainer {

    TileEntityRepellent tileEntity;

    private GuiTextField xCoord, yCoord, zCoord;
    private GuiButton butt;


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
    }

    @Override
    public void initGui() {
        super.initGui();
        xCoord = new GuiTextField(fontRendererObj, ((width-xSize)/2)  + 5, ((height - ySize) /2) + 10, 40, 10);
        yCoord = new GuiTextField(fontRendererObj, ((width-xSize)/2)  + 5, ((height - ySize) /2) + 20, 40, 10);
        zCoord = new GuiTextField(fontRendererObj, ((width-xSize)/2)  + 5, ((height - ySize) /2) + 30, 40, 10);
        butt = new GuiButton(0, ((width-xSize)/2)  + 5, ((height - ySize) /2) + 10, 100, 20, "Save");

        xCoord.setText(""+tileEntity.locX);
        yCoord.setText(""+tileEntity.locY);
        zCoord.setText(""+tileEntity.locZ);
        buttonList.add(butt);
        }

    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
        xCoord.drawTextBox();
        yCoord.drawTextBox();
        zCoord.drawTextBox();
        butt.drawButton(mc, i, j);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int locX = Integer.parseInt(xCoord.getText());
        int locY = Integer.parseInt(yCoord.getText());
        int locZ = Integer.parseInt(zCoord.getText());

        PacketRepellent packetRepel = new PacketRepellent(locX, locY, locZ, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        Enderius.packetPipeline.sendToServer(packetRepel);
        System.out.println("Sending Packet");
    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        xCoord.mouseClicked(i, j, k);
        yCoord.mouseClicked(i, j, k);
        zCoord.mouseClicked(i, j, k);
        //butt.mousePressed(mc, i, j);
    }


    @Override
    protected void keyTyped(char par1, int par2) {
        super.keyTyped(par1, par2);

        if(Character.isDigit(par1) || par1 == '-' || par2 == Keyboard.KEY_BACK){
            if(xCoord.isFocused()){
                xCoord.textboxKeyTyped(par1, par2);
            }
            if(yCoord.isFocused()){
                yCoord.textboxKeyTyped(par1, par2);
            }
            if(zCoord.isFocused()){
                zCoord.textboxKeyTyped(par1, par2);
            }
        }
    }

}
