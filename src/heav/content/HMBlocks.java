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
import heav.content.*;

import static mindustry.type.ItemStack.*;

public class HMBlocks implements ContentList{
  public static Block
	//walls
	lonsdaleiteWall, lonsdaleiteWallLarge,
	//defense
	statusEffectProjector, tesla;
  
	
  @Override
	public void load(){
		statusEffectProjector = new StatusEffectProjector("statusEffectProjector"){{
			statusFxEnemies = HMFx.flameBurst;
			healEffect = HMFx.healWave;
		}};
		
		tesla = new Tesla("tesla"){{
			hitEffect = HMFx.spark;
		}};

		lonsdaleiteWall = new DRWall("lonsdaleiteWall"){{
      dRChance = 15;
      dRPercentage = 20;
		}};

		lonsdaleiteWallLarge = new DRWall("lonsdaleiteWallLarge"){{
      dRChance = 15;
      dRPercentage = 45;
		}};

	}
}
