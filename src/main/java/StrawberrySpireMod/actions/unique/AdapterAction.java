package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;

public class AdapterAction extends AbstractGameAction {

    public static final String[] TEXT = {
            "Discard",
            "Register"
    };
    private AbstractPlayer player;
    private int numberOfOrbs;
    private boolean upgraded;

    public AdapterAction(int magicNumber, boolean upgraded) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfOrbs = magicNumber;
        this.upgraded = upgraded;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.player.hand.isEmpty()) {
                for (int i = 0; i < this.numberOfOrbs; i++) {
                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
                }
                this.isDone = true;
                return;
            }
            else if (this.player.hand.size() == 1) {
                AbstractCard tempCard;
                tempCard = this.player.hand.getBottomCard();
                if (tempCard.type == AbstractCard.CardType.SKILL) {
                    for (int i = 0; i < this.numberOfOrbs; i++) {
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Frost()));
                    }
                }
                else {
                    for (int i = 0; i < this.numberOfOrbs; i++) {
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
                    }
                }
                if (!this.upgraded) {
                    this.player.hand.moveToDiscardPile(tempCard);
                    tempCard.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }
            else {
                if (this.upgraded) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[1], 1, false, false);
                }
                else {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                }
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.type == AbstractCard.CardType.SKILL) {
                    for (int i = 0; i < this.numberOfOrbs; i++) {
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Frost()));
                    }
                }
                else {
                    for (int i = 0; i < this.numberOfOrbs; i++) {
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
                    }
                }
                if (this.upgraded) {
                    this.player.hand.addToHand(c);
                }
                else {
                    this.player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}