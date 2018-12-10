package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;

public class AddActionLaterAction extends AbstractGameAction { // This is to get certain actions at the correct priorities so stupid things like Whirlwind and Skewer don't ruin my life.

    private AbstractGameAction actionToQueue;
    private int actionPriority;

    public AddActionLaterAction(AbstractGameAction actionToQueue, int actionPriority) {
        this.actionToQueue = actionToQueue;
        this.actionPriority = actionPriority;
    }

    public void update() {
        if (this.actionPriority > 0) {
            AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction(this.actionToQueue, this.actionPriority - 1)); // LOOK AT DAT RECURSION
        }
        else {
            AbstractDungeon.actionManager.addToBottom(this.actionToQueue);
        }
        this.isDone = true;
    }
}
