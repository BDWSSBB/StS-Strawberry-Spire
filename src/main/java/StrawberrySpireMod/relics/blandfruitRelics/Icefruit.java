package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.powers.*;

import basemod.abstracts.*;

public class Icefruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Icefruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int TURN_AMOUNT = 3;
    private static final int STRENGTH_LOSS_AMOUNT = 1;

    public Icefruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TURN_AMOUNT + DESCRIPTIONS[1] + STRENGTH_LOSS_AMOUNT + DESCRIPTIONS[2];
    }

    public void atBattleStart() {
        this.counter = 0;
        beginPulse();
    }

    public void atTurnStart() {
        if (this.counter < TURN_AMOUNT) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -STRENGTH_LOSS_AMOUNT), -STRENGTH_LOSS_AMOUNT));
            }
        }
        else if (this.counter == TURN_AMOUNT) {
            stopPulse();
        }
    }

    public void onPlayerEndTurn() {
        this.counter++;
    }

    public void onVictory() {
        this.counter = -1;
        stopPulse();
    }

    public AbstractRelic makeCopy() {
        return new Icefruit();
    }
}
