package heav.content;

import arc.util.Nullable;
import arc.func.Boolf;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.type.ItemStack;
import mindustry.game.Objectives.*;

public class HMTechTree {
	static TechTree.TechNode context = null;

	private static TechNode get(String tree, UnlockableContent parent){
		return findNode(TechTree.roots.find(r -> r.name == tree), n -> n.content == parent);
	}

  private static TechNode get(UnlockableContent parent){
		return get("serpulo", parent);
	}

  private static void vanillaNode(UnlockableContent parent, Runnable children){
		vanillaNode("serpulo", parent, children);
	}

	private static void vanillaNode(String tree, UnlockableContent parent, Runnable children){
		context = get(tree, parent);
		children.run();
	}


	private static TechNode findNode(TechNode root, Boolf<TechNode> filter){
		if(filter.get(root)) return root;
		for(TechNode node : root.children){
			TechNode search = findNode(node, filter);
			if(search != null) return search;
		}
		return null;
	}

	private static void node(UnlockableContent parent, UnlockableContent child, @Nullable ItemStack[] requirements, Seq<Objective> objectives) {
		ItemStack[] requirementsIn = (requirements != null)? requirements : child.researchRequirements();
		TechNode newNode = new TechTree.TechNode(get(parent), child, requirementsIn);
		
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
