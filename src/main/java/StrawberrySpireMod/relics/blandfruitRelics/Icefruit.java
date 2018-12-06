package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.powers.*;

import basemod.abstracts.*;

public class Icefruit extends CustomRelic {

    public static final String ID = "strawberrySpire:Icefruit";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    private static final int MINIMUM_DAMAGE_AMOUNT = 6;
    private static final int STRENGTH_LOSS_AMOUNT = 1;

    public Icefruit() {
        super(ID, IMAGE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + MINIMUM_DAMAGE_AMOUNT + DESCRIPTIONS[1] + STRENGTH_LOSS_AMOUNT + DESCRIPTIONS[2];
    }

    public void atBattleStart()
    {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrostTouchPower(AbstractDungeon.player, STRENGTH_LOSS_AMOUNT), STRENGTH_LOSS_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public AbstractRelic makeCopy() {
        return new Icefruit();
    }
}
