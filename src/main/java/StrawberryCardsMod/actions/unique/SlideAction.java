package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import basemod.*;

public class SlideAction extends AbstractGameAction
{

    public static final String[] TEXT = {
            "Choose a Card to Put into Your Hand.",
            "Choose ",
            " Cards to Put into Your Hand."
    };
    private AbstractPlayer player;
    private int numberOfCards;

    public SlideAction(int magicNumber) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.player = AbstractDungeon.player;
        this.numberOfCards = magicNumber;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            if (this.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            else {
                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : this.player.drawPile.group) {
                    if (c.costForTurn == 0 || c.freeToPlayOnce) {
                        temp.addToTop(c);
                    }
                }
                temp.sortAlphabetically(true);
                temp.sortByRarityPlusStatusCardType(false);
                if (temp.size() == 0) {
                    this.isDone = true;
                    return;
                }
                else if (temp.size() <= numberOfCards) {
                    for (AbstractCard c : temp.group) {
                        if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                            this.player.drawPile.moveToDiscardPile(c);
                            this.player.createHandIsFullDialog();
                        }
                        else {
                            this.player.drawPile.moveToHand(c, this.player.drawPile);
                        }
                    }
                }
                else {
                    if (numberOfCards == 1) {
                        AbstractDungeon.gridSelectScreen.open(temp, numberOfCards, TEXT[0], false, false, false, false);
                    }
                    else {
                        AbstractDungeon.gridSelectScreen.open(temp, numberOfCards, TEXT[1] + numberOfCards + TEXT[2], false, false, false, false);
                    }
                    tickDuration();
                    return;
                }
            }
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                    this.player.drawPile.moveToDiscardPile(c);
                    this.player.createHandIsFullDialog();
                }
                else {
                    this.player.drawPile.moveToHand(c, this.player.drawPile);
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
