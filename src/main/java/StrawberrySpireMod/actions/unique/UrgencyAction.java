package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;

public class UrgencyAction extends AbstractGameAction {

    private AbstractMonster target;
    private AbstractPlayer player;
    private int amountOfCards;

    public UrgencyAction(AbstractMonster m, int amountOfCards) {
        this.target = m;
        this.player = AbstractDungeon.player;
        this.amountOfCards = amountOfCards;
    }

    public void update() {
        if (this.target.intent == AbstractMonster.Intent.ATTACK || this.target.intent == AbstractMonster.Intent.ATTACK_BUFF || this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF || this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.player, this.amountOfCards));
        }
        this.isDone = true;
    }
}
