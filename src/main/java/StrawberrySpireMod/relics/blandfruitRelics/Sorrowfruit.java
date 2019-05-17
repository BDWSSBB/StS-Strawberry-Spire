package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.relics.*;

public class Sorrowfruit extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:Sorrowfruit";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/blandfruits/sorrowfruit.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/blandfruit.png");
    private static final int POISON_AMOUNT = 2;

    public Sorrowfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + POISON_AMOUNT + DESCRIPTIONS[1];
    }

    public void onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        if (p.type == AbstractPower.PowerType.DEBUFF && !p.ID.equals(PoisonPower.POWER_ID) && !p.ID.equals(GainStrengthPower.POWER_ID) && target != AbstractDungeon.player && !target.hasPower(ArtifactPower.POWER_ID) && source == AbstractDungeon.player) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new PoisonPower(target, source, POISON_AMOUNT), POISON_AMOUNT));
        }
    }

    public AbstractRelic makeCopy() {
        return new Sorrowfruit();
    }
}