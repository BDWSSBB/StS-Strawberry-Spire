package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.relics.*;

public class Swiftfruit extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:Swiftfruit";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/blandfruits/swiftfruit.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/blandfruit.png");
    public boolean isActive = false;

    public Swiftfruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        this.isActive = true;
        this.pulse = true;
        beginPulse();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.isActive = false;
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new Swiftfruit();
    }
}
