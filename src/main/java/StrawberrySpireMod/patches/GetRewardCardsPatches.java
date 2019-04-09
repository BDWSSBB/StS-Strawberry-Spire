package StrawberrySpireMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;

import StrawberrySpireMod.relics.*;

import java.util.ArrayList;

@SpirePatch(
        clz= AbstractDungeon.class,
        method="getRewardCards"
)
public class GetRewardCardsPatches {

    public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result) {
        if (AbstractDungeon.player.hasRelic(ShipInABottle.ID)) {
            ArrayList<AbstractCard> upgradeableCards = new ArrayList<>();
            for (AbstractCard c : __result) {
                if (c.canUpgrade()) {
                    upgradeableCards.add(c);
                }
            }
            if (!upgradeableCards.isEmpty()) {
                upgradeableCards.get(AbstractDungeon.cardRng.random(0, upgradeableCards.size() - 1)).upgrade();
            }
        }
        return __result;
    }
}
