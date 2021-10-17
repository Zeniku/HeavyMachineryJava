package heav.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import heav.world.blocks.defense.*;
import heav.world.blocks.defense.turrets.*;

import static mindustry.type.ItemStack.*;

public class HMBlocks implements ContentList{
  public static Block
	//walls
	lonsdaleiteWall, lonsdaleiteWallLarge,
	//turrets
	
	//defense
	statusEffectProjector, tesla;
  
  @Override
	public void load(){
		statusEffectProjector = new StatusEffectProjector("statusEffectProjector"){{
			statusFxEnemies = HMFx.flameBurst;
			healEffect = HMFx.healWave;
      size = 3;
      health = 900;
      requirements(Category.effect, with(Items.titanium, 200, Items.plastanium, 100, Items.silicon, 200, Items.graphite, 300));
			consumes.power(300f/60f);	
		}};
		
		tesla = new Tesla("tesla"){{
			hitEffect = HMFx.spark;
      size = 3;
      health = 1500;
  	  requirements(Category.effect, with(Items.titanium, 150, Items.plastanium, 150, HMItems.lonsdaleite, 100, Items.silicon, 200, Items.graphite, 300));
			consumes.power(300f/60f);
		}};

		lonsdaleiteWall = new DRWall("lonsdaleiteWall"){{
      dRChance = 15f;
      dRPercentage = 20f;
      health = 520;
      requirements(Category.defense, with(HMItems.lonsdaleite, 6, Items.phaseFabric, 6));
		}};

		lonsdaleiteWallLarge = new DRWall("lonsdaleiteWallLarge"){{
			size = 2;
      dRChance = 15f;
      dRPercentage = 45f;
		  health = 2800;
			requirements(Category.defense, with(HMItems.lonsdaleite, 24, Items.phaseFabric, 24));
		}};
	}
}
