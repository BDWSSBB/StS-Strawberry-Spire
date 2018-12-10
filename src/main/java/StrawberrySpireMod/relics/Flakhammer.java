package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.actions.unique.*;

import basemod.abstracts.*;

public class Flakhammer extends CustomRelic {

    public static final String ID = "strawberrySpire:Flakhammer";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int MINIMUM_COST_AMOUNT = 2;
    private static final int VULNERABLE_AMOUNT = 1;
    private boolean hasActivated = false;

    public Flakhammer() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.RARE, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + MINIMUM_COST_AMOUNT + DESCRIPTIONS[1] + VULNERABLE_AMOUNT + DESCRIPTIONS[2];
    }

    public void atTurnStart() {
        this.hasActivated = false;
        this.pulse = true;
        beginPulse();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && (card.costForTurn >= MINIMUM_COST_AMOUNT || (card.costForTurn == -1 && card.energyOnUse >= MINIMUM_COST_AMOUNT)) && !this.hasActivated) {
            this.hasActivated = true;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, VULNERABLE_AMOUNT, false), VULNERABLE_AMOUNT, true), 1));
            }
            this.pulse = false;
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new Flakhammer();
    }
}
