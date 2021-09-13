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

import static mindustry.type.ItemStack.*;
import heav.world.blocks.defense.StatusEffectProjector;
import heav.world.blocks.defense.Tesla;

public class HMBlocks implements ContentList{
  public static Block
	
	//defense
	statusEffectProjector, tesla;
  
	
  @Override
	public void load(){
		statusEffectProjector = new StatusEffectProjector("statusEffectProjector"){{
			
		}};
		
		tesla = new Tesla("tesla"){{
				
		}};
	}
}