package heav.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.ShootSpread;
import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.graphics.*;
import heav.entities.bullet.*;
import heav.entities.abilities.*;
import heav.ai.types.*;

public class HMUnits{
	public static UnitType
	//Purple Air
	aranea, traho, spiculum, interitus, eterius,
	//OverSeer
	princeps, procurator, inductor,
	//Melee
	pugione, mucro, tragula, lucius, machaera;

	public static void load(){
		aranea = new UnitType("aranea"){{
			speed = 1.5f;
			hitSize = 8;
			health = 180f;
			range = 180f;
			maxRange = 180f;
			flying = true;
			circleTarget = true;
			aiController = FlyingAI::new;
			constructor = UnitEntity::create;
			weapons.add(
				new Weapon(){{
					reload = 24;
					shootCone = 180;
					ejectEffect = Fx.none;
					shootSound = Sounds.explosion;
					bullet = new BombBulletType(0f, 0f, "clear"){{
						hitEffect = Fx.pulverize;
						lifetime = 10f;
						splashDamageRadius = 70f;
						instantDisappear = true;
						splashDamage = 80f;
						killShooter = true;
						hittable = false;
						collidesAir = true;
					}};
				}}
			);
		}};

		traho = new UnitType("traho"){{
			speed = 1.5f;
			hitSize = 10.5f;
			health = 380;
			flying = true;
			circleTarget = false;
			lowAltitude = true;
			armor = 2;
			aiController = FlyingAI::new;
			constructor = UnitEntity::create;
			circleTarget = false;

			weapons.add(
				new Weapon("heavymachineryjava-trahoWeapon"){{
					x = 0;
					y = -5 / 4;
					recoil = 0;
					reload = 30;
					rotate = true;
					continuous = true;
					shootSound = Sounds.tractorbeam;
					bullet = new RayBulletType(3f, 142f){{
						width = 2f;
						maxRange = 142;
						lifetime = 120;
						force = 8f;
						scaledForce = 70f;
					}};
				}},
				new Weapon("heavymachineryjava-trahoWeaponII"){{
					x = 3 / 4;
					y = 0;
					shootY = 25 / 4;
					reload = 30;
					rotate = false;
					bullet = new SapBulletType(){{
						length = 8 * 10;
						damage = 20;
						shootEffect = Fx.shootSmall;
						hitColor = Pal.sapBullet;
						color = Pal.sapBullet;
						despawnEffect = Fx.none;
						width = 0.5f;
						knockback = 0;
					}};
				}}
			);
		}};

		spiculum = new UnitType("spiculum"){{
			speed = 3.5f;
			accel = 1.5f;
			hitSize = 22f;
			health = 780;
			flying = true;
			circleTarget = true;
			maxRange = 240;
			range = 240;
			lowAltitude = true;
			armor = 3;
			aiController = FlyingAI::new;
			constructor = UnitEntity::create;
			weapons.add(
				new Weapon("heavymachineryjava-spiculumWeapon"){{
					x = 34 / 4;
					y = 0;
					reload = 15;
					rotate = false;
					bullet = new SapBulletType(){{
						length = 8 * 10;
						damage = 37;
						shootEffect = Fx.shootSmall;
						hitColor = color = Pal.sapBullet;
						despawnEffect = Fx.none;
						width = 0.5f;
						knockback = 2.5f;
					}};
				}}
			);
			abilities.add(
				new LaserMoveAbility(new ContinuousLaserBulletType(20){{
					length = 8f * 5f;
					damage = 23f;
					width = 3f;
					colors = new Color[]{Pal.sapBullet, Pal.sapBulletBack, Color.white};
				}}, 27f / 4f, 27f / 4f, 2, 5));
		}};
		
		interitus = new UnitType("interitus"){{
			speed = 1f;
			accel = 1f;
			hitSize = 47.75f;
			health = 6850;
			flying = true;
			lowAltitude = true;
			armor = 5;
			engineOffset = 24.25f;
			engineSize = 5;
			aiController = FlyingAI::new;
			constructor = UnitEntity::create;

			weapons.add(
				new Weapon("heavymachineryjava-interitusSpikeWeapon"){{
					reload = 30;
					rotate = true;
					x = 49 / 4;
					y = 27 / 4;
					bullet = new ShrapnelBulletType(){{
						fromColor = Pal.sapBullet;
						toColor = hitColor = Pal.sapBulletBack;
						length = 30 * 8;
						damage = 25;
						width = 16;
						status = StatusEffects.sapped;
						statusDuration = 60 * 7;
						serrations = 4;
					}};
					shootSound = Sounds.shotgun;
				}},

				new Weapon("heavymachineryjava-interitusSpikeWeapon"){{
					reload = 20;
					rotate = true;
					x = 74 / 4;
					y = -9 / 4;
					bullet = new ShrapnelBulletType(){{
						fromColor = Pal.sapBullet;
						toColor = hitColor = Pal.sapBulletBack;
						length = 25 * 8;
						damage = 25;
						width = 16;
						status = StatusEffects.sapped;
						statusDuration = 60 * 7;
						serrations = 4;
					}};
					shootSound = Sounds.shotgun;
				}},

				new Weapon("heavymachineryjava-interitusArtillery"){{
					reload = 60 * 4;
					recoil = 4;
					bullet = new ArtilleryBulletType(){{
						collidesAir = true;
						collides = true;
						collidesTiles = true;
						speed = 3.7f;
						lifetime = 55;
						hitEffect = despawnEffect = HMFx.bigBoom;
						height = 16;
						width = 16;
						damage = 230;
						splashDamage = 40;
						splashDamageRadius = 10 * 8;
						frontColor = Pal.sapBullet;
						backColor = Pal.sapBulletBack;
						fragBullets = 3;
						fragBullet = new ArtilleryBulletType(){{
							collidesAir = true;
							collides = true;
							collidesTiles = true;
							despawnEffect = HMFx.boom;
							damage = 30;
							splashDamage = 15;
							splashDamageRadius = 3 * 8;
							hitColor = backColor = Pal.sapBulletBack;
							frontColor = Pal.sapBullet;
							status = StatusEffects.sapped;
							statusDuration = 60 * 7;
							height = 10;
							width = 10;
						}};
						status = StatusEffects.sapped;
						statusDuration = 60 * 7;
						hitSound = Sounds.explosionbig;
					}};
					y = -15 / 4;
					x = 0;
					shootY = 58/ 4;
					mirror = false;
					shake = 7;
					rotate = true;
					rotateSpeed = 1.5f;
					shootSound = Sounds.shootBig;
				}}
			);
		}};

		eterius = new UnitType("eterius"){{
			speed = 1;
			accel = 1;
			hitSize = 73.25f;
			health = 21000;
			flying = true;
			lowAltitude = true;
			armor = 9;
			engineOffset = 24.25f;
			engineSize = 5;
			range = 280;
			maxRange = 280;
			aiController = FlyingAI::new;
			constructor = UnitEntity::create;
			
			weapons.add(
				new Weapon("heavymachineryjava-eteriusLaser"){{
					x = 0;
					y = -5 / 4;
					mirror = false;
					rotate = true;
					rotateSpeed = 1.5f;
					shoot.firstShotDelay = HMFx.laserCharge.lifetime;
					recoil = 4;
					bullet = new LaserBulletType(){{
						damage = 400;
						length = 8 * 40;
						width = 8 * 8;
						lifetime = 65;
						shootEffect = HMFx.laserCharge;
						lightningSpacing = 35;
						lightningLength = 5;
						lightningDelay = 1.1f;
						lightningLengthRand = 15;
						lightningDamage = 50;
						lightningAngleRand = 40;
						lightningColor = lightColor = Pal.sapBullet;
						largeHit = true;
						sideAngle = 15;
						sideWidth = 0;
						sideLength = 0;
						colors = new Color[]{Pal.sapBulletBack, Pal.sapBullet, Color.white};
					}};
					reload = 60 * 5;
					cooldownTime = 60 * 5;
					shootStatusDuration = 60;
					shootStatus = StatusEffects.unmoving;
					shake = 14;
					shootSound = Sounds.laserblast;
					chargeSound = Sounds.lasercharge;
				}},
				
				new Weapon("heavymachineryjava-eteriusArtillery"){{
					x = 74 / 4;
					y = -76 / 4;
					reload = 30;
					shootSound = Sounds.shootSnap;
					recoil = 3;
					rotate = true;
					bullet = new ArtilleryBulletType(){{
						damage = 40;
						splashDamage = 20;
						splashDamageRadius = 8 * 6;
						lifetime = 90;
						speed = 2.3f;
						height = 18;
						width = 16;
						frontColor = Pal.sapBullet;
						backColor = Pal.sapBulletBack;
						fragBullets = 5;
						fragBullet = new MissileBulletType(){{
							damage = 50;
							speed = 2.3f;
							homingPower = 4;
							frontColor = trailColor = Pal.sapBullet;
							backColor = Pal.sapBulletBack;
							height = 10;
							width = 8;
							splashDamage = 20;
							splashDamageRadius = 8 * 5;
							lifetime = 60;
						}};
						status = StatusEffects.sapped;
						statusDuration = 60 * 7;
					}};
				}}
			);
		}};

		princeps = new UnitType("princeps"){{
			aiController = DisabledPredictAi::new;
			constructor = MechUnit::create;
			speed = 0.8f;
			hitSize = 9;
			health = 250;
			maxRange = 140;
			range = 140;
			targetAir = true;
			armor = 3;
			weapons.add(
				new Weapon("heavymachineryjava-princepsWeapon"){{
					x = 5;
					y = 0;
					top = false;
					reload = 15;
					ejectEffect = Fx.lightningShoot;
					shootSound = Sounds.laser;
					bullet = HMBullets.standardOverseer;
				}}
			);
		}};

		procurator = new UnitType("procurator"){{
			aiController = DisabledPredictAi::new;
			constructor = MechUnit::create;
			speed = 0.6f;
			hitSize = 13.375f;
			health = 500;
			maxRange = 300;
			range = 300;
			targetAir = true;
			armor = 5f;

			weapons.add(
				new Weapon("heavymachineryjava-procuratorWeapon"){{
					x = 25 / 4;
					y = 3 / 4;
					top = false;
					reload = 25;
					ejectEffect = Fx.lightningShoot;
					shootSound = Sounds.laser;
					bullet = HMBullets.mediumOverseer;
				}}
			);
		}};

		inductor = new UnitType("inductor"){{
			aiController = DisabledPredictAi::new;
			constructor = MechUnit::create;
			health = 600;
			speed = 0.56f;
			hitSize = 17.625f;
			drag = 0.4f;
			range = 350;
			maxRange = 350;
			singleTarget = true;
			legCount = 6;
			legLength = 9;
			legForwardScl = 0.6f;
			legMoveSpace = 1.4f;
			hovering = true;
			armor = 6;
			allowLegStep = true;
			groundLayer = Layer.legUnit;

			weapons.add(
				new Weapon("heavymachineryjava-inductorShotgun"){{
					x = 36 / 4;
					y = 1 / 4;
					reload = 60;
					top = false;
					ejectEffect = Fx.lightningShoot;
					shootSound = Sounds.laser;
					shoot.shots = 4;
					inaccuracy = 15;
					bullet = HMBullets.mediumOverseer;
				}},

				new Weapon("heavymachineryjava-inductorArtillery"){{
					x = 0;
					y = -21 / 4;
					reload = 190;
					mirror = false;
					rotate = true;
					rotateSpeed = 3.5f;
					ejectEffect = Fx.lightningShoot;
					shootSound = Sounds.laser;
					bullet = HMBullets.mediumOrbiter;
					recoil = 4;
				}}
			);
		}};

		pugione = new UnitType("pugione"){{
			speed = 0.6f;
			hitSize = 11;
			health = 300;
			maxRange = 120;
			range = 120;
			
			targetAir = false;
			armor = 3;
			aiController = GroundAI::new;
			constructor = MechUnit::create;

			weapons.add(
				new Weapon("heavymachineryjava-pugioneWeapon"){{
					reload = 20;
					x = 5;
					y = 0;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.shotgun;
					shootY = 4.75f;
					recoil = -4; //negative so it looks like it's punching
					range = 80;
					soundPitchMin = 0.42f;
					soundPitchMax = 1.74f;
					rotate = true;
					rotateSpeed = 60;
					bullet = HMBullets.standardSpike;
				}}
			);
		}};

		mucro = new UnitType("mucro"){{
			speed = 0.45f;
			hitSize = 11.75f;
			health = 675;
			targetAir = false;
			maxRange = 170;
			range = 170;
			
			armor = 4;
			aiController = GroundAI::new;
			constructor = MechUnit::create;

			weapons.add(
				new Weapon("heavymachineryjava-mucroWeapon"){{
					x = 24 / 4;
					y = 0;
					reload = 30;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.shotgun;
					shootY = 30 / 4;
					recoil = -4; //negative so it looks like it's punching
					targetAir = false;
					soundPitchMin = 0.42f;
					soundPitchMax = 1.74f;
					rotate = true;
					rotateSpeed = 60f;
					bullet = HMBullets.mediumSpike;
					shoot = new ShootSpread(4, 22.5f);
					shoot.shotDelay = 5;
				}}
			);
		}};

		tragula = new UnitType("tragula"){{
			speed = 0.4f;
			hitSize = 15;
			health = 1175;
			targetAir = false;
			maxRange = 180;
			range = 180;
			armor = 9;
			mechFrontSway = 0.55f;
			aiController = GroundAI::new;
			constructor = MechUnit::create;

			weapons.add(
				new Weapon("heavymachineryjava-tragulaWeapon"){{
					x = 8;
					y = 1;
					reload = 40;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.shotgun;
					shootY = 35 / 4;
					recoil = -4; //negative so it looks like it's punching
					targetAir = false;
					soundPitchMin = 0.42f;
					soundPitchMax = 1.74f;
					rotate = true;
					rotateSpeed = 60;
					bullet = HMBullets.highSpike;
				}}
			);
		}};

		lucius = new UnitType("lucius"){{
			speed = 0.35f;
			hitSize = 22.5f;
			health = 12000;
			targetAir = false;
			maxRange = 190;
			range = 190;
			armor = 12;
			canDrown = false;
			mechFrontSway = 1;
			mechStepParticles = true;
			stepShake = 0.15f;
			aiController = GroundAI::new;
			constructor = MechUnit::create;

			weapons.add(
				new Weapon("heavymachineryjava-luciusWeapon"){{
					x = 44 / 4;
					y = 1 / 4;
					reload = 20;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.shotgun;
					shootY = 43 / 4;
					recoil = -4; //negative so it looks like it's punching
					targetAir = false;
					soundPitchMin = 0.42f;
					soundPitchMax = 1.74f;
					rotate = true;
					rotateSpeed = 60;
					bullet = HMBullets.luciusBullet;
				}},
				new Weapon("heavymachineryjava-earthBend"){{
					x = 0;
					y = 0;
					reload = 120;
					mirror = false;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.place;
					shootY = 35 / 4;
					targetAir = false;
					soundPitchMin = 0.42f;
					soundPitchMax = 1;
					rotate = true;
					rotateSpeed = 60;
					bullet = new GroundSpikeBulletType(){{
					  shootEffect = Fx.none;
						speed = 2.5f;
						lifetime = 60;
						groundBullet = HMBullets.standardSpike;
						groundEffect = HMFx.earthDust;
						groundBullets = 6;
						groundBulletST = 9.375f;
						groundEffectST = 5;
						groundBulletSpacing = 22.5f;
					}};
				}}
			);
		}};

		machaera = new UnitType("machaera"){{
			speed = 0.3f;
			hitSize = 31.75f;
			health = 16000;
			targetAir = false;
			maxRange = 220;
			range = 220;	
			armor = 16;
			canDrown = false;
			mechFrontSway = 1.79f;
			mechSideSway = 0.6f;
			mechStepParticles = true;
			stepShake = 0.15f;
			aiController = GroundAI::new;
			constructor = MechUnit::create;

			weapons.add(
				new Weapon("heavymachineryjava-machaeraWeapon"){{
					x = 75 / 4;
					y = -6 / 4;
					reload = 35;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.shotgun;
					shootY = 62 / 4;
					recoil = -5;
					targetAir = false;
					soundPitchMin = 0.42f;
					soundPitchMax = 1.74f;
					rotate = true;
					rotateSpeed = 60;

					shoot.shots = 2;
					shoot.shotDelay = 5;

					bullet = HMBullets.machaeraBullet;
					inaccuracy = 10;
				}},
				new Weapon("heavymachineryjava-earthBendII"){{
					x = 0;
					y = 0;
					reload = 120;
					mirror = false;
					top = false;
					ejectEffect = Fx.none;
					shootSound = Sounds.place;
					shootY = 35 / 4;
					targetAir = false;
					soundPitchMin = 0.42f;
					soundPitchMax = 1;
					rotate = true;
					rotateSpeed = 60;
					bullet = new GroundSpikeBulletType(){{
						shootEffect = Fx.none;
						speed = 2.5f;
						lifetime = 60;
						groundBullet = HMBullets.mediumSpike;
						groundEffect = HMFx.earthDustII;
						groundBullets = 6;
						groundBulletST = 6.25f;
						groundEffectST = 5;
						groundBulletSpacing = 22.5f;
					}};
				}}
			);
		}};
	};
};
