package StrawberrySpireMod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.ui.campfire.*;

import StrawberrySpireMod.relics.blandfruitRelics.*;
import StrawberrySpireMod.ui.campfire.*;

import java.util.*;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.rooms.CampfireUI",
        method = "initializeButtons"
)
public class InitializeButtonsPatches {
    public static void Postfix(Object __instance) { // This is not mine and I think this patch sucks too.
        CampfireUI campfire = (CampfireUI)__instance;
        try {
            @SuppressWarnings("unchecked")
            ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>) ReflectionHacks.getPrivate(campfire, CampfireUI.class, "buttons");
            if(AbstractDungeon.player.hasRelic(Blandfruit.ID)) {
                campfireButtons.add(new InfuseBlandfruitOption());
                campfireButtons.get(campfireButtons.size() - 1).setPosition(1260 * Settings.scale, 180 * Settings.scale);
            }

        } catch (SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
