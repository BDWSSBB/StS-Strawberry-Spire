package StrawberrySpireMod.actions.unique;

import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.ui.panels.*;

import StrawberrySpireMod.relics.*;

public class JuiceKegAction extends AbstractGameAction {

    public JuiceKegAction() {

    }

    public void update() {
        CardGroup potentialCardsToPlay = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        int highestCost = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK && (c.costForTurn > highestCost || (c.costForTurn == -1 && EnergyPanel.totalCount > highestCost))) {
                if (c.costForTurn == -1) {
                    highestCost = EnergyPanel.totalCount;
                }
                else {
                    highestCost = c.costForTurn;
                }
                potentialCardsToPlay.clear();
                potentialCardsToPlay.addToRandomSpot(c);
            }
            else if (c.type == AbstractCard.CardType.ATTACK && (c.costForTurn == highestCost || (c.costForTurn == -1 && EnergyPanel.totalCount == highestCost))) {
                potentialCardsToPlay.addToRandomSpot(c);
            }
        }
        potentialCardsToPlay.shuffle();
        if (!potentialCardsToPlay.isEmpty()) {
            AbstractCard card = potentialCardsToPlay.getBottomCard();
            AbstractDungeon.getCurrRoom().souls.remove(card);
            card.freeToPlayOnce = true;
            AbstractDungeon.player.limbo.group.add(card);
            card.targetAngle = MathUtils.random(0.0F, 360.0F);
            AbstractMonster target = AbstractDungeon.getRandomMonster();
            card.applyPowers();
            AbstractDungeon.actionManager.addToTop(new QueueCardAction(card, target));
            AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
                AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            }
            else {
                AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
            if (AbstractDungeon.player.hasRelic(JuiceKeg.ID)) {
                AbstractDungeon.player.getRelic(JuiceKeg.ID).flash();
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, AbstractDungeon.player.getRelic(JuiceKeg.ID)));
            }
        }
        this.isDone = true;
    }
}
