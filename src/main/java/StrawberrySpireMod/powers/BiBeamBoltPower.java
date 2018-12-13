package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.powers.*;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.*;

public class BiBeamBoltPower extends AbstractPower {

    public static final String POWER_ID = "strawberrySpire:BiBeamBolt";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;

    public BiBeamBoltPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("mastery");
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o.ID.equals(Lightning.ORB_ID)) {
                AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(o));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public void onEvokeOrb(AbstractOrb orb) {
        if (Lightning.ORB_ID.equals(orb.ID)) {
            for (int i = 0; i < this.amount; i++) {
                AbstractDungeon.player.orbs.get(0).onEvoke();
            }
        }
    }
}
