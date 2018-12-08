package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class SilkGarment extends CustomRelic {

    public static final String ID = "strawberrySpire:SilkGarment";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int DRAW_AMOUNT = 1;
    private boolean hasDiscardedThisTurn = false;

    public SilkGarment() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.MAGICAL);
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1];
    }

    public void atPreBattle() {
        this.hasDiscardedThisTurn = false;
        this.pulse = true;
        beginPulse();
    }

    public void atTurnStart() {
        this.hasDiscardedThisTurn = false;
        this.pulse = true;
        beginPulse();
    }

    public void onManualDiscard() {
        if (!this.hasDiscardedThisTurn) {
            this.hasDiscardedThisTurn = true;
            this.pulse = false;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, DRAW_AMOUNT));
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new SilkGarment();
    }
}
