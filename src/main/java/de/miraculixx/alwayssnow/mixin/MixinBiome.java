package de.miraculixx.alwayssnow.mixin;

import de.miraculixx.alwayssnow.AlwaysSnow;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class MixinBiome {

    @Shadow public abstract boolean hasPrecipitation();

    @Shadow public abstract boolean coldEnoughToSnow(BlockPos blockPos, int i);

    @Inject(at = @At("HEAD"), method = "getPrecipitationAt", cancellable = true)
    public void getPrecipitationAt(BlockPos blockPos, int i, CallbackInfoReturnable<Biome.Precipitation> cir) {
        if (!hasPrecipitation()) {
            cir.setReturnValue(Biome.Precipitation.NONE);
        } else {
            if (AlwaysSnow.Companion.getConfig().alwaysSnow) {
                cir.setReturnValue(Biome.Precipitation.SNOW);
            } else {
                cir.setReturnValue(coldEnoughToSnow(blockPos, i) ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN);
            }
        }
    }
}
