package heav.content;

import arc.util.Nullable;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.type.ItemStack;
import mindustry.game.Objectives.*;

public class HMTechTree {
	private static void node(UnlockableContent parent, UnlockableContent child, @Nullable ItemStack[] requirements, Seq<Objective> objectives) {
		ItemStack[] requirementsIn = (requirements != null)? requirements : child.researchRequirements();
		TechNode newNode = new TechTree.TechNode(TechTree.get(parent), child, requirementsIn);
		
		if (objectives != null) newNode.objectives.addAll(objectives);
	}

  private static void node(UnlockableContent parent, UnlockableContent child, @Nullable ItemStack[] requirements) {
    node(parent, child, requirements, null);
  }

	private static void node(UnlockableContent parent, UnlockableContent child) {
		node(parent, child, null);
	}

	public static void load(){
		// Planets and SectorPresets
		node(SectorPresets.tarFields, HMPlanets.caeruleum, null, Seq.with(new SectorComplete(SectorPresets.tarFields)));
		node(HMPlanets.caeruleum, HMSectorPresents.generated);

		//Blocks
		node(Blocks.coreFoundation, HMBlocks.miniCore);
		node(Blocks.lancer, HMBlocks.praefector);
		node(HMBlocks.praefector, HMBlocks.heavenlyStrike);
		node(Blocks.plastaniumWallLarge, HMBlocks.lonsdaleiteWall);
		node(HMBlocks.lonsdaleiteWall, HMBlocks.lonsdaleiteWallLarge);
		node(Blocks.overdriveProjector, HMBlocks.statusEffectProjector);
		node(HMBlocks.statusEffectProjector, HMBlocks.tesla);
		node(Blocks.siliconCrucible, HMBlocks.lonsdaleiteCompressor);
	}
}
