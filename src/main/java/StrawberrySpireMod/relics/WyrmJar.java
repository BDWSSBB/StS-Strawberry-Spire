package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class WyrmJar extends CustomRelic {

    public static final String ID = "strawberrySpire:WyrmJar";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int EXHAUST_AMOUNT = 3;
    private static final int STRENGTH_AMOUNT = 1;

    public WyrmJar() {
        super(ID, IMAGE_PATH, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + EXHAUST_AMOUNT + DESCRIPTIONS[1] + STRENGTH_AMOUNT + DESCRIPTIONS[2];
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void onExhaust(AbstractCard card) {
        this.counter += 1;
        if (this.counter % EXHAUST_AMOUNT == 0) {
            this.counter = 0;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STRENGTH_AMOUNT), STRENGTH_AMOUNT));
        }
    }

    public void onVictory() {
        this.counter = -1;
    }

    public AbstractRelic makeCopy() {
        return new WyrmJar();
    }
}

