package StrawberrySpireMod.patches;

import StrawberrySpireMod.relics.blandfruitRelics.Swiftfruit;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractCard.class,
        method = "applyPowersToBlock"
)
public class SwiftfruitPatch {

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"tmp"}
    )
    public static void Insert(AbstractCard __instance, @ByRef float[] tmp) { // Too lazy.
        if (AbstractDungeon.player.hasRelic(Swiftfruit.ID) && ((Swiftfruit) AbstractDungeon.player.getRelic(Swiftfruit.ID)).isActive) {
            tmp[0] *= 2.0F;
            __instance.isBlockModified = true;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "isBlockModified");
            int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            found[found.length - 1] += 2;
            return new int[]{found[found.length-1]};
        }
    }
}
