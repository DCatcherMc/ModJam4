package net.dcatcher.enderius.client.gui;

import cpw.mods.fml.client.GuiConfirmation;
import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.network.PacketRepellent;
import net.dcatcher.enderius.common.network.PacketSync;
import net.dcatcher.enderius.common.network.PacketToggle;
import net.dcatcher.enderius.common.tileentities.ContainerRepellent;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import scala.Int;
import scala.tools.nsc.Global;

import java.util.List;

/**
 * Copyright: DCatcher
 */
public class GuiRepellent extends GuiScreen {

    TileEntityRepellent tileEntity;

    List<String> whitelist;

    private GuiTextField xCoord, yCoord, zCoord;
    private GuiTextField username;
    private GuiButton whitelistadder, coordSender;



    private final int xSize = 256, ySize = 180;


    public GuiRepellent(TileEntityRepellent repel) {
        super();
        this.tileEntity = repel;
    }




    @Override
    public void drawDefaultBackground() {
        this.mc.renderEngine.bindTexture(new ResourceLocation("enderius", "textures/gui/repellent.png"));
        int x = (width - xSize) /2;
        int y = (height - ySize) /2;

        this.drawTexturedModalRect(x, y, 0,0, xSize, ySize);
    }

    @Override
    public void initGui() {
        super.initGui();

        int x = ((width -  xSize) / 2);
        int y = ((height - ySize) / 2);
        xCoord = new GuiTextField(fontRendererObj, x + 40, y + 40, 60, 15);
        yCoord = new GuiTextField(fontRendererObj, x + 40, y + 60, 60, 15);
        zCoord = new GuiTextField(fontRendererObj, x + 40, y + 80, 60, 15);
        username = new GuiTextField(fontRendererObj, x + 20, y + 140, 90, 15);

        whitelistadder = new GuiButton(1, x + 20, y + 160, 100, 20, "Toggle");
        coordSender = new GuiButton(2, x + 40, y + 100, 60, 15, "Save");

        int[] locs = tileEntity.getLocs();

        xCoord.setText("" + locs[0]);
        yCoord.setText("" + locs[1]);
        zCoord.setText("" + locs[2]);

        buttonList.add(whitelistadder);
        buttonList.add(coordSender);

        whitelist = tileEntity.getWhitelist();

        }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        int x = ((width -  xSize) / 2);
        int y = ((height - ySize) / 2);
        xCoord.drawTextBox();
        yCoord.drawTextBox();
        zCoord.drawTextBox();
        username.drawTextBox();


        this.fontRendererObj.drawString("X:", x + 30, y + 43, 0x1a1a1a);
        this.fontRendererObj.drawString("Y:", x + 30, y + 63, 0x1a1a1a);
        this.fontRendererObj.drawString("Z:", x + 30, y + 83, 0x1a1a1a);

        this.fontRendererObj.drawString("Add/Remove user ", x + 20, y + 120, 0x1a1a1a);
        this.fontRendererObj.drawString("from whitelist:", x + 20, y + 130, 0x1a1a1a);
        whitelistadder.drawButton(mc, i, j);
        coordSender.drawButton(mc, i, j);

        int startX = x + 145, startY = y + 25;

        for(String str : whitelist){
            this.fontRendererObj.drawString(str, startX, startY, 0xffffff);
            startY += 10;
        }
        whitelist = tileEntity.getWhitelist();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch(button.id){
            case 1:
                PacketToggle toggle = new PacketToggle(username.getText(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
                Enderius.packetPipeline.sendToServer(toggle);
                Enderius.packetPipeline.sendToAll(toggle);
                break;
            case 2:
                int locX = Integer.parseInt(xCoord.getText());
                int locY = Integer.parseInt(yCoord.getText());
                int locZ = Integer.parseInt(zCoord.getText());
                tileEntity.setLocationToTpTo(locX, locY, locZ);
                PacketRepellent repel = new PacketRepellent(locX, locY, locZ, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
                System.out.println("Sending PacketRepel");
                Enderius.packetPipeline.sendToServer(repel);
                Enderius.packetPipeline.sendToAll(repel);
                break;
        }
        PacketSync packet = new PacketSync(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        Enderius.packetPipeline.sendToAll(packet);
        Enderius.packetPipeline.sendToServer(packet);
    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        xCoord.mouseClicked(i, j, k);
        yCoord.mouseClicked(i, j, k);
        zCoord.mouseClicked(i, j, k);
        username.mouseClicked(i, j, k);
        whitelistadder.mousePressed(mc, i, j);
        coordSender.mousePressed(mc, i, j);
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

        if(username.isFocused()){
            username.textboxKeyTyped(par1, par2);
        }
    }

}
