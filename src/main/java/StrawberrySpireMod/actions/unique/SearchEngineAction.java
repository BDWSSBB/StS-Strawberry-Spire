package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import basemod.BaseMod;

public class SearchEngineAction extends AbstractGameAction {

    public static final String[] TEXT = {
            "Search"
    };
    private AbstractPlayer player;
    private int numberOfCards;

    public SearchEngineAction(int magicNumber) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = magicNumber;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            else if (this.player.hand.size() == 1) {
                AbstractCard cardInHand = this.player.hand.getBottomCard();
                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : this.player.drawPile.group) {
                    if (c.costForTurn == cardInHand.costForTurn || ((cardInHand.costForTurn == 0 || cardInHand.freeToPlayOnce) && (c.costForTurn == 0 || c.freeToPlayOnce))) {
                        temp.addToTop(c);
                    }
                }
                if (temp.size() == 0) {
                    this.isDone = true;
                    return;
                }
                else {
                    for (int i = 0; i < this.numberOfCards; i++) {
                        if (!temp.isEmpty()) {
                            temp.shuffle();
                            AbstractCard card = temp.getBottomCard();
                            temp.removeCard(card);
                            if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                                this.player.drawPile.moveToDiscardPile(card);
                                this.player.createHandIsFullDialog();
                            }
                            else {
                                this.player.drawPile.moveToHand(card, this.player.drawPile);
                            }
                        }
                    }
                }
            }
            else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.player.hand.addToHand(c);
                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard ca : this.player.drawPile.group) {
                    if (ca.costForTurn == c.costForTurn || ((c.costForTurn == 0 || c.freeToPlayOnce) && (ca.costForTurn == 0 || ca.freeToPlayOnce))) {
                        temp.addToTop(ca);
                    }
                }
                if (temp.size() == 0) {
                    this.isDone = true;
                    return;
                }
                else {
                    for (int i = 0; i < this.numberOfCards; i++) {
                        if (!temp.isEmpty()) {
                            temp.shuffle();
                            AbstractCard card = temp.getBottomCard();
                            temp.removeCard(card);
                            if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                                this.player.drawPile.moveToDiscardPile(card);
                                this.player.createHandIsFullDialog();
                            }
                            else {
                                this.player.drawPile.moveToHand(card, this.player.drawPile);
                            }
                        }
                    }
                }
            }
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}