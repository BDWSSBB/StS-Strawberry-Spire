package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.actions.unique.*;
import StrawberrySpireMod.powers.*;

public class ByrdBeak extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:ByrdBeak";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireModResources/relics/outline/placeholder.png");
    private static final int ATTACK_AMOUNT = 7;
    private static final int VULNERABLE_AMOUNT = 1;

    public ByrdBeak() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.COMMON, LandingSound.HEAVY);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + ATTACK_AMOUNT + DESCRIPTIONS[1] + VULNERABLE_AMOUNT + DESCRIPTIONS[2];
    }

    public void atBattleStart() {
        if (this.counter == ATTACK_AMOUNT - 1) {
            this.pulse = true;
            beginPulse();
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ByrdBeakPower(AbstractDungeon.player)));
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.counter += 1;
            if (this.counter == ATTACK_AMOUNT) {
                this.counter = 0;
                flash();
                this.pulse = false;
            }
            else if (this.counter == ATTACK_AMOUNT - 1) {
                this.pulse = true;
                beginPulse();
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ByrdBeakPower(AbstractDungeon.player)), 1));
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new ByrdBeak();
    }
}
