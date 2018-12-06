package StrawberrySpireMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import StrawberrySpireMod.relics.blandfruitRelics.*;
import com.megacrit.cardcrawl.vfx.*;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.core.AbstractCreature",
        method = "increaseMaxHp"
)
public class IncreaseMaxHpPatches { // There's definitely a better way to do this.

    public static void Prefix(AbstractCreature __instance, final int amount, final boolean showEffect) {
        if (AbstractDungeon.player.hasRelic(Starfruit.ID) && !AbstractDungeon.player.hasBlight("FullBelly")) {
            __instance.maxHealth += amount * 0.5;
            AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(__instance.hb.cX - __instance.animX, __instance.hb.cY, AbstractCreature.TEXT[2] + Integer.toString(amount / 2), Settings.GREEN_TEXT_COLOR));
            __instance.heal(amount / 2, true);
            __instance.healthBarUpdatedEvent();
        }
    }
}
