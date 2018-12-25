package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.orbs.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.actions.unique.*;

import com.evacipated.cardcrawl.mod.stslib.relics.*;

public class AztechBall extends AbstractStrawberrySpireRelic implements OnChannelRelic {

    public static final String ID = "strawberrySpire:AztechBall";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");
    private static final int CHANNEL_AMOUNT = 2;

    public AztechBall() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SHOP, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CHANNEL_AMOUNT + DESCRIPTIONS[1];
    }

    public void onChannel(AbstractOrb orb) {
        AbstractDungeon.actionManager.addToTop(new AztechBallAction()); // It got weird when I tried putting the actual stuff in both hooks. This is safer.
    }

    public void onEvokeOrb(AbstractOrb orb) {
        AbstractDungeon.actionManager.addToTop(new AztechBallAction());
    }

    public AbstractRelic makeCopy() {
        return new AztechBall();
    }
}
