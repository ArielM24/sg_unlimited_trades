package com.sg.unlimited_trades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.village.TradeOffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SGUnlimitedTrades implements ModInitializer {
	public static final String MOD_ID = "sg-unlimited-trades";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		UseEntityCallback.EVENT.register((player, world, hand, entity, result)->{
			if(world.isClient){
				return ActionResult.PASS;
			}
			if(!(entity instanceof VillagerEntity)){
				return ActionResult.PASS;				
			}
			VillagerEntity villager = (VillagerEntity)entity;
			for(TradeOffer offer: villager.getOffers()){
				offer.resetUses();
				offer.updateDemandBonus();
			}
			
			return ActionResult.PASS;
		});
	}
}