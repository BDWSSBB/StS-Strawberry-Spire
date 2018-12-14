package StrawberrySpireMod.events.theCity;

import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.events.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

import StrawberrySpireMod.relics.*;

import java.util.*;

public class TheByrdhouse extends AbstractImageEvent {

    public static final String ID = "strawberrySpire:TheByrdhouse";
    private static final EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString(ID);
    public static final String[] DESCRIPTIONS = EVENT_STRINGS.DESCRIPTIONS;
    public static final String[] OPTIONS = EVENT_STRINGS.OPTIONS;
    private static final int REST_COST = 80;
    private static final float HP_HEAL_PERCENT = 0.50F;
    private static final float ASC_HP_HEAL_PERCENT = 0.375F;
    private CurScreen screen;
    private int healAmount;
    private AbstractCard cardOption;

    private enum CurScreen {
        INTRO_1, INTRO_2, RENTED_1, RENTED_2, END_1, END_2
    }

    public TheByrdhouse(){
        super(EVENT_STRINGS.NAME, DESCRIPTIONS[0], "StrawberrySpireMod/events/theByrdhouse.jpg");
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.healAmount = ((int)(AbstractDungeon.player.maxHealth * ASC_HP_HEAL_PERCENT));
        }
        else {
            this.healAmount = ((int)(AbstractDungeon.player.maxHealth * HP_HEAL_PERCENT));
        }
        this.cardOption = this.getRandomNonBasicCard();
        this.screen = CurScreen.INTRO_1;
        this.imageEventText.setDialogOption(OPTIONS[0]);
    }

    public static boolean canSpawn() {
        if (AbstractDungeon.player.gold >= REST_COST) {
            return true;
        }
        else {
            return false;
        }
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO_1:
                int randomSound = MathUtils.random(2);
                if (randomSound == 0) {
                    CardCrawlGame.sound.play("VO_CULTIST_1A");
                }
                else if (randomSound == 1) {
                    CardCrawlGame.sound.play("VO_CULTIST_1B");
                }
                else {
                    CardCrawlGame.sound.play("VO_CULTIST_1C");
                }
                this.imageEventText.updateBodyText(DESCRIPTIONS[1] + DESCRIPTIONS[2]);
                this.screen = CurScreen.INTRO_2;
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[1] + REST_COST + OPTIONS[2] + this.healAmount + OPTIONS[3], AbstractDungeon.player.gold < REST_COST);
                this.imageEventText.setDialogOption(OPTIONS[4]);
                break;
            case INTRO_2:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.player.loseGold(REST_COST);
                        AbstractDungeon.player.heal(this.healAmount);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[4]);
                        this.screen = CurScreen.RENTED_1;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        break;
                    case 1:
                        this.openMap();
                        this.screen = CurScreen.END_2;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                }
                break;
            case RENTED_1:
                if (AbstractDungeon.player instanceof Ironclad) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5] + DESCRIPTIONS[7] + DESCRIPTIONS[6]);
                }
                else if (AbstractDungeon.player instanceof TheSilent) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5] + DESCRIPTIONS[8] + DESCRIPTIONS[6]);
                }
                else if (AbstractDungeon.player instanceof Defect) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5] + DESCRIPTIONS[9] + DESCRIPTIONS[6]);
                }
                else {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5] + DESCRIPTIONS[7] + DESCRIPTIONS[6]);
                }
                this.screen = CurScreen.RENTED_2;
                this.imageEventText.clearAllDialogs();
                if (cardOption != null) {
                    this.imageEventText.setDialogOption(OPTIONS[6] + cardOption.name + OPTIONS[7], cardOption.makeStatEquivalentCopy());
                }
                else {
                    this.imageEventText.setDialogOption(OPTIONS[8], true);
                }
                this.imageEventText.setDialogOption(OPTIONS[4]);
                break;
            case RENTED_2:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.effectList.add(new PurgeCardEffect(this.cardOption));
                        AbstractDungeon.player.masterDeck.removeCard(this.cardOption);
                        if (AbstractDungeon.player instanceof Ironclad) {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[10] + DESCRIPTIONS[12] + DESCRIPTIONS[11] + DESCRIPTIONS[14]);
                        }
                        else if (AbstractDungeon.player instanceof TheSilent) {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[10] + DESCRIPTIONS[13] + DESCRIPTIONS[11] + DESCRIPTIONS[14]);
                        }
                        else if (AbstractDungeon.player instanceof Defect) {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[10] + DESCRIPTIONS[13] + DESCRIPTIONS[11] + DESCRIPTIONS[14]);
                        }
                        else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[10] + DESCRIPTIONS[12] + DESCRIPTIONS[11] + DESCRIPTIONS[14]);
                        }
                        this.screen = CurScreen.END_1;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        break;
                    case 1:
                        this.openMap();
                        this.screen = CurScreen.END_2;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                }
                break;
            case END_1:
                if (AbstractDungeon.player instanceof Ironclad) {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F,  RelicLibrary.getRelic(TannedGloves.ID).makeCopy());
                    this.imageEventText.updateBodyText(DESCRIPTIONS[15] + DESCRIPTIONS[18] + DESCRIPTIONS[16] + DESCRIPTIONS[20] + DESCRIPTIONS[17]);
                }
                else if (AbstractDungeon.player instanceof TheSilent) {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F,  RelicLibrary.getRelic(SilkGarment.ID).makeCopy());
                    this.imageEventText.updateBodyText(DESCRIPTIONS[15] + DESCRIPTIONS[19] + DESCRIPTIONS[16] + DESCRIPTIONS[21] + DESCRIPTIONS[17]);
                }
                else if (AbstractDungeon.player instanceof Defect) {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F,  RelicLibrary.getRelic(HandKnitScarf.ID).makeCopy());
                    this.imageEventText.updateBodyText(DESCRIPTIONS[15] + DESCRIPTIONS[19] + DESCRIPTIONS[16] + DESCRIPTIONS[22] + DESCRIPTIONS[17]);
                }
                else {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F,  RelicLibrary.getRelic(TannedGloves.ID).makeCopy());
                    this.imageEventText.updateBodyText(DESCRIPTIONS[15] + DESCRIPTIONS[18] + DESCRIPTIONS[16] + DESCRIPTIONS[20] + DESCRIPTIONS[17]);
                }
                this.screen = CurScreen.END_2;
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[4]);
                break;
            case END_2:
                this.openMap();
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[4]);
        }
    }

    private AbstractCard getRandomNonBasicCard() {
        ArrayList<AbstractCard> list = new ArrayList();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if ((c.rarity != AbstractCard.CardRarity.BASIC) && (c.type != AbstractCard.CardType.CURSE)) {
                list.add(c);
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        Collections.shuffle(list, new java.util.Random(AbstractDungeon.miscRng.randomLong()));
        return list.get(0);
    }
}