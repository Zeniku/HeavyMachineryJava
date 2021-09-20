package heav.entities.bullet;

import arc.math.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.defense.*;

public class OverseerBulletType extends BasicBulletType{
  public int trailLength = 15;
  public float trailWidth = 10;
  public boolean homeStop = false;
  public Color trailColor = Pal.lancerLaser;
  public Effect trailEffect = Fx.none;
  
  public OverseerBulletType(float speed, float damage, String sprite){
    super(speed, damage, sprite);
    pierce = true;
    pierceCap = 10;
    hitEffect = Fx.hitLancer;
    drawSize = 300f;
    hitColor = Pal.lancerLaser;
  }

  public OverseerBulletType(float speed, float damage){
    this(speed, damage, "bullet");
  }
  
  @Override
  public void init(Bullet b){
    if(b.data == null){
      b.data = new OverseerBulletData(new Trail(trailLength));
    }
    super.init(b);
  }
  
  @Override
  public void update(Bullet b){
    if(!(b.data instanceof OverseerBulletData d)) return;
    
    float tx = 0, ty = 0;
		if(b.owner instanceof Unit u){
		  tx = u.aimX;
		  ty = u.aimY;
		};
	 	if(b.owner instanceof Turret.TurretBuild t){
		  tx = t.targetPos.x;
		  ty = t.targetPos.y;
	  };
	  
	  float ang = Angles.moveToward(b.rotation(), b.angleTo(tx, ty), turningPower * Time.delta * 50f);
	  
	  if(b.within(tx, ty, hitSize / 2f)){
		  if(homeStop){
	      d.home = false;
			}
		}
		
		if(b.timer.get(0, targetTime)){
		  if(homeStop){
			  if(d.home){
	        b.rotation(ang);
		      b.vel.setAngle(ang);
			  }
		  }else{
			  b.rotation(ang);
			  b.vel.setAngle(ang);
			}
	  }
	  
		if(trailEffect != Fx.none || trailEffect != null){
		  if(Mathf.chanceDelta(1)){
		    trailEffect.at(b.x, b.y, b.rotation());
		  };
	  }else{
	    d.trail.update(tx, ty);
	  }
  }
  
  @Override
  public void draw(Bullet b){
    if(!(b.data instanceof OverseerBulletData d)) return;
    if(!(trailEffect != Fx.none || trailEffect != null)){
	    d.trail.draw(trailColor, trailWidth);
	  }
	  super.draw(b);
  }
  
  public static class OverseerBulletData{
    public boolean home = true;
    public Trail trail;
    
    public OverseerBulletData(Trail trail){
      this.trail = trail;
    }
  }
}