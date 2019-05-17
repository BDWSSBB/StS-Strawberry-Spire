package StrawberrySpireMod.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DreamfoilDartAction extends AbstractGameAction {

    private AbstractMonster target;
    private AbstractPlayer player;
    private int poisonToLoseStrength;

    public DreamfoilDartAction(AbstractMonster monster, int poisonToLoseStrength) {
        this.target = monster;
        this.player = AbstractDungeon.player;
        this.poisonToLoseStrength = poisonToLoseStrength;
    }

    public void update() {
        if (this.poisonToLoseStrength <= 0) {
            this.isDone = true;
        }
        if (!this.target.isDeadOrEscaped() && this.target.hasPower(PoisonPower.POWER_ID) && this.target.getPower(PoisonPower.POWER_ID).amount >= poisonToLoseStrength) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.player, new StrengthPower(this.target, -2), -2));
            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.player, PoisonPower.POWER_ID, poisonToLoseStrength));
        }
        this.isDone = true;
    }
}
