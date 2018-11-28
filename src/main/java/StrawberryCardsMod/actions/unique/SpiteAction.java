package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;

public class SpiteAction extends AbstractGameAction {

    private AbstractCreature target;
    private AbstractPlayer player;
    private DamageInfo info;

    public SpiteAction(AbstractCreature target, DamageInfo info) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.target = target;
        this.player = AbstractDungeon.player;
        this.info = info;
    }

    public void update() {
        for (AbstractPower p : this.player.powers) {
            if (p.type == AbstractPower.PowerType.DEBUFF) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_LIGHT));
            }
        }
        this.isDone = true;
    }
}