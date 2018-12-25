package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.*;

public class RottenAvocado extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:RottenAvocado";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");
    private static final int MAX_HP_LOSE_AMOUNT = 1;

    public RottenAvocado() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.BOSS, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + MAX_HP_LOSE_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }

    public void onEnterRoom(AbstractRoom room) {
        flash();
        AbstractDungeon.player.decreaseMaxHealth(MAX_HP_LOSE_AMOUNT);
    }

    public AbstractRelic makeCopy() {
        return new RottenAvocado();
    }
}
