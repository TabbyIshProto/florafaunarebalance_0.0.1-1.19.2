package net.tabby.florafaunarebalance.util.FFR;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.tabby.florafaunarebalance.item.FFRii;

public class FFRItemProperties {
    public static void addCustomItemProperties() {
        makeBow(FFRii.DART_CHUTE.get());
        makeSling(FFRii.LEATHER_SLING.get());
    }
    private static void makeBow(Item Items) { //# TODO: add diff custom values here.
        ItemProperties.register(Items, new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
            if (p_174637_ == null) {
                return 0.0F; //# did not understand these are lambda parameters and not some secret code pointing to a specific value in memory...
            } else {
                return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float)(p_174635_.getUseDuration() -
                        p_174637_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(Items, new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
        });
    }
    private static void makeSling(Item item) {
        ItemProperties.register(item, new ResourceLocation("has_pebble"), (itm, level, entity, y) -> itm.getOrCreateTag().getBoolean("Set") ? 1.0f : 0.0f);
    }
}
