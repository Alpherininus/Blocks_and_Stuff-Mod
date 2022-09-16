package com.alpherininus.basmod.client.handlers;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodAnivilHandler {

    static class CombineRecipe {
        public final Item left;
        public final Item right;
        public final Item out;
        protected CombineRecipe(Item left, Item right, Item out){
            this.left = left;
            this.right = right;
            this.out = out;
        }
    }

    private static ArrayList<CombineRecipe> combineRecipes = new ArrayList<>();

    public static void initAnvilRecipes() {
        // combineRecipes.add(new CombineRecipe(Items.BEEF, Items.COAL, Items.COOKED_BEEF));
        combineRecipes.add(new CombineRecipe(ItemInit.STEEL_ITEM.get(), ItemInit.MANA_BUCKET.get(), ItemInit.DARK_STEEL_ITEM.get()));

        combineRecipes.add(new CombineRecipe(ItemInit.STEEL_ITEM.get(), ItemInit.MANA_BUCKET.get(), ItemInit.DARK_STEEL_ITEM.get()));


    }

    @SubscribeEvent
    public static void handleRepair(AnvilUpdateEvent event){
        combineRecipes.forEach((data) -> {
            if (event.getLeft().getItem() == data.left && event.getRight().getItem() == data.right){
                event.setOutput(new ItemStack(data.out, event.getLeft().getCount()));
                event.setCost(event.getLeft().getCount());
                event.setMaterialCost(event.getLeft().getCount());
            }
        });
    }


}
