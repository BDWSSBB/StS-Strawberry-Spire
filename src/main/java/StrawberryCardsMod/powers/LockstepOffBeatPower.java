package StrawberryCardsMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class LockstepOffBeatPower extends AbstractPower {

    public static final String POWER_ID = "strawberryCards:LockstepOffBeat";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public LockstepOffBeatPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.updateDescription();
        loadRegion("bias");
        this.priority = 99;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LockstepOnBeatPower(this.owner)));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 0.5F;
        }
        else {
            return damage;
        }
    }
}
