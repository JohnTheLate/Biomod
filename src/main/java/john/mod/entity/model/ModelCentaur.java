package john.mod.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelCentaur - john
 * Created using Tabula 6.0.0
 */
public class ModelCentaur extends ModelBase {
    public ModelRenderer CowBody;
    public ModelRenderer CowUdders;
    public ModelRenderer RearLeftLeg;
    public ModelRenderer FrontLeftLeg;
    public ModelRenderer RearRightLeg;
    public ModelRenderer FrontRightLeg;
    public ModelRenderer VilHead;
    public ModelRenderer Chest;
    public ModelRenderer ArmR;
    public ModelRenderer ArmM;
    public ModelRenderer ArmL;
    public ModelRenderer Coat;
    public ModelRenderer VilNose;

    public ModelCentaur() {
        this.textureWidth = 64;
        this.textureHeight = 96;
        this.Chest = new ModelRenderer(this, 0, 64);
        this.Chest.setRotationPoint(0.0F, -12.0F, -5.0F);
        this.Chest.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.CowUdders = new ModelRenderer(this, 52, 0);
        this.CowUdders.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.CowUdders.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(CowUdders, 1.5707963267948966F, 0.0F, 0.0F);
        this.Coat = new ModelRenderer(this, 0, 40);
        this.Coat.setRotationPoint(0.0F, -12.0F, -5.0F);
        this.Coat.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.RearRightLeg = new ModelRenderer(this, 0, 16);
        this.RearRightLeg.setRotationPoint(-4.0F, 12.0F, 7.0F);
        this.RearRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.VilHead = new ModelRenderer(this, 28, 46);
        this.VilHead.setRotationPoint(0.0F, -12.0F, -5.0F);
        this.VilHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.FrontLeftLeg = new ModelRenderer(this, 0, 16);
        this.FrontLeftLeg.setRotationPoint(4.0F, 12.0F, -6.0F);
        this.FrontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RearLeftLeg = new ModelRenderer(this, 0, 16);
        this.RearLeftLeg.setRotationPoint(4.0F, 12.0F, 7.0F);
        this.RearLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.FrontRightLeg = new ModelRenderer(this, 0, 16);
        this.FrontRightLeg.setRotationPoint(-4.0F, 12.0F, -6.0F);
        this.FrontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.ArmL = new ModelRenderer(this, 28, 64);
        this.ArmL.setRotationPoint(0.0F, -9.0F, -6.0F);
        this.ArmL.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(ArmL, -0.7499679795819634F, 0.0F, 0.0F);
        this.VilNose = new ModelRenderer(this, 52, 8);
        this.VilNose.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.VilNose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.ArmM = new ModelRenderer(this, 0, 82);
        this.ArmM.setRotationPoint(0.0F, -9.0F, -6.0F);
        this.ArmM.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(ArmM, -0.7499679795819634F, 0.0F, 0.0F);
        this.CowBody = new ModelRenderer(this, 18, 4);
        this.CowBody.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.CowBody.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.setRotateAngle(CowBody, 1.5707963267948966F, 0.0F, 0.0F);
        this.ArmR = new ModelRenderer(this, 28, 64);
        this.ArmR.setRotationPoint(0.0F, -9.0F, -6.0F);
        this.ArmR.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(ArmR, -0.7499679795819634F, 0.0F, 0.0F);
        this.VilHead.addChild(this.VilNose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Chest.render(f5);
        this.CowUdders.render(f5);
        this.Coat.render(f5);
        this.RearRightLeg.render(f5);
        this.VilHead.render(f5);
        this.FrontLeftLeg.render(f5);
        this.RearLeftLeg.render(f5);
        this.FrontRightLeg.render(f5);
        this.ArmL.render(f5);
        this.ArmM.render(f5);
        this.CowBody.render(f5);
        this.ArmR.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.FrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RearLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.FrontRightLeg.rotateAngleX = MathHelper.cos((limbSwing * 0.6662F) + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.RearRightLeg.rotateAngleX = MathHelper.cos((limbSwing * 0.6662F) + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.VilHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.VilHead.rotateAngleX = headPitch * 0.017453292F;
    }
}
