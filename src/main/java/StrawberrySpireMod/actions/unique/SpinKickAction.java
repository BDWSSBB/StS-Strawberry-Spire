package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;

public class SpinKickAction extends AbstractGameAction {

    private AbstractCreature target;
    private AbstractPlayer player;
    private int blockNumber;

    public SpinKickAction(AbstractCreature m, int blockNumber) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.target = m;
        this.player = AbstractDungeon.player;
        this.blockNumber = blockNumber;
    }

    public void update() {
        if ((this.target != null) && (this.target.hasPower(WeakPower.POWER_ID))) {
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.player, this.player, this.blockNumber));
        }
        this.isDone = true;
    }
}