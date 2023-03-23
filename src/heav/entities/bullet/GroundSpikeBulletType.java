package heav.entities.bullet;

import mindustry.gen.*;
import arc.graphics.Color;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;

public class GroundSpikeBulletType extends BasicBulletType{
	public float groundEffectST = 0f;
	public float groundBulletSpacing = 0f;
	public float groundBulletST = 0f;
	public Effect groundEffect = Fx.none;
	public BulletType groundBullet = Bullets.placeholder;
	public int groundBullets = 2;

	public GroundSpikeBulletType(float speed, float damage, String sprite){
		super(speed, damage, sprite);
		absorbable = false;
		reflectable = false;
		hittable = false;
		collides = false;
		collidesGround = false;
		collidesAir = false;
		collidesTiles = false;
		hitEffect = Fx.none;
		despawnEffect = Fx.none;
		shootEffect = Fx.none;
		smokeEffect = Fx.none;
	}

	public GroundSpikeBulletType(float speed, float damage){
		this(speed, damage, "bullet");
	}
	
	public GroundSpikeBulletType(){
		this(1, 1);
	}
  @Override
	public void drawLight(Bullet b){
		//nothing
	};

  @Override
	public void draw(Bullet b){
	  //nothing
	};

  @Override
	public void update(Bullet b){
		Color col = b.tileOn().floor().mapColor;
		if(b.timer.get(0, groundEffectST)){
			if(groundEffect != null && groundEffect != Fx.none){
		  	groundEffect.at(b.x, b.y, b.rotation(), col);
	  	}
			Sounds.place.at(b.x, b.y, 0.42f, 1f);
		}

		if(b.timer.get(1, groundBulletST)){
			groundEffect.at(b.x, b.y, b.rotation(), col);
			for(int i = 0; i < groundBullets; i++){
				float angle = b.rotation() + (i - (groundBullets / 2)) * groundBulletSpacing;
				groundBullet.create(b, b.x, b.y, angle);
			};
		};
	};

  @Override
	public float estimateDPS(){
    float sum = super.estimateDPS();
    if(groundBullet != null && groundBullet != this){
      sum += (groundBullet.estimateDPS() * groundBullets / 2f) * (lifetime / groundBulletST);
    }
    return sum;
	};
}
