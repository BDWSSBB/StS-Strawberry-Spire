package StrawberrySpireMod.relics;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

import basemod.helpers.*;

import java.util.ArrayList;
import java.util.Iterator;

public class GlyphStone extends AbstractStrawberrySpireRelic {

    public static final String ID = "strawberrySpire:GlyphStone";
    public static final Texture IMAGE_PATH = new Texture("relics/placeholder.png");
    public static final Texture IMAGE_OUTLINE_PATH = new Texture("relics/outline/placeholder.png");
    private static final int TRANSFORM_AMOUNT = 2;

    public GlyphStone() {
        super(ID, IMAGE_PATH, IMAGE_OUTLINE_PATH, RelicTier.SHOP, LandingSound.SOLID);
        this.removeStrikeTip();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TRANSFORM_AMOUNT + DESCRIPTIONS[1];
    }

    public void onEquip() {
        flash();
        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.hasTag(BaseModCardTags.BASIC_STRIKE) || c.hasTag(BaseModCardTags.BASIC_DEFEND) || c instanceof Strike_Red || c instanceof Defend_Red || c instanceof Strike_Green || c instanceof Defend_Green || c instanceof Strike_Blue || c instanceof Defend_Blue) {
                temp.addToRandomSpot(c);
            }
        }
        float offset = 0.0F;
        for (int i = 0; i < TRANSFORM_AMOUNT; i++) {
            if (!temp.isEmpty()) {
                temp.shuffle();
                AbstractCard card = temp.getBottomCard();
                temp.removeCard(card);
                AbstractDungeon.player.masterDeck.removeCard(card);
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card)); // The visuals look kinda ugly, but I'm not in the mood to fix it.
                AbstractDungeon.transformCard(card, true, AbstractDungeon.miscRng);
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(AbstractDungeon.getTransformedCard(), Settings.WIDTH * 5.0F / 12.0F + offset, Settings.HEIGHT * 2.0F / 10.0F, false));
                offset += Settings.WIDTH / 6.0F;
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new GlyphStone();
    }

    private void removeStrikeTip()
    {
        ArrayList<String> strikes = new ArrayList();
        for (String s : GameDictionary.STRIKE.NAMES) {
            strikes.add(s.toLowerCase());
        }
        for (Object t = this.tips.iterator(); ((Iterator<Object>)t).hasNext();) {
            PowerTip derp = (PowerTip)((Iterator)t).next();
            String s = derp.header.toLowerCase();
            if (strikes.contains(s)) {
                ((Iterator)t).remove();
                break;
            }
        }
    }
}
