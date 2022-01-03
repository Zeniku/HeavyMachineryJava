package heav.content;

import mindustry.ctype.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import heav.entities.bullet.*;

public class HMBullets implements ContentList{
	public static BulletType
	//Overseer
	standardOverseer, mediumOverseer, highOverseer,
	//Ground Spike
	standardSpike, mediumSpike, highSpike,
	//Orbiter
	standardOrbiter, mediumOrbiter, highOrbiter,
	//Swords 
	standardSword, mediumSword, highSword;

	@Override
	public void load(){
		standardOverseer = new OverseerBulletType(2.5f, 18){{
			width = 7f;
      height = 9f;
      reloadMultiplier = 1.5f;
      ammoMultiplier = 5;
			lifetime = 60f * 2f;
      trailWidth = 2f;
      trailLength = 10;
			trailColor = Pal.lancerLaser;
		}};

    mediumOverseer = new OverseerBulletType(3f, 28){{
			width = 9f;
      height = 11f;
      reloadMultiplier = 2f;
      ammoMultiplier = 5;
			lifetime = 60f * 2f;
      trailWidth = 3f;
      trailLength = 10;
			trailColor = Pal.lancerLaser;
		}};

		highOverseer = new OverseerBulletType(4f, 49){{
			width = 11f;
      height = 13f;
      reloadMultiplier = 4f;
      ammoMultiplier = 7;
			lifetime = 60f * 4f;
      trailWidth = 2f;
      trailLength = 15;
			trailColor = Pal.lancerLaser;
		}};

		standardOrbiter = new OrbiterBulletType(2f, 30){{
			width = 7f;
      height = 9f;
			lifetime = 60f * 2f;
			hitSound = Sounds.plasmaboom;
			hitEffect = HMFx.orbExplode;
			orbiter = standardOverseer;
			trailColor = Pal.lancerLaser;
		}};

		mediumOrbiter = new OrbiterBulletType(2f, 40){{
			width = 9f;
      height = 11f;
			lifetime = 60f * 3f;
			hitSound = Sounds.plasmaboom;
			hitEffect = HMFx.orbExplode;
			orbiter = standardOverseer;
			trailColor = Pal.lancerLaser;
		}};

		highOrbiter = new OrbiterBulletType(3f, 50){{
			width = 11f;
      height = 13f;
			lifetime = 60f * 4f;
			hitSound = Sounds.plasmaboom;
			hitEffect = HMFx.orbExplode;
			orbiter = standardOverseer;
			trailColor = Pal.lancerLaser;
		}};
		
		standardSword = new RandSpriteBulletType(3f, 18, "heavymachineryjava-swordBullet"){{
			lifetime = 60f;
			pierceCap = 10;
			trailWidth = 4.5f;
			critTrail = HMFx.critTrail;
			spawnFx = HMFx.swordSpawnFx;
			trailColor = Pal.heal;
		}};

		mediumSword = new RandSpriteBulletType(3f, 29, "heavymachineryjava-swordBullet"){{
			lifetime = 60f * 1.5f;
			pierceCap = 10;
			trailWidth = 4.5f;
			critTrail = HMFx.critTrail;
			spawnFx = HMFx.swordSpawnFx;
			trailColor = Pal.heal;
		}};

		highSword = new RandSpriteBulletType(3f, 40, "heavymachineryjava-swordBullet"){{
			lifetime = 60f * 2.5f;
			pierceCap = 10;
			trailWidth = 4.5f;
			critTrail = HMFx.critTrail;
			spawnFx = HMFx.swordSpawnFx;
			trailColor = Pal.heal;
		}};
	}
}
