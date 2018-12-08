package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;

import basemod.abstracts.*;

public class PoopsieRoll extends CustomRelic {

    public static final String ID = "strawberrySpire:PoopsieRoll";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int DAMAGE_PERCENT_AMOUNT = 40;

    public PoopsieRoll() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DAMAGE_PERCENT_AMOUNT + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(((AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) * DAMAGE_PERCENT_AMOUNT / 100), true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public AbstractRelic makeCopy() {
        return new PoopsieRoll();
    }
}
