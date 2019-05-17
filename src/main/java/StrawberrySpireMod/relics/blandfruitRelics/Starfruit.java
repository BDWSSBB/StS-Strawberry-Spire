package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.relics.*;

public class Starfruit extends AbstractStrawberrySpireRelic { // The Future Max HP benefits are patched.

    public static final String ID = "strawberrySpire:Starfruit";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/blandfruits/starfruit.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/blandfruit.png");
    private static final int MAX_HP_AMOUNT = 8;
    private static final int FUTURE_MAX_HP_PERCENT_INCREASE_AMOUNT = 50;

    public Starfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + MAX_HP_AMOUNT + DESCRIPTIONS[1] + FUTURE_MAX_HP_PERCENT_INCREASE_AMOUNT + DESCRIPTIONS[2];
    }

    public void onEquip() {
        flash();
        AbstractDungeon.player.increaseMaxHp(MAX_HP_AMOUNT, true);
    }

    public AbstractRelic makeCopy() {
        return new Starfruit();
    }
}
