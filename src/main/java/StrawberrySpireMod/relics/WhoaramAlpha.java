package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.powers.*;

import com.evacipated.cardcrawl.mod.stslib.relics.*;

public class WhoaramAlpha extends AbstractStrawberrySpireRelic implements OnChannelRelic {

    public static final String ID = "strawberrySpire:WhoaramAlpha";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int CHANNEL_AMOUNT = 2;
    private static final int FOCUS_AMOUNT = 1;

    public WhoaramAlpha() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.RARE, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CHANNEL_AMOUNT + DESCRIPTIONS[1] + FOCUS_AMOUNT + DESCRIPTIONS[2] + FOCUS_AMOUNT + DESCRIPTIONS[3];
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onChannel(AbstractOrb orb) {
        this.counter++;
        if (this.counter % CHANNEL_AMOUNT == 0) {
            this.counter = 0;
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, FOCUS_AMOUNT), FOCUS_AMOUNT));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseFocusPower(AbstractDungeon.player, FOCUS_AMOUNT), FOCUS_AMOUNT));
        }
    }

    public void onVictory() {
        this.counter = -1;
    }

    public AbstractRelic makeCopy() {
        return new WhoaramAlpha();
    }
}
