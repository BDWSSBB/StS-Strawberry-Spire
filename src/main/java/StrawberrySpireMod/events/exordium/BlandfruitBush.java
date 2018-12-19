package StrawberrySpireMod.events.exordium;

import com.megacrit.cardcrawl.cards.curses.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.events.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

import StrawberrySpireMod.relics.blandfruitRelics.*;

import java.util.*;

public class BlandfruitBush extends AbstractImageEvent {

    public static final String ID = "strawberrySpire:BlandfruitBush";
    private static final EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString(ID);
    public static final String[] DESCRIPTIONS = EVENT_STRINGS.DESCRIPTIONS;
    public static final String[] OPTIONS = EVENT_STRINGS.OPTIONS;
    private CurScreen screen;
    private ArrayList<String> seeds = new ArrayList<>();

    private enum CurScreen {
        INTRO, CHOOSE_SEED, END
    }

    public BlandfruitBush(){
        super(EVENT_STRINGS.NAME, DESCRIPTIONS[0], "StrawberrySpireModResources/events/blandfruitBush.jpg");
        this.screen = CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0], CardLibrary.getCopy(Clumsy.ID));
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO: {
                switch (buttonPressed) {
                    case 0: {
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, RelicLibrary.getRelic(Blandfruit.ID).makeCopy());
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(Clumsy.ID), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        CardCrawlGame.sound.play("BLUNT_HEAVY");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.screen = CurScreen.CHOOSE_SEED;
                        this.imageEventText.clearAllDialogs();
                        this.addSeedsAndShuffle();
                        if (AbstractDungeon.player.hasRelic(Cauldron.ID)) {
                            for (int i = 0; i < 4; i++) {
                                this.imageEventText.setDialogOption(OPTIONS[2] + seeds.get(i) + OPTIONS[3] + getFruit(seeds.get(i)).name + OPTIONS[5]);
                            }
                        }
                        else {
                            for (int i = 0; i < 4; i++) {
                                this.imageEventText.setDialogOption(OPTIONS[2] + seeds.get(i) + OPTIONS[3] + getFruit(seeds.get(i)).name + OPTIONS[4]);
                            }
                        }
                        break;
                    }
                    case 1: {
                        this.openMap();
                        this.screen = CurScreen.END;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[1]);
                    }
                }
                break;
            }
            case CHOOSE_SEED: {
                if (AbstractDungeon.player.hasRelic(Blandfruit.ID)) { // I swear something is gonna troll me by removing the blandfruit right when the player picks it up
                    if (AbstractDungeon.player.hasRelic(Cauldron.ID)) {
                        AbstractDungeon.player.loseRelic(Blandfruit.ID);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, RelicLibrary.getRelic(getFruit(seeds.get(buttonPressed)).relicId));
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                    }
                    else {
                        for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                            if (AbstractDungeon.player.relics.get(i).relicId.equals(Blandfruit.ID)) {
                                ((Blandfruit)AbstractDungeon.player.relics.get(i)).chosenFruit = getFruit(seeds.get(buttonPressed));
                            }
                        }
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    }
                }
                this.screen = CurScreen.END;
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[1]);
                break;
            }
            case END: {
                this.openMap();
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[1]);
            }
        }
    }

    private void addSeedsAndShuffle() {
        this.seeds.add(OPTIONS[6]);
        this.seeds.add(OPTIONS[7]);
        this.seeds.add(OPTIONS[8]);
        this.seeds.add(OPTIONS[9]);
        this.seeds.add(OPTIONS[10]);
        this.seeds.add(OPTIONS[11]);
        if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
            this.seeds.add(OPTIONS[12]);
        }
        if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
            this.seeds.add(OPTIONS[13]);
        }
        this.seeds.add(OPTIONS[14]);
        if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
            this.seeds.add(OPTIONS[15]);
        }
        this.seeds.add(OPTIONS[16]);
        this.seeds.add(OPTIONS[17]);
        Collections.shuffle(seeds, new java.util.Random(AbstractDungeon.miscRng.randomLong()));
    }

    private AbstractRelic getFruit(String seedName) {
        if (seedName.equals(OPTIONS[6])) {
            return new Blindfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[7])) {
            return new Dreamfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[8])) {
            return new Earthfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[9])) {
            return new Fadefruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[10])) {
            return new Firefruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[11])) {
            return new Icefruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[12])) {
            return new Rotfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[13])) {
            return new Sorrowfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[14])) {
            return new Starfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[15])) {
            return new Stormfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[16])) {
            return new Sunfruit().makeCopy();
        }
        else if (seedName.equals(OPTIONS[17])) {
            return new Swiftfruit().makeCopy();
        }
        else {
            return null;
        }
    }
}
