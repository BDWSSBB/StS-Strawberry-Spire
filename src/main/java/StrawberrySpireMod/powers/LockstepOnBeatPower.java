package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class LockstepOnBeatPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:LockstepOnBeat";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public LockstepOnBeatPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.updateDescription();
        loadRegion("focus");
        this.priority = 6;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LockstepOffBeatPower(this.owner)));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 2.0F;
        }
        else {
            return damage;
        }
    }
}
