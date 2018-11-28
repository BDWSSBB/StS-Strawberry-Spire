package StrawberryCardsMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.cards.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.characters.AbstractPlayer", method = "useCard")
public class UseCardPatches {

    public static void Postfix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
        if (c instanceof AbstractStrawberryCardsCard) {
            ((AbstractStrawberryCardsCard)c).triggerAfterCardPlayed();
        }
    }
}
