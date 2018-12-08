package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;

import StrawberrySpireMod.powers.PackagePower;

import java.util.*;

public class PackageAction extends AbstractGameAction {

    public static final String[] TEXT = {
            "Choose a Card to Remove.",
            "Choose ",
            " Cards to Remove."
    };
    private AbstractPlayer player;
    private int numberOfCards;

    public PackageAction(int magicNumber) {
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
            else {
                if (numberOfCards == 1) {
                    AbstractDungeon.gridSelectScreen.open(this.player.drawPile, numberOfCards, true, TEXT[0]);
                }
                else {
                    AbstractDungeon.gridSelectScreen.open(this.player.drawPile, numberOfCards, true, TEXT[1] + numberOfCards + TEXT[2]);
                }
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            ArrayList<AbstractCard> cardList = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                cardList.add(c);
                this.player.drawPile.removeCard(c);
            }
            if (!cardList.isEmpty()) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.player, this.player, new PackagePower(this.player, cardList)));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
