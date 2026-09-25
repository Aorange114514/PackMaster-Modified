package pmoverrides.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.cards.red.*;
import pmoverrides.PackText;
import thePackmaster.packs.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PackContents {
    private static void set(ArrayList<String> cards, String... ids) {
        cards.clear();
        cards.addAll(Arrays.asList(ids));
    }

    @SpirePatch2(clz = SilentPack.class, method = "getCards")
    public static class Silent {
        @SpirePostfixPatch
        public static void Postfix(AbstractCardPack __instance, ArrayList<String> __result) {
            __instance.description = PackText.silent();
            set(__result, WraithForm.ID, GrandFinale.ID, CalculatedGamble.ID, WellLaidPlans.ID, Reflex.ID,
                    Tactician.ID, Acrobatics.ID, Prepared.ID, Eviscerate.ID, SneakyStrike.ID);
        }
    }

    @SpirePatch2(clz = IroncladPack.class, method = "getCards")
    public static class Ironclad {
        @SpirePostfixPatch
        public static void Postfix(AbstractCardPack __instance, ArrayList<String> __result) {
            __instance.description = PackText.ironclad();
            set(__result, Offering.ID, FiendFire.ID, DarkEmbrace.ID, SecondWind.ID, BattleTrance.ID,
                    Bloodletting.ID, Havoc.ID, Shockwave.ID, PommelStrike.ID, BurningPact.ID);
        }
    }

    @SpirePatch2(clz = WatcherPack.class, method = "getCards")
    public static class Watcher {
        @SpirePostfixPatch
        public static void Postfix(AbstractCardPack __instance, ArrayList<String> __result) {
            __instance.description = PackText.watcher(__instance.description);
            set(__result, Scrawl.ID, Vault.ID, EmptyMind.ID, Tantrum.ID, InnerPeace.ID,
                    Crescendo.ID, Prostrate.ID, FearNoEvil.ID, Indignation.ID, Rushdown.ID);
        }
    }

    @SpirePatch2(clz = DefectPack.class, method = "getCards")
    public static class Defect {
        @SpirePostfixPatch
        public static void Postfix(AbstractCardPack __instance, ArrayList<String> __result) {
            __instance.description = PackText.defect();
            set(__result, Coolheaded.ID, EchoForm.ID, Overclock.ID, MeteorStrike.ID, Skim.ID,
                    Recycle.ID, Heatsinks.ID, Aggregate.ID, Turbo.ID, Hologram.ID);
        }
    }

    @SpirePatch2(clz = BulwarkPack.class, method = "getCards")
    public static class Bulwark {
        @SpirePostfixPatch
        public static void Postfix(ArrayList<String> __result) {
            set(__result, GhostlyArmor.ID, FlameBarrier.ID, Rage.ID, Barricade.ID, ShrugItOff.ID,
                    Backflip.ID, Deflect.ID, LegSweep.ID, AfterImage.ID, ReinforcedBody.ID);
        }
    }

    @SpirePatch2(clz = StatusPack.class, method = "getCards")
    public static class Status {
        @SpirePostfixPatch
        public static void Postfix(ArrayList<String> __result) {
            set(__result, TrueGrit.ID, Terror.ID, SeverSoul.ID, PowerThrough.ID, Evolve.ID,
                    Immolate.ID, FireBreathing.ID, RecklessCharge.ID, Dropkick.ID, CoreSurge.ID);
        }
    }
}
