package StrawberrySpireMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import StrawberrySpireMod.relics.blandfruitRelics.*;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class IncreaseMaxHpPatches {

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "increaseMaxHp"
    )
    public static class changeMaxHPChangeAmount {

        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractCreature __instance, final int amount, final boolean showEffect) {
            __instance.maxHealth += amount / 2;
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCreature.class, "maxHealth");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "increaseMaxHp"
    )
    public static class changeHealAmount {

        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("heal")) {
                        m.replace(
                                "{" +
                                        "if (" + Nested.class.getName() + ".hasStarfruit()) {" +
                                        "$_ = $proceed($1 + " + Nested.class.getName() + ".increaseAmount($1), $2);" +
                                        "}" +
                                        "else" +
                                        "{" +
                                        "$_ = $proceed($$);" +
                                        "}" +
                                        "}");
                    }
                }
            };
        }

        public static class Nested {

            public static boolean hasStarfruit() {
                if (AbstractDungeon.player.hasRelic(Starfruit.ID)) {
                    return true;
                }
                else {
                    return false;
                }
            }

            public static int increaseAmount(int originalAmount) {
                return originalAmount / 2;
            }
        }
    }
}
