package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

public class DataDumpAction extends AbstractGameAction {

    public static final String[] TEXT = {
            "Choose a Card to Put into Your Discard Pile.",
            "Choose ",
            " Cards to Put into Your Discard Pile."
    };
    private AbstractPlayer player;
    private int numberOfCards;

    public DataDumpAction(int magicNumber) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.player = AbstractDungeon.player;
        this.numberOfCards = magicNumber;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
            return;
        }
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            if (this.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            else if (this.player.drawPile.size() <= this.numberOfCards) {
                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c: this.player.drawPile.group) {
                    temp.addToTop(c);
                }
                for (AbstractCard c : temp.group) {
                    AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                }
            }
            else {
                if (numberOfCards == 1) {
                    AbstractDungeon.gridSelectScreen.open(this.player.drawPile, numberOfCards, false, TEXT[0]);
                }
                else {
                    AbstractDungeon.gridSelectScreen.open(this.player.drawPile, numberOfCards, false, TEXT[1] + numberOfCards + TEXT[2]);
                }
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                this.player.drawPile.moveToDiscardPile(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
