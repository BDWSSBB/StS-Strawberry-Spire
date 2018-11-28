package StrawberryCardsMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;

import StrawberryCardsMod.cards.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.actions.GameActionManager", method = "callEndOfTurnActions")
public class CallEndOfTurnActionPatches {

    public static void Postfix(GameActionManager __instance) {
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractStrawberryCardsCard) {
                ((AbstractStrawberryCardsCard)c).triggerAtEndOfTurnFromAnywhere();
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractStrawberryCardsCard) {
                ((AbstractStrawberryCardsCard)c).triggerAtEndOfTurnFromAnywhere();
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof AbstractStrawberryCardsCard) {
                ((AbstractStrawberryCardsCard)c).triggerAtEndOfTurnFromAnywhere();
            }
        }
    }
}