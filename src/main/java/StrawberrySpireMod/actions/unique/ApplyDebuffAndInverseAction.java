package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class ApplyDebuffAndInverseAction extends AbstractGameAction {

    private AbstractCreature target;
    private AbstractCreature source;
    private AbstractPower debuff;
    private int debuffAmount;
    private AbstractPower inversePower;
    private int inversePowerAmount;

    public ApplyDebuffAndInverseAction(AbstractCreature target, AbstractCreature source, AbstractPower debuff, int debuffAmount, AbstractPower inversePower, int inversePowerAmount) {
        this.target = target;
        this.source = source;
        this.debuff = debuff;
        this.debuffAmount = debuffAmount;
        this.inversePower = inversePower;
        this.inversePowerAmount = inversePowerAmount;
    }

    @Override
    public void update() {
        if (this.target != null && !this.target.hasPower(ArtifactPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, inversePower, inversePowerAmount));
        }
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, debuff, debuffAmount));
        this.isDone = true;
    }
}
