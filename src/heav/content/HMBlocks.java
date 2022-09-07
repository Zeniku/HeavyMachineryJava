package heav.content;

import mindustry.content.*;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootPattern;
import mindustry.game.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.*;
import mindustry.world.draw.DrawFrames;
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
	statusEffectProjector, tesla,
	//Production
	lonsdaleiteCompressor;
  
	public static void load(){
		statusEffectProjector = new StatusEffectProjector("statusEffectProjector"){{
			statusFxEnemies = HMFx.flameBurst;
			healEffect = HMFx.healWave;
      size = 3;
      health = 900;
      requirements(Category.effect, with(Items.titanium, 200, Items.plastanium, 100, Items.silicon, 200, Items.graphite, 300));
			consumePower(300f/60f);	
		}};
		
		tesla = new Tesla("tesla"){{
			hitEffect = HMFx.spark;
      size = 3;
      health = 1500;
  	  requirements(Category.effect, with(Items.titanium, 150, Items.plastanium, 150, HMItems.lonsdaleite, 100, Items.silicon, 200, Items.graphite, 300));
			consumePower(300f/60f);
		}};

		lonsdaleiteCompressor = new GenericCrafter("lonsdaleite-compressor"){{
			size = 3;
			hasPower = true;
			hasItems = true;
			hasLiquids = false;
			craftTime = 45;
			craftEffect = Fx.producesmoke;
			update = true;
			itemCapacity = 30;
			consumePower(0.7f);
			consumeItem(Items.graphite, 12);

			drawer = new DrawFrames(){{
				frames = 4;
			}};

			outputItem = new ItemStack(HMItems.lonsdaleite, 1);
			requirements(Category.crafting, with(Items.copper, 100, Items.lead, 150, Items.silicon, 250, Items.titanium, 120, Items.graphite, 80));
		}};
		
		heavenlyStrike = new FractalTurret("heavenlyStrike"){{
			health = 1540;
      recoil = 0;
			shoot = new ShootPattern(){{
				shots = 3;
			}};
		  size = 4;
			reload = 20f;
			shootType = HMBullets.mediumSword;
			range = (shootType.lifetime * shootType.speed) / 2f;
			requirements(Category.turret, with(HMItems.lonsdaleite, 150, Items.titanium, 200, Items.lead, 280));
		}};
		
		praefector = new DisabledPredictTurret("praefector"){{
			health = 1280;
      recoil = 2;
			shoot = new ShootAlternate(){{
				shots = 2;
			}};
		  size = 4;
			reload = 15f;
			shootType = HMBullets.mediumOverseer;
			range = (shootType.lifetime * shootType.speed) / 1.5f;
			requirements(Category.turret, with(HMItems.lonsdaleite, 100, Items.titanium, 150, Items.lead, 180));
		}};

		lonsdaleiteWall = new DRWall("lonsdaleiteWall"){{
      dRChance = 15f;
      dRPercentage = 20f;
      health = 520;
      armor = 4;
			insulated = true;
      requirements(Category.defense, with(HMItems.lonsdaleite, 6, Items.phaseFabric, 6));
		}};

		lonsdaleiteWallLarge = new DRWall("lonsdaleiteWallLarge"){{
			size = 2;
      dRChance = 15f;
      dRPercentage = 20f;
      health = 2800;
      armor = 4;
			insulated = true;
			requirements(Category.defense, with(HMItems.lonsdaleite, 24, Items.phaseFabric, 24));
		}};

		miniCore = new CoreBlock("miniCore"){
			{
				alwaysUnlocked = false;
				unitType = UnitTypes.alpha;
				health = 1200;
				itemCapacity = 1500;
                                armor = 2;
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
