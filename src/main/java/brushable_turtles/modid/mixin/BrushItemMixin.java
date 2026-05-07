package brushable_turtles.modid.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BrushItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(Item.class)
public abstract class BrushItemMixin {

	@Inject(at = @At("HEAD"), method = "useOnEntity", cancellable = true)
	private void onUseOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		if (!(stack.getItem() instanceof BrushItem) || !(entity instanceof TurtleEntity)) {
			return;
		}

		World world = ((EntityAccessor) entity).getWorld();
		if (!world.isClient()) {
			ServerWorld serverWorld = (ServerWorld) world;
			serverWorld.playSound(null, entity.getBlockPos(), SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
			entity.dropStack(serverWorld, new ItemStack(Items.TURTLE_SCUTE));
			stack.damage(8, player, EquipmentSlot.MAINHAND);
		}

		player.setCurrentHand(hand);
		cir.setReturnValue(ActionResult.SUCCESS);
	}
}
