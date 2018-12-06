package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class BarkskinPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:Barkskin";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public BarkskinPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        loadRegion("noPain");
        this.priority = 0;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage - amount;
        }
        else {
            return damage;
        }
    }
}
