package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.colorless.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

public class RummageAction extends AbstractGameAction {

    public static final String[] TEXT = {
            "Discard",
    };
    private AbstractPlayer player;
    private int cardsToDiscard;

    public RummageAction(int magicNumber) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.cardsToDiscard = magicNumber;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }
            else if (this.player.hand.size() <= this.cardsToDiscard) {
                this.cardsToDiscard = this.player.hand.size();
                int numberOfAttacksDiscarded = 0;
                int tmp = this.player.hand.size();
                for (int i = 0; i < tmp; i++)
                {
                    AbstractCard c = this.player.hand.getTopCard();
                    if (c.type == AbstractCard.CardType.ATTACK) {
                        numberOfAttacksDiscarded++;
                    }
                    this.player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
                if (numberOfAttacksDiscarded > 0) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Shiv(), numberOfAttacksDiscarded));
                }
                AbstractDungeon.player.hand.applyPowers();
                tickDuration();
                return;
            }
            else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.cardsToDiscard, false);
                AbstractDungeon.player.hand.applyPowers();
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            int numberOfAttacksDiscarded = 0;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
            {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    numberOfAttacksDiscarded++;
                }
                this.player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            if (numberOfAttacksDiscarded > 0) {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Shiv(), numberOfAttacksDiscarded));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
