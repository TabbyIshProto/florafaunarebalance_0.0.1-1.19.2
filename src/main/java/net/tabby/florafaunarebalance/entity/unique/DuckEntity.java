package net.tabby.florafaunarebalance.entity.unique;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.entity.unique.goals.BuoyancyGoal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DuckEntity extends Animal implements FlyingAnimal {
    public boolean waterBelow;
    public DuckEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
    }

    //# TODO: float but not bob goal / fix.
    //# TODO /simplify add goals using list/array...
    @Override
    protected void registerGoals() { // TODO: boids, flight
        GoalSelector ai = this.goalSelector;
        ai.addGoal(0, new BuoyancyGoal(this));

        // TODO: mix between panic and attack goal...
        ai.addGoal(10, new BreedGoal(this, 0.9)); // eggs, nests. //auto breed, auto die by age.
        //# replace with peas.
        ai.addGoal(30, new TemptGoal(this, 1.75, Ingredient.of(Items.BREAD), false));
        ai.addGoal(20, new FollowParentGoal(this, 1.2));
        ai.addGoal(40, new RandomSwimmingGoal(this, 2.4, 70)); //mob, speed, interval.
        ai.addGoal(50, new RandomStrollGoal(this, 0.2)); //TODO: fix: ducks speedy on land compared to water...

        ai.addGoal(60, new LookAtPlayerGoal(this, Player.class, 4.7f));
        ai.addGoal(70, new RandomLookAroundGoal(this));

    }
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)

                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FLYING_SPEED, 1.15f)
                .add(Attributes.ATTACK_SPEED, 1.5)

                .add(Attributes.FOLLOW_RANGE, 300)
                .add(ForgeMod.SWIM_SPEED.get(), 2.4);
    }
    @Override
    public void sinkInFluid(FluidType type) {
        self().setDeltaMovement(self().getDeltaMovement().add(0.0D, -0.08F, 0.0D));
    }

    @Override
    public int getMaxAirSupply() {
        return 650;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
        return FFRenty.DUCK.get().create(level);
    }

    @Override
    public boolean isFood(@NotNull ItemStack item) {
        return item.is(Items.BREAD);
    }
    public boolean isFlying() {
        return false;
    }
}
