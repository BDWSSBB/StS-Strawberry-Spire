package StrawberrySpireMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;

import StrawberrySpireMod.relics.*;

import java.util.ArrayList;

@SpirePatch(
        clz= AbstractDungeon.class,
        method="getColorlessRewardCards"
)
public class GetColorlessRewardCardsPatches {

    public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result) {
        if (AbstractDungeon.player.hasRelic(ShipInABottle.ID)) {
            __result.get(AbstractDungeon.cardRng.random(0, __result.size() - 1)).upgrade();
        }
        return __result;
    }
}
