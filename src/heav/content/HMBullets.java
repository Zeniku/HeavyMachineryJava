package heav.content;

import arc.graphics.*;
import mindustry.graphics.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.content.*;
import heav.entities.bullet.*;

public class HMBullets {
	public static BulletType
	//Overseer
	standardOverseer, mediumOverseer, highOverseer,
	//Ground Spike
	standardSpike, mediumSpike, highSpike,
	//Orbiter
	standardOrbiter, mediumOrbiter, highOrbiter,
	//Swords 
	standardSword, mediumSword, highSword,
	//Portal
	standardPortal, mediumPortal, highPortal,

	//UnitSpecific
	luciusBullet, machaeraBullet;

	public static void load(){
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

		standardSpike = new ShrapnelBulletType(){{
			fromColor = Color.valueOf("404040FF");
			toColor = Color.valueOf("2a2a2aFF");
			hitColor = Color.valueOf("2a2a2aFF");
			shootEffect = Fx.none;
			smokeEffect = Fx.none;
			serrations = 3;
			hitEffect = Fx.mine;
			knockback = 0;
			reflectable = false;
			width = 0;
			length = 16;
			lifetime = 12;
			serrations = 3;
			serrationWidth = 11;
			serrationSpacing = 4;
			serrationSpaceOffset = 9;
			serrationLenScl = 6;
			serrationFadeOffset = 0;
			damage = 20;
			recoil = -3; //dash
		}};

		mediumSpike = new ShrapnelBulletType(){{
			fromColor = Color.valueOf("404040FF");
			toColor = Color.valueOf("2a2a2aFF");
			hitColor = Color.valueOf("2a2a2aFF");
			shootEffect = Fx.none;
			smokeEffect = Fx.none;
			serrations = 3;
			hitEffect = Fx.mine;
			knockback = 0;
			reflectable = false;
			lifetime = 20;
			width = 0;
			length = 24;
			serrations = 3;
			serrationWidth = 11;
			serrationSpacing = 4;
			serrationSpaceOffset = 9;
			serrationLenScl = 6;
			serrationFadeOffset = 0;
			damage = 30;
			recoil = -2; //dass
		}};

		highSpike = new ShrapnelBulletType(){{
			fromColor = Color.valueOf("404040FF");
			toColor = Color.valueOf("2a2a2aFF");
			hitColor = Color.valueOf("2a2a2aFF");
			shootEffect = Fx.none;
			smokeEffect = Fx.none;
			serrations = 3;
			hitEffect = Fx.mine;
			knockback = 0;
			reflectable = false;
			lifetime = 30;
			width = 8;
			length = 32;
			serrations = 3;
			damage = 40;
			recoil = -2; //dash
			knockback = 0;
			fragBullet = standardSpike;
			fragBullets = 3;
		}};

		//Portal
		standardPortal = new PortalBulletType(){{}};

		//UnitSpecific
		luciusBullet = new ShrapnelBulletType(){{
			fromColor = Color.valueOf("404040");
			toColor = Color.valueOf("2a2a2a");
			hitColor = Color.valueOf("2a2a2a");
			shootEffect = Fx.none;
			smokeEffect = Fx.none;
			serrations = 3;
			hitEffect = Fx.mine;
			knockback = 0;
			reflectable = false;
			lifetime = 40;
			width = 10;
			length = 40;
			serrations = 3;
			damage = 65;
			recoil = -2; //dash
			knockback = -1;
			fragBullet = mediumSpike;
			fragBullets = 4;
		}};

		machaeraBullet = new ShrapnelBulletType(){{
			fromColor = Color.valueOf("404040FF");
			toColor = Color.valueOf("2a2a2aFF");
			hitColor = Color.valueOf("2a2a2aFF");
			shootEffect = Fx.none;
			smokeEffect = Fx.none;
			serrations = 3;
			hitEffect = Fx.mine;
			knockback = 0;
			reflectable = false;
			lifetime = 40;
			width = 15;
			length = 50;
			serrations = 5;
			damage = 75;
			recoil = -2; //dash
			knockback = -1;
			fragBullet = mediumSpike;
			fragBullets = 5;
		}};
	}
}
