package StrawberrySpireMod.events.theBeyond;

import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.events.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

import StrawberrySpireMod.relics.*;

import java.util.*;

public class MirrorTunnel extends AbstractImageEvent {

    public static final String ID = "strawberrySpire:MirrorTunnel";
    private static final EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString(ID);
    public static final String[] DESCRIPTIONS = EVENT_STRINGS.DESCRIPTIONS;
    public static final String[] OPTIONS = EVENT_STRINGS.OPTIONS;
    private static final float FIRST_HP_LOSS_PERCENT = 0.125F;
    private static final float ASC_FIRST_HP_LOSS_PERCENT = 0.15F;
    private static final float SECOND_HP_LOSS_PERCENT = 0.22F;
    private static final float ASC_SECOND_HP_LOSS_PERCENT = 0.25F;
    private CurScreen screen;
    private int firstDamageAmount;
    private int secondDamageAmount;

    private enum CurScreen {
        INTRO, TAKE_1, TAKE_2, END
    }

    public MirrorTunnel() {
        super(EVENT_STRINGS.NAME, DESCRIPTIONS[0], "events/mirrorTunnel.jpg");
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.firstDamageAmount = ((int)(AbstractDungeon.player.maxHealth * ASC_FIRST_HP_LOSS_PERCENT));
            this.secondDamageAmount = ((int)(AbstractDungeon.player.maxHealth * ASC_SECOND_HP_LOSS_PERCENT));
        }
        else {
            this.firstDamageAmount = ((int)(AbstractDungeon.player.maxHealth * FIRST_HP_LOSS_PERCENT));
            this.secondDamageAmount = ((int)(AbstractDungeon.player.maxHealth * SECOND_HP_LOSS_PERCENT));
        }
        this.screen = CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0] + this.firstDamageAmount + OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[6], false, false, false, false);
                        AbstractDungeon.player.damage(new DamageInfo(null, this.firstDamageAmount));
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.screen = CurScreen.TAKE_1;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[0] + this.secondDamageAmount + OPTIONS[1]);
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.screen = CurScreen.END;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                }
                break;
            case TAKE_1:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[6], false, false, false, false);
                        AbstractDungeon.player.damage(new DamageInfo(null, this.secondDamageAmount));
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.screen = CurScreen.TAKE_2;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.screen = CurScreen.END;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                }
                break;
            case TAKE_2:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[6], false, false, false, false);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F,  RelicLibrary.getRelic(BrokenVision.ID).makeCopy());
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.screen = CurScreen.END;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.screen = CurScreen.END;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                        break;
                    case 2:
                        ArrayList<AbstractCard> cardsToDuplicate = new ArrayList<>();
                        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                            cardsToDuplicate.add(c);
                        }
                        for (AbstractCard c : cardsToDuplicate) {
                            c.inBottleFlame = false;
                            c.inBottleLightning = false;
                            c.inBottleTornado = false;
                            for (int i = 0; i < 2; i++) {
                                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(c.makeStatEquivalentCopy(), MathUtils.random(0.1F, 0.9F) * Settings.WIDTH, MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT));
                            }
                        }
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        this.screen = CurScreen.END;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                }
                break;
            case END:
                this.openMap();
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[2]);
        }
    }

    public void update() {
        super.update();
        if ((!AbstractDungeon.isScreenUp) && (AbstractDungeon.gridSelectScreen.selectedCards.size() == 1)) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeStatEquivalentCopy();
            c.inBottleFlame = false;
            c.inBottleLightning = false;
            c.inBottleTornado = false;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }
}
