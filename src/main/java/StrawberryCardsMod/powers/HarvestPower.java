package StrawberryCardsMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

public class HarvestPower extends AbstractPower {

    public static final String POWER_ID = "strawberryCards:Harvest";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private boolean removePowerLater;

    public HarvestPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        loadRegion("sadistic");
        this.removePowerLater = false;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            this.amount -= 1;
            if (this.amount == 0) {
                this.removePowerLater = true;
            }
        }
    }

    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            if (AbstractDungeon.player.hasRelic(Boot.ID) && damageAmount < 5) { // Is there a better way to do this? Doesn't seem so.
                this.owner.heal(5 / 2);
            }
            else if (damageAmount > target.currentHealth) {
                this.owner.heal(target.currentHealth / 2);
            }
            else {
                this.owner.heal(damageAmount / 2);
            }
            if (removePowerLater) {
                removePowerLater = false;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            }
        }
    }
}
