package heav.entities.bullet;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.util.*;
import mindustry.content.Bullets;
import mindustry.entities.*;
import mindustry.entities.Units.Sortf;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

//wi made a bullet shoot a bullet aaaa
public class PortalBulletType extends BasicBulletType{
	public BulletType bullet = Bullets.placeholder;
	public float growTime = 10f, fadeTime = 10f;
	public float shootTime = 40f;
	public float radius = 3f;
	public float rotateSpeed = 4f; //why you ask i dont want it instant
	public float range = 32f;
	public float inaccuracy = 3f, spread = 1f;
	public int shots = 1;
	public Sortf unitSort = UnitSorts.closest;

	
  public PortalBulletType(float speed, float damage, String sprite){
    super(speed, damage, sprite);
    spin = 5f;
  };

	public PortalBulletType(float speed, float damage){
		this(speed, damage, "bullet");
	}

	public PortalBulletType(){
		this(1f, 1f);
	}

	@Override
	public void init(Bullet b){
	  b.data = new PortalBulletData();
		super.init(b);
	}

	public void targetPosition(Bullet b, Posc pos){
    if(pos == null) return;
		var offset = Tmp.v1.setZero();

		if(b.data instanceof PortalBulletData data){
		  data.targetPos.set(Predict.intercept(b, pos, offset.x, offset.y, bullet.speed <= 0.01f ? 99999999f : bullet.speed));
 
			if(data.targetPos.isZero()){
			  data.targetPos.set(pos);
			}
		}
	}

	public void turnToTarget(Bullet b, float targetRot){
	  if(b.data instanceof PortalBulletData data){
			//dont want to rotate the bullet it will be h
      data.rotation = Angles.moveToward(data.rotation, targetRot, rotateSpeed * Time.delta);
		}
	}

  protected void findTarget(Bullet b){
	  if(!(b.data instanceof PortalBulletData data)) return;
		if(collidesAir && !collidesGround){
			data.target = Units.bestEnemy(b.team, b.x, b.y, range, e -> !e.dead() && !e.isGrounded(), unitSort);
		}else{
			data.target = Units.bestTarget(b.team, b.x, b.y, range, e -> !e.dead() && (e.isGrounded() || collidesAir) && (!e.isGrounded() || collidesGround), g -> collidesGround, unitSort);

			if(data.target == null && healPercent != 0){
					data.target = Units.findAllyTile(b.team, b.x, b.y, range, t -> t.damaged());
			}
		}
	}

	@Override
	public void update(Bullet b){
		if(!(b.data instanceof PortalBulletData data)) return;

		findTarget(b);
		targetPosition(b, data.target);

    if(Float.isNaN(data.rotation)) data.rotation = 0;

		turnToTarget(b, b.angleTo(data.targetPos));

		if(b.timer(0, shootTime)){
			for (int i = 0; i < shots; i++) {
			  bullet.create(b, b.x, b.y, data.rotation + Mathf.range(inaccuracy + bullet.inaccuracy) + (i - (int)(shots / 2f)) * spread);
			}
		}
	}

	@Override
	public void draw(Bullet b){
		float fin = Mathf.curve(b.fin(), 0, growTime / b.lifetime);
		float fout = 1 - Mathf.curve(b.fin(), (b.lifetime - fadeTime) / b.lifetime, 1);
		float size = fin * fout * radius;
		float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360) + b.time * spin : 0);
		
		Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());

		Draw.mixcol(mix, mix.a);

		Draw.color(backColor);
		Draw.rect(backRegion, b.x, b.y, size, size, b.rotation() + offset);
		Draw.color(frontColor);
		Draw.rect(frontRegion, b.x, b.y, size, size, b.rotation() + offset);

		Draw.reset();
	}

	public class PortalBulletData {
		public Vec2 targetPos = new Vec2();
		public float rotation = 0;
		public @Nullable Posc target;
		
		public PortalBulletData(){
		}
	}
}
