package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;

import basemod.BaseMod;

public class AggressionAction extends AbstractGameAction {

    private AbstractPlayer player;
    private int maxAmountOfAttacks;
    private int failsafeCounter;

    public AggressionAction(int amount, int failsafeCounter)
    {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.player = AbstractDungeon.player;
        this.maxAmountOfAttacks = amount;
        this.failsafeCounter = failsafeCounter;
    }

    public void update() {
        int amountOfAttacksInHand = 0;
        for (AbstractCard c : this.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                amountOfAttacksInHand++;
            }
        }
        if (amountOfAttacksInHand < this.maxAmountOfAttacks && this.player.hand.size() != BaseMod.MAX_HAND_SIZE && !this.player.hasPower(NoDrawPower.POWER_ID) && failsafeCounter < 30) {
            failsafeCounter++;
            AbstractDungeon.actionManager.addToTop(new AggressionAction(this.maxAmountOfAttacks, this.failsafeCounter));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.player,1));
        }
        this.isDone = true;
    }
}
