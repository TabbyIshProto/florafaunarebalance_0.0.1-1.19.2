package net.tabby.florafaunarebalance.core.mixins;

import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.world.generation.WorldExtender;
import net.tabby.florafaunarebalance.world.generation.ore.OrePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    @Unique
    WorldExtender eX = new WorldExtender();

    @Inject(method = "applyBiomeDecoration", at = @At("TAIL"))
    private void ffr$applyBiomeDecorations(WorldGenLevel level, ChunkAccess chunk, StructureManager struct, CallbackInfo ci) {
        eX.bedrockPlus8();


        OrePlacer orePlacer = new OrePlacer(); //# ore stuff
        orePlacer.replaceOres(chunk, level);
        orePlacer.placeVeins(chunk, level);
    }
}