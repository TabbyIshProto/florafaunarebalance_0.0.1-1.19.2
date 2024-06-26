package net.tabby.florafaunarebalance.item.unique.enchantment.unique;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BarrageEnchantment extends Enchantment {
    public BarrageEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }


    @Override
    public boolean allowedInCreativeTab(Item book, CreativeModeTab tab) {
        return super.allowedInCreativeTab(book, tab);
    }
}
