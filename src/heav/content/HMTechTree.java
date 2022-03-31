package heav.content;

import arc.util.Nullable;
import mindustry.content.Blocks;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.type.ItemStack;
import arc.struct.Seq;
import mindustry.game.Objectives;

public class HMTechTree {
	private static void node(UnlockableContent parent, UnlockableContent child, @Nullable ItemStack[] requirements, @Nullable Seq<Objectives.Objective> objectives) {
		ItemStack[] requirementsIn = (requirements != null)? requirements : child.researchRequirements();
		TechNode newNode = new TechTree.TechNode(TechTree.get(parent) , child, requirementsIn);
		
		if (objectives != null) newNode.objectives.addAll(objectives);
	}

  private static void node(UnlockableContent parent, UnlockableContent child, @Nullable ItemStack[] requirements) {
    node(parent, child, requirements, null);
  }

	private static void node(UnlockableContent parent, UnlockableContent child) {
		node(parent, child, null);
	}

	public static void load(){
		node(Blocks.coreShard, HMSectorPresents.generated);
	}
}
