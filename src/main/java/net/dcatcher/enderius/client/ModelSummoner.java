package net.dcatcher.enderius.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class ModelSummoner extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Pillar;
    ModelRenderer rungA;
    ModelRenderer rungB;
    ModelRenderer rungC;
    ModelRenderer rungD;
  
  public ModelSummoner()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 7);
      Base.addBox(0F, 0F, 0F, 14, 2, 14);
      Base.setRotationPoint(-7F, 22F, -7F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Pillar = new ModelRenderer(this, 44, 1);
      Pillar.addBox(0F, 0F, 0F, 4, 14, 4);
      Pillar.setRotationPoint(-2F, 8F, -2F);
      Pillar.setTextureSize(64, 32);
      Pillar.mirror = true;
      setRotation(Pillar, 0F, 0F, 0F);
      rungA = new ModelRenderer(this, 0, 0);
      rungA.addBox(0F, 0F, 0F, 6, 1, 6);
      rungA.setRotationPoint(-3F, 19F, -3F);
      rungA.setTextureSize(64, 32);
      rungA.mirror = true;
      setRotation(rungA, 0F, 0F, 0F);
      rungB = new ModelRenderer(this, 0, 0);
      rungB.addBox(0F, 0F, 0F, 6, 1, 6);
      rungB.setRotationPoint(-3F, 16F, -3F);
      rungB.setTextureSize(64, 32);
      rungB.mirror = true;
      setRotation(rungB, 0F, 0F, 0F);
      rungC = new ModelRenderer(this, 0, 0);
      rungC.addBox(0F, 0F, 0F, 6, 1, 6);
      rungC.setRotationPoint(-3F, 13F, -3F);
      rungC.setTextureSize(64, 32);
      rungC.mirror = true;
      setRotation(rungC, 0F, 0F, 0F);
      rungD = new ModelRenderer(this, 0, 0);
      rungD.addBox(0F, 0F, 0F, 6, 1, 6);
      rungD.setRotationPoint(-3F, 10F, -3F);
      rungD.setTextureSize(64, 32);
      rungD.mirror = true;
      setRotation(rungD, 0F, 0F, 0F);
  }
  
  public void render(TileEntity entity, float scale)
  {
    Base.render(scale);
    Pillar.render(scale);
    rungA.render(scale);
    rungB.render(scale);
    rungC.render(scale);
    rungD.render(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

    public void renderItem(float scale){
        Base.render(scale);
        Pillar.render(scale);
        rungA.render(scale);
        rungB.render(scale);
        rungC.render(scale);
        rungD.render(scale);
    }
  
  public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
