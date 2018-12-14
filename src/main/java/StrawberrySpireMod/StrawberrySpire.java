package StrawberrySpireMod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.cards.blue.*;
import StrawberrySpireMod.cards.blue.Package; // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
import StrawberrySpireMod.cards.colorless.*;
import StrawberrySpireMod.cards.curses.*;
import StrawberrySpireMod.cards.green.*;
import StrawberrySpireMod.cards.red.*;
import StrawberrySpireMod.events.exordium.*;
import StrawberrySpireMod.events.theBeyond.*;
import StrawberrySpireMod.events.theCity.*;
import StrawberrySpireMod.relics.*;
import StrawberrySpireMod.relics.blandfruitRelics.*;

import basemod.*;
import basemod.helpers.*;
import basemod.interfaces.*;

@SpireInitializer
public class StrawberrySpire implements EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, PostInitializeSubscriber, PostPowerApplySubscriber {

    public StrawberrySpire() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new StrawberrySpire();
    }

    @Override
    public void receiveEditCards() {

        // Red cards
        BaseMod.addCard(new Aggression());
        BaseMod.addCard(new Beatdown());
        BaseMod.addCard(new Conflagrate());
        BaseMod.addCard(new CrashLanding());
        BaseMod.addCard(new DangerMountain());
        BaseMod.addCard(new DebrisPile());
        BaseMod.addCard(new DraftPunk());
        BaseMod.addCard(new Endurance());
        BaseMod.addCard(new FireDrill());
        BaseMod.addCard(new FortFortyFour());
        BaseMod.addCard(new GettingOverIt());
        BaseMod.addCard(new Harvest());
        BaseMod.addCard(new Hellbender());
        BaseMod.addCard(new Incursion());
        BaseMod.addCard(new JustAWound());
        BaseMod.addCard(new Overwork());
        BaseMod.addCard(new PowerStomp());
        BaseMod.addCard(new PumpUp());
        BaseMod.addCard(new Spite());
        BaseMod.addCard(new Taunt());
        BaseMod.addCard(new ZonePlayer());

        // Green cards
        BaseMod.removeCard(Prepared.ID, AbstractCard.CardColor.GREEN); // Side Swipe makes this card obsolete, and it sucks anyway.
        BaseMod.addCard(new DeltaFlow());
        BaseMod.addCard(new DreamfoilDart());
        BaseMod.addCard(new Fastroyer());
        BaseMod.addCard(new Grapple());
        BaseMod.addCard(new HopSkipAndJump());
        BaseMod.addCard(new HotShot());
        BaseMod.addCard(new Insectonator());
        BaseMod.addCard(new KnifeParty());
        BaseMod.addCard(new Lockstep());
        BaseMod.addCard(new Marathoner());
        BaseMod.addCard(new Momentum());
        BaseMod.addCard(new Pant());
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
        BaseMod.addCard(new AngeryBot());
        BaseMod.addCard(new AnotherWinter());
        BaseMod.addCard(new BiBeamBolt());
        BaseMod.addCard(new BlasterWyrm());
        BaseMod.addCard(new BreederCore());
        BaseMod.addCard(new Compressor());
        BaseMod.addCard(new DataDump());
        BaseMod.addCard(new Disconnect());
        BaseMod.addCard(new FocalLaser());
        BaseMod.addCard(new Gammacceleration());
        BaseMod.addCard(new GearCrunch());
        BaseMod.addCard(new HateBit());
        BaseMod.addCard(new NeonFlight());
        BaseMod.addCard(new Package());
        BaseMod.addCard(new Refactor());
        BaseMod.addCard(new ReturnNull());
        BaseMod.addCard(new Ronchyble());
        BaseMod.addCard(new SearchEngine());
        BaseMod.addCard(new THEL());

        // Colorless cards
        BaseMod.addCard(new Disreguard());
        BaseMod.addCard(new Dropbox());
        BaseMod.addCard(new ExtraUtilities());
        BaseMod.addCard(new Fade());
        BaseMod.addCard(new Flashback());
        BaseMod.addCard(new NeglectedBlade());
        BaseMod.addCard(new Neurons());

        // Curses
        BaseMod.addCard(new AscendersBanana());
        BaseMod.addCard(new Teyered());
    }

    @Override
    public void receiveEditRelics(){

        // Standard Pool Relics
        BaseMod.addRelic(new AztechBall(), RelicType.BLUE);
        BaseMod.addRelic(new BonfireDress(), RelicType.SHARED);
        BaseMod.addRelic(new BrokenPrinter(), RelicType.SHARED);
        BaseMod.addRelic(new ByrdBeak(), RelicType.GREEN);
        BaseMod.addRelic(new CapacitorDischarge(), RelicType.BLUE);
        BaseMod.addRelic(new CharschorBlot(), RelicType.SHARED);
        BaseMod.addRelic(new Dictionifier(), RelicType.SHARED);
        BaseMod.addRelic(new DirtyLipstick(), RelicType.SHARED);
        BaseMod.addRelic(new ElectricRodnt(), RelicType.BLUE);
        BaseMod.addRelic(new ElefentMask(), RelicType.SHARED);
        BaseMod.addRelic(new Flakhammer(), RelicType.SHARED);
        BaseMod.addRelic(new GlyphStone(), RelicType.SHARED);
        BaseMod.addRelic(new InflatableHammer(), RelicType.SHARED);
        BaseMod.addRelic(new IronFlakes(), RelicType.RED);
        BaseMod.addRelic(new JuiceKeg(), RelicType.RED);
        BaseMod.addRelic(new KineticCharger(), RelicType.SHARED);
        BaseMod.addRelic(new MawJerky(), RelicType.SHARED);
        BaseMod.addRelic(new PoopsieRoll(), RelicType.SHARED);
        BaseMod.addRelic(new RottenAvocado(), RelicType.SHARED);
        BaseMod.addRelic(new ShipInABottle(), RelicType.SHARED);
        BaseMod.addRelic(new SolarisEssence(), RelicType.SHARED);
        BaseMod.addRelic(new StillNotAdequateHouse(), RelicType.SHARED);
        BaseMod.addRelic(new TinySteamboat(), RelicType.SHARED);
        BaseMod.addRelic(new Treaders(), RelicType.SHARED);
        BaseMod.addRelic(new ThornyGelberry(), RelicType.SHARED);
        BaseMod.addRelic(new WhoaramAlpha(), RelicType.BLUE);
        BaseMod.addRelic(new WyrmJar(), RelicType.RED);

        // Event relics
        BaseMod.addRelic(new BrokenVision(), RelicType.SHARED);
        BaseMod.addRelic(new HandKnitScarf(), RelicType.BLUE);
        BaseMod.addRelic(new SilkGarment(), RelicType.GREEN);
        BaseMod.addRelic(new TannedGloves(), RelicType.RED);

        // Blandfruit relics
        BaseMod.addRelic(new Blandfruit(), RelicType.SHARED);
        BaseMod.addRelic(new Blindfruit(), RelicType.SHARED);
        BaseMod.addRelic(new Dreamfruit(), RelicType.SHARED);
        BaseMod.addRelic(new Earthfruit(), RelicType.SHARED);
        BaseMod.addRelic(new Fadefruit(), RelicType.SHARED);
        BaseMod.addRelic(new Firefruit(), RelicType.SHARED);
        BaseMod.addRelic(new Icefruit(), RelicType.SHARED);
        BaseMod.addRelic(new Rotfruit(), RelicType.RED);
        BaseMod.addRelic(new Sorrowfruit(), RelicType.GREEN);
        BaseMod.addRelic(new Starfruit(), RelicType.SHARED);
        BaseMod.addRelic(new Stormfruit(), RelicType.BLUE);
        BaseMod.addRelic(new Sunfruit(), RelicType.SHARED);
        BaseMod.addRelic(new Swiftfruit(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "StrawberrySpireMod/localization/StrawberrySpire-CardStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "StrawberrySpireMod/localization/StrawberrySpire-EventStrings.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "StrawberrySpireMod/localization/StrawberrySpire-MonsterStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "StrawberrySpireMod/localization/StrawberrySpire-PowerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "StrawberrySpireMod/localization/StrawberrySpire-RelicStrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "StrawberrySpireMod/localization/StrawberrySpire-UIStrings.json");
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(BlandfruitBush.ID, BlandfruitBush.class, Exordium.ID);
        BaseMod.addEvent(MirrorTunnel.ID, MirrorTunnel.class, TheBeyond.ID);
        BaseMod.addEvent(TheByrdhouse.ID, TheByrdhouse.class, TheCity.ID);
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof  AbstractStrawberrySpireRelic) {
                ((AbstractStrawberrySpireRelic)r).onApplyPower(p, target, source);
            }
        }
    }
}