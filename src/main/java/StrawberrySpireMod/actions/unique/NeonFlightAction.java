package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;

import java.util.ArrayList;

public class NeonFlightAction extends AbstractGameAction {

    private AbstractPlayer player;
    private int blockAmount;

    public NeonFlightAction(int blockNumber) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.player = AbstractDungeon.player;
        this.blockAmount = blockNumber;
    }

    public void update() {
        ArrayList<String> orbList = new ArrayList();
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if ((o.ID != null) && (!o.ID.equals(EmptyOrbSlot.ORB_ID)) && (!orbList.contains(o.ID))) {
                orbList.add(o.ID);
            }
        }
        for (int i = 0; i < orbList.size(); i++) {
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.player, this.player, this.blockAmount));
        }
        this.isDone = true;
    }
}