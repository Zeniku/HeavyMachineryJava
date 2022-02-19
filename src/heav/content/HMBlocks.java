package heav.content;

import mindustry.content.*;
import mindustry.game.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.storage.*;
import heav.world.blocks.defense.*;
import heav.world.blocks.defense.turrets.*;

import static mindustry.type.ItemStack.*;

public class HMBlocks {
  public static Block
	//storage
	miniCore,
	//walls
	lonsdaleiteWall, lonsdaleiteWallLarge,
	//turrets
	heavenlyStrike,	praefector,
	//defense
	statusEffectProjector, tesla;
  
	public static void load(){
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
		
		heavenlyStrike = new FractalTurret("heavenlyStrike"){{
			health = 1540;
      recoilAmount = 0;
      shots = 3;
		  size = 4;
			reloadTime = 20f;
			requirements(Category.turret, with(HMItems.lonsdaleite, 150, Items.titanium, 200, Items.lead, 280));
		}};
		
		praefector = new DisabledPredictTurret("praefector"){{
			health = 1280;
      recoilAmount = 2;
			alternate = true;
		  size = 4;
			reloadTime = 15f;
			shots = 2;
			requirements(Category.turret, with(HMItems.lonsdaleite, 100, Items.titanium, 150, Items.lead, 180));
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

		miniCore = new CoreBlock("miniCore"){
			{
				alwaysUnlocked = false;
				unitType = UnitTypes.alpha;
				health = 1200;
				itemCapacity = 1500;
				size = 2;
				unitCapModifier = 0;
				requirements(Category.effect, with(Items.lead, 1500, Items.silicon, 2000, Items.copper, 1500, Items.titanium, 1500));
			};

			@Override
			public boolean canPlaceOn(Tile tile, Team team, int rotation){
				return true;
			}
		};
	}
}
