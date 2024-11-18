package de.miraculixx.alwayssnow.mixin;

import de.miraculixx.alwayssnow.AlwaysSnow;
import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WeatherEffectRenderer.class)
public class MixinWeatherEffectRenderer {


    @Inject(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBiome(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;"),
        method = "getPrecipitationAt",
        cancellable = true
    )
    public void getPrecipitationAt(Level level, BlockPos blockPos, CallbackInfoReturnable<Biome.Precipitation> cir) {
        if (AlwaysSnow.Companion.getConfig().alwaysSnow) {
            cir.setReturnValue(Biome.Precipitation.SNOW);
        }
    }
}
