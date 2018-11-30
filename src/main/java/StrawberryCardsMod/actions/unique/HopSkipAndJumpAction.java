package StrawberryCardsMod.actions.unique;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;

public class HopSkipAndJumpAction extends AbstractGameAction {

    private AbstractPlayer player;
    private int blockAmount;

    public HopSkipAndJumpAction(int block) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.player = AbstractDungeon.player;
        this.blockAmount = block;
    }

    public void update() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!(m.isDead || m.isDying) && (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND)) {
                AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.player, this.player, this.blockAmount));
            }
        }
        this.isDone = true;
    }
}