package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class PumpUpAction extends AbstractGameAction {

    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean upgraded;

    public PumpUpAction(int magicNumber, boolean upgraded) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = magicNumber;
        this.upgraded = upgraded;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            else if (this.player.hand.size() <= this.numberOfCards && !this.upgraded) {
                int totalStrength = 0;
                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (int i = 0; i < this.player.hand.size(); i++) {
                    temp.addToTop(AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.size() - i - 1));
                }
                for (AbstractCard c : temp.group) {
                    if (c.costForTurn == -1) {
                        totalStrength += EnergyPanel.getCurrentEnergy();
                    }
                    else if (c.costForTurn > 0) {
                        totalStrength += c.costForTurn;
                    }
                    AbstractDungeon.player.hand.moveToExhaustPile(c);
                }
                if (totalStrength > 0) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new StrengthPower(this.player, totalStrength), totalStrength));
                }
            }
            else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.numberOfCards, this.upgraded, this.upgraded);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.selectedCards.isEmpty()) {
            int totalStrength = 0;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.costForTurn == -1) {
                    totalStrength += EnergyPanel.getCurrentEnergy();
                }
                else if (c.costForTurn > 0) {
                    totalStrength += c.costForTurn;
                }
                AbstractDungeon.player.hand.moveToExhaustPile(c);
            }
            if (totalStrength > 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new StrengthPower(this.player, totalStrength), totalStrength));
            }
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
