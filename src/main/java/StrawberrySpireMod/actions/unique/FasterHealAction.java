package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;

public class FasterHealAction extends AbstractGameAction { // Why do you take so long HealAction? :(

    public FasterHealAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.HEAL;
    }

    public void update() {
        this.target.heal(this.amount);
        this.isDone = true;
    }
}
