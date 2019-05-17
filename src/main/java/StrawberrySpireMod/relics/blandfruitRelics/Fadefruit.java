package StrawberrySpireMod.relics.blandfruitRelics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.relics.*;

public class Fadefruit extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:Fadefruit";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/blandfruits/fadefruit.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/blandfruit.png");
    private static final int INITIAL_DRAW_REDUCTION = 1;
    private static final int CARD_TAKE_AMOUNT = 1;

    public Fadefruit() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + INITIAL_DRAW_REDUCTION + DESCRIPTIONS[1] + CARD_TAKE_AMOUNT + DESCRIPTIONS[2];
    }

    public void onEquip() {
        AbstractDungeon.player.masterHandSize -= INITIAL_DRAW_REDUCTION;
    }

    public void onUnequip() {
        AbstractDungeon.player.masterHandSize += INITIAL_DRAW_REDUCTION;
    }

    public void atPreBattle() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
        AbstractDungeon.actionManager.addToBottom(new SeekAction(CARD_TAKE_AMOUNT));
    }

    public void atBattleStart() {
        AbstractDungeon.player.gameHandSize += INITIAL_DRAW_REDUCTION;
    }

    public AbstractRelic makeCopy() {
        return new Fadefruit();
    }
}
