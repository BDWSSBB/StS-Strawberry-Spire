package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

public class ElefentMask extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:ElefentMask";
    public static final Texture IMAGE_PATH = new Texture("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("StrawberrySpireModResources/relics/outline/placeholder.png");
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
        if (AbstractDungeon.getCurrRoom().monsters.monsters.size() >= 1) {
            AbstractMonster mostHpMonster = null;
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mostHpMonster == null || m.maxHealth > mostHpMonster.maxHealth) {
                    mostHpMonster = m;
                }
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mostHpMonster, AbstractDungeon.player, new StrengthPower(mostHpMonster, -STRENGTH_LOSS_AMOUNT), -STRENGTH_LOSS_AMOUNT));
            if (mostHpMonster != null && !mostHpMonster.hasPower(ArtifactPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mostHpMonster, AbstractDungeon.player, new GainStrengthPower(mostHpMonster, STRENGTH_LOSS_AMOUNT), STRENGTH_LOSS_AMOUNT));
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new ElefentMask();
    }
}