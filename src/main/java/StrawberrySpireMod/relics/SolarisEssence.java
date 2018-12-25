package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

public class SolarisEssence extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:SolarisEssence";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/solarisEssence.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/solarisEssenceOutline.png");
    private static final int BURN_AMOUNT = 2;

    public SolarisEssence() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BURN_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }

    public void atBattleStartPreDraw() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn(), BURN_AMOUNT));
    }

    public AbstractRelic makeCopy() {
        return new SolarisEssence();
    }
}
