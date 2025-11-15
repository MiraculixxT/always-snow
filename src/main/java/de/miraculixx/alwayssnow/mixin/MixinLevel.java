package de.miraculixx.alwayssnow.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.miraculixx.alwayssnow.AlwaysSnow;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class MixinLevel {

    @Shadow protected float oRainLevel;

    @Shadow protected float rainLevel;

    @Inject(at = @At("RETURN"), method = "getRainLevel", cancellable = true)
    public void getRainLevel(float delta, CallbackInfoReturnable<Float> cir) {
        if (AlwaysSnow.Companion.getConfig().weatherChange) {
            cir.setReturnValue(1.0F);
        } else {
            cir.setReturnValue(Mth.lerp(delta, oRainLevel, rainLevel));
        }
    }

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/biome/Biome;getPrecipitationAt(Lnet/minecraft/core/BlockPos;I)Lnet/minecraft/world/level/biome/Biome$Precipitation;",
            shift = At.Shift.BEFORE
        ),
        method = "isRainingAt",
        cancellable = true
    )
    public void isRainingAt(BlockPos blockPos, CallbackInfoReturnable<Boolean> cir, @Local Biome biome) {
        if (AlwaysSnow.Companion.getConfig().alwaysSnow) {
            Level level = (Level) (Object) this;
            cir.setReturnValue(biome.getPrecipitationAt(blockPos, level.getSeaLevel()) != Biome.Precipitation.NONE);
        }
    }
}
