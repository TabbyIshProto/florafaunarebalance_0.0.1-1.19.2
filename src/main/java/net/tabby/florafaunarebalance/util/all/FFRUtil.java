package net.tabby.florafaunarebalance.util.all;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;
import java.util.function.Predicate;

public class FFRUtil {
    public static ItemStack getAmmo(Player player, Predicate<ItemStack> predicate) {
        for (int idx = 0; idx < player.getInventory().getContainerSize(); ++idx) {
            ItemStack ammo = player.getInventory().getItem(idx);
            if (predicate.test(ammo)) {
                return ammo;
            }
        } //# loops through inv to find item.
        return ItemStack.EMPTY;
    }

    public static String getRgStr(Item item) { //# get string from Item...
        return Nn(ForgeRegistries.ITEMS.getKey(item));
    }
    public static String getRgStr(BlockState state) { //# get string from blockState...
        return Nn(ForgeRegistries.BLOCKS.getKey(state.getBlock()));
    }


    public static String Nn(ResourceLocation loc) { //# objects require non null duplication removal.
        return Objects.requireNonNull(loc).getPath();
    }
}
