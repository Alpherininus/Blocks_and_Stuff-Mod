package com.alpherininus.basmod.common.particles;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.data.IDataProvider;
import net.minecraft.particles.BasicParticleType;

public class ManaParticle extends TexturedParticle {


    public ManaParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);

        this.maxAge = 20;
    }

    @Override
    protected float getMinU() {
        return 0;
    }

    @Override
    protected float getMaxU() {
        return 0;
    }

    @Override
    protected float getMinV() {
        return 0;
    }

    @Override
    protected float getMaxV() {
        return 0;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.particleAlpha = (-(1 / (float) maxAge) * age + 1);
    }


    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

}
