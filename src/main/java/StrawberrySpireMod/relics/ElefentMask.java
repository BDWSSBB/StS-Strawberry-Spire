package StrawberrySpireMod.relics;

import StrawberrySpireMod.actions.unique.ApplyDebuffAndInverseAction;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

public class ElefentMask extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:ElefentMask";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");
    private static final int STRENGTH_LOSS_AMOUNT = 8;

    public ElefentMask() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.COMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STRENGTH_LOSS_AMOUNT + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        if (AbstractDungeon.getMonsters().monsters.size() >= 1) {
            AbstractMonster mostHpMonster = null;
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (mostHpMonster == null || m.maxHealth > mostHpMonster.maxHealth) {
                    mostHpMonster = m;
                }
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyDebuffAndInverseAction(mostHpMonster, AbstractDungeon.player, new StrengthPower(mostHpMonster, -STRENGTH_LOSS_AMOUNT), -STRENGTH_LOSS_AMOUNT, new GainStrengthPower(mostHpMonster, STRENGTH_LOSS_AMOUNT), STRENGTH_LOSS_AMOUNT));
        }
    }

    public AbstractRelic makeCopy() {
        return new ElefentMask();
    }
}