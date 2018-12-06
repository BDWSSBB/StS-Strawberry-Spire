package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class FrostTouchPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:FrostTouch";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private static final int MINIMUM_DAMAGE_AMOUNT = 6;

    public FrostTouchPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        loadRegion("int");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + MINIMUM_DAMAGE_AMOUNT + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if ((damageAmount >= MINIMUM_DAMAGE_AMOUNT) && (target != this.owner) && (info.type == DamageInfo.DamageType.NORMAL)) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new StrengthPower(target, -this.amount), -this.amount, true));
        }
    }
}
