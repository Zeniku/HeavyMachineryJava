package heav.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.graphics.*;
import heav.entities.bullet.*;
import heav.entities.abilities.*;

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
			defaultController = FlyingAI::new;
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
			defaultController = FlyingAI::new;
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
					bullet = new RayBulletType(){{
						length = 142;
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
						length = 8 * 15;
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
						hitColor = Pal.sapBullet;
						color = Pal.sapBullet;
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

			weapons.add(
				new Weapon("heavymachineryjava-interitusSpikeWeapon"){{
					reload = 20;
					rotate = false;
					x = 49 / 4;
					y = 27 / 4;
					bullet = new ShrapnelBulletType(){{
						hitColor = Pal.sapBulletBack;
						fromColor = Pal.sapBullet;
						toColor = Pal.sapBulletBack;
						length = 20 * 8;
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
					rotate = false;
					x = 74 / 4;
					y = -9 / 4;
					bullet = new ShrapnelBulletType(){{
						hitColor = Pal.sapBulletBack;
						fromColor = Pal.sapBullet;
						toColor = Pal.sapBulletBack;
						length = 20 * 8;
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
						hitEffect = HMFx.bigBoom;
						despawnEffect = HMFx.bigBoom;
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
							hitColor = Pal.sapBulletBack;
							backColor = Pal.sapBulletBack;
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
			
			weapons.add(
				new Weapon("heavymachineryjava-eteriusLaser"){{
					x = 0;
					y = -5 / 4;
					mirror = false;
					rotate = true;
					rotateSpeed = 1.5f;
					firstShotDelay = HMFx.laserCharge.lifetime;
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
						lightningColor = Pal.sapBullet;
						lightColor = Pal.sapBullet;
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
					bullet = new BasicBulletType(){{
						damage = 40;
						splashDamage = 20;
						splashDamageRadius = 8 * 6;
						lifetime = 60;
						speed = 2.3f;
						height = 12;
						width = 10;
						frontColor = Pal.sapBullet;
						backColor = Pal.sapBulletBack;
						fragBullets = 5;
						fragBullet = new MissileBulletType(){{
							damage = 50;
							speed = 2;
							homingPower = 4;
							frontColor = Pal.sapBullet;
							backColor = Pal.sapBullet;
							height = 6;
							width = 46;
							splashDamage = 20;
							splashDamageRadius = 8 * 5;
							lifetime = 50;
							trailColor = Pal.sapBullet;
						}};
						status = StatusEffects.sapped;
						statusDuration = 60 * 7;
						fragCone = 15;
					}};
				}}
			);
		}};
	};
};
