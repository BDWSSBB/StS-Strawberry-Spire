package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

public class CapacitorDischarge extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:CapacitorDischarge";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireModResources/relics/outline/placeholder.png");
    private static final int FOCUS_AMOUNT = 3;
    private static final int DECREASE_ORBS_AMOUNT = 2;

    public CapacitorDischarge() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.BOSS, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + FOCUS_AMOUNT + DESCRIPTIONS[1] + DECREASE_ORBS_AMOUNT + DESCRIPTIONS[2];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new DecreaseMaxOrbAction(DECREASE_ORBS_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, FOCUS_AMOUNT), FOCUS_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

    }

    public AbstractRelic makeCopy() {
        return new CapacitorDischarge();
    }
}
