package StrawberryCardsMod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberryCardsMod.cards.blue.*;
import StrawberryCardsMod.cards.blue.Package;
import StrawberryCardsMod.cards.colorless.*;
import StrawberryCardsMod.cards.curses.*;
import StrawberryCardsMod.cards.green.*;
import StrawberryCardsMod.cards.red.*;
import StrawberryCardsMod.powers.*;

import basemod.*;
import basemod.interfaces.*;

@SpireInitializer
public class StrawberryCards implements EditCardsSubscriber, EditStringsSubscriber, PostDrawSubscriber {

    public StrawberryCards() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new StrawberryCards();
    }

    @Override
    public void receiveEditCards() {

        // Red cards
        BaseMod.addCard(new Aggression());
        BaseMod.addCard(new Beatdown());
        BaseMod.addCard(new Conflagrate());
        BaseMod.addCard(new CrashLanding());
        BaseMod.addCard(new DebrisPile());
        BaseMod.addCard(new DraftPunk());
        BaseMod.addCard(new Endurance());
        BaseMod.addCard(new FortFortyFour());
        BaseMod.addCard(new Harvest());
        BaseMod.addCard(new Hellbender());
        BaseMod.addCard(new Incursion());
        BaseMod.addCard(new Overwork());
        BaseMod.addCard(new PowerStomp());
        BaseMod.addCard(new PumpUp());
        BaseMod.addCard(new Spite());

        // Green cards
        BaseMod.removeCard(Prepared.ID, AbstractCard.CardColor.GREEN); // Side Swipe makes this card obsolete, and it sucks anyway.
        BaseMod.addCard(new DeltaFlow());
        BaseMod.addCard(new FootworkForm());
        BaseMod.addCard(new Grapple());
        BaseMod.addCard(new HopSkipAndJump());
        BaseMod.addCard(new HotShot());
        BaseMod.addCard(new KnifeParty());
        BaseMod.addCard(new Lockstep());
        BaseMod.addCard(new Momentum());
        BaseMod.addCard(new Plunge());
        BaseMod.addCard(new Rummage());
        BaseMod.addCard(new SideSwipe());
        BaseMod.addCard(new Slide());
        BaseMod.addCard(new SpinKick());
        BaseMod.addCard(new Swampstar());
        BaseMod.addCard(new Trickbox());
        BaseMod.addCard(new Urgency());

        // Blue cards
        BaseMod.addCard(new Adapter());
        BaseMod.addCard(new AnotherWinter());
        BaseMod.addCard(new BiBeamBolt());
        BaseMod.addCard(new BreederCore());
        BaseMod.addCard(new Compressor());
        BaseMod.addCard(new DataDump());
        BaseMod.addCard(new FocalLaser());
        BaseMod.addCard(new Gammacceleration());
        BaseMod.addCard(new GearCrunch());
        BaseMod.addCard(new NeonFlight());
        BaseMod.addCard(new Package());
        BaseMod.addCard(new Refactor());
        BaseMod.addCard(new Salvo());
        BaseMod.addCard(new THEL());
        BaseMod.addCard(new TypeOrDie());

        // Colorless cards
        BaseMod.addCard(new Disreguard());
        BaseMod.addCard(new Dropbox());
        BaseMod.addCard(new ExtraUtilities());
        BaseMod.addCard(new Fade());
        BaseMod.addCard(new NeglectedBlade());
        BaseMod.addCard(new Neurons());

        // Curses
        BaseMod.addCard(new AscendersBanana());
        BaseMod.addCard(new Teyered());
    }

    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/StrawberryCards-CardStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/StrawberryCards-PowerStrings.json");
    }

    public void receivePostDraw(AbstractCard card) {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof HotShotPower) {
                ((HotShotPower)p).onCardDraw(card);
            }
            if (p instanceof ConflagratePower) {
                ((ConflagratePower)p).onCardDraw(card);
            }
        }
    }
}