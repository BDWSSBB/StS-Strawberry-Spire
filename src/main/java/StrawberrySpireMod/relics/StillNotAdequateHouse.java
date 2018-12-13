package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

import java.util.*;

public class StillNotAdequateHouse extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:StillNotAdequateHouse";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int GOLD_AMOUNT = 90;
    private static final int MAX_HP_AMOUNT = 6;
    private static final int ATTACK_CARD_AMOUNT = 1;
    private static final int SKILL_CARD_AMOUNT = 1;
    private static final int POWER_CARD_AMOUNT = 1;

    public StillNotAdequateHouse() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.RARE, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + GOLD_AMOUNT + DESCRIPTIONS[1] + MAX_HP_AMOUNT + DESCRIPTIONS[2] + ATTACK_CARD_AMOUNT + DESCRIPTIONS[3] + SKILL_CARD_AMOUNT + DESCRIPTIONS[4] + POWER_CARD_AMOUNT + DESCRIPTIONS[5];
    }

    public void onEquip() {
        flash();
        AbstractDungeon.player.gainGold(GOLD_AMOUNT);
        AbstractDungeon.player.increaseMaxHp(MAX_HP_AMOUNT, true);
        AbstractCard temp;
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (int i = 0; i < ATTACK_CARD_AMOUNT; i++) {
            temp = this.getCard(AbstractCard.CardType.ATTACK);
            if (temp != null) {
                cards.add(temp);
            }
        }
        for (int i = 0; i < SKILL_CARD_AMOUNT; i++) {
            temp = this.getCard(AbstractCard.CardType.SKILL);
            if (temp != null) {
                cards.add(temp);
            }
        }
        for (int i = 0; i < POWER_CARD_AMOUNT; i++) {
            temp = this.getCard(AbstractCard.CardType.POWER);
            if (temp != null) {
                cards.add(temp);
            }
        }
        for (AbstractCard c : cards) {
            c.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(c);
        }
        if (!cards.isEmpty()) {
            if (cards.size() == 1) {
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cards.get(0).makeStatEquivalentCopy()));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            }
            else if (cards.size() == 2) {
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cards.get(0).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cards.get(1).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F + 20.0F * Settings.scale, Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            }
            else {
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cards.get(0).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH - 40.0F * Settings.scale, Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cards.get(1).makeStatEquivalentCopy()));
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(cards.get(2).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH + 40.0F * Settings.scale, Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            }
        }
    }

    private AbstractCard getCard(AbstractCard.CardType type) {
        ArrayList<AbstractCard> list = new ArrayList();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade() && c.type == type) {
                list.add(c);
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        Collections.shuffle(list, new java.util.Random(AbstractDungeon.miscRng.randomLong()));
        return list.get(0);
    }

    public AbstractRelic makeCopy() {
        return new StillNotAdequateHouse();
    }
}
