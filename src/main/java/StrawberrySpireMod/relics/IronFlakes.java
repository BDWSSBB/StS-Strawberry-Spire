package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.*;

public class IronFlakes extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:IronFlakes";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/ironFlakes.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/ironFlakesOutline.png");
    private static final int HEAL_AMOUNT = 1;

    public IronFlakes() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.RARE, LandingSound.HEAVY);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEAL_AMOUNT + DESCRIPTIONS[1];
    }

    public void onLoseHp(int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            flash();
            AbstractDungeon.player.heal(HEAL_AMOUNT);
        }
    }

    public AbstractRelic makeCopy() {
        return new IronFlakes();
    }
}