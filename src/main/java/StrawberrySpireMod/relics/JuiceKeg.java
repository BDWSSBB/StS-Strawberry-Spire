package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.actions.unique.*;

public class JuiceKeg extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:JuiceKeg";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireMod/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireMod/relics/outline/placeholder.png");
    private static final int STRENGTH_AMOUNT = 1;

    public JuiceKeg() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.BOSS, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STRENGTH_AMOUNT + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STRENGTH_AMOUNT), STRENGTH_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConfusionPower(AbstractDungeon.player)));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public void atTurnStart() {
        AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction(new JuiceKegAction(), 1)); // For some reason, the hand doesn't exist if you just add it in normally >:(
    }

    public AbstractRelic makeCopy() {
        return new JuiceKeg();
    }
}
