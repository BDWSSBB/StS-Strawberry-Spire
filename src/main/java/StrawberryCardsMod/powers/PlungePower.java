package StrawberryCardsMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class PlungePower extends AbstractPower {

    public static final String POWER_ID = "strawberryCards:Plunged";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public PlungePower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.DEBUFF;
        this.owner = owner;
        this.updateDescription();
        loadRegion("doubleTap");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 2.0F;
        }
        return damage;
    }
}
