package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;

public class PoopsieRoll extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:PoopsieRoll";
    public static final Texture IMAGE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = ImageMaster.loadImage("StrawberrySpireModResources/relics/outline/placeholderOutline.png");

    public PoopsieRoll() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.UNCOMMON, LandingSound.FLAT);
        if (AbstractDungeon.player != null) {
            this.counter = (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) / 3;
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(((AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) / 3), true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void onBloodied() {
        this.counter = (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) / 3;
    }

    public AbstractRelic makeCopy() {
        return new PoopsieRoll();
    }
}
