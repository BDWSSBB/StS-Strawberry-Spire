package StrawberrySpireMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.random.Random;

import StrawberrySpireMod.events.theCity.*;

import javassist.*;
import java.util.*;

@SpirePatch(
        clz=AbstractDungeon.class,
        method="getEvent"
)
public class GetEventPatches
{
    @SpireInsertPatch(
            locator=Locator.class,
            localvars={"tmp"}
    )
    public static void Insert(Random rng, ArrayList<String> tmp) {
        if (!TheByrdhouse.canSpawn()) {
            tmp.remove(TheByrdhouse.ID);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "isEmpty");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
