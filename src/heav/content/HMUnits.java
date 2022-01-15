package heav.content;

import mindustry.content.*;
import mindustry.type.*;
import mindustry.gen.*;
import mindustry.ctype.*;
import mindustry.entities.bullet.*;

public class HMUnits implements ContentList{
	public static UnitType
	//Purple Air
	aranea, traho, spiculum, interitus, eterius,
	//OverSeer
	princeps, procurator, inductor,
	//Melee
	pugione, mucro, tragula, lucius, machaera;

	@Override
	public void load(){
		aranea = new UnitType("aranea"){{
			speed = 1.5f;
			hitSize = 8;
			health = 180f;
			range = 180f;
			maxRange = 180f;
			flying = true;
			circleTarget = true;
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
	};
};
