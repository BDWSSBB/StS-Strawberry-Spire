package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

public class ShipInABottle extends AbstractStrawberrySpireRelic { // Patched in GetRewardCardsPatches.java and GetColorlessRewardCardsPatches.

    public static final String ID = "strawberrySpire:ShipInABottle";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");

    public ShipInABottle() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ShipInABottle();
    }
}
