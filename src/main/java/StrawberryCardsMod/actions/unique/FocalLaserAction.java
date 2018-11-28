package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.ui.panels.*;

import java.util.UUID;

public class FocalLaserAction extends AbstractGameAction {

    private AbstractPlayer player;
    private UUID uuid;
    private int damageIncrementAmount;
    private boolean freeToPlayOnce;
    private int energyOnUse;

    public FocalLaserAction(UUID uuid, int magicNumber, boolean freeToPlayOnce, int energyOnUse) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.player = AbstractDungeon.player;
        this.uuid = uuid;
        this.damageIncrementAmount = magicNumber;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (this.player.hasRelic(ChemicalX.ID)) {
            effect += 2;
            this.player.getRelic(ChemicalX.ID).flash();
        }
        if (effect > 0)
        {
            AbstractDungeon.actionManager.addToTop(new ModifyDamageAction(this.uuid, this.damageIncrementAmount * effect));
            if (!this.freeToPlayOnce) {
                this.player.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
