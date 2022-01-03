package heav.entities.bullet;

import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.world.blocks.defense.turrets.*;

public class OverseerBulletType extends BasicBulletType{
  public float targetTime = 15f;
  public boolean homeStop = false;
  public Color trailColor = Pal.lancerLaser;

  public OverseerBulletType(float speed, float damage, String sprite){
    super(speed, damage, sprite);
    trailEffect = Fx.none;
    trailLength = 15;
    trailWidth = -1f;
    trailColor = Pal.lancerLaser;
    pierce = true;
    pierceCap = 10;
    homingPower = 10f;
    hitEffect = Fx.hitLancer;
    despawnEffect = Fx.hitLancer;
    hitColor = Pal.lancerLaser;
    frontColor = Color.white;
    backColor = Pal.lancerLaser;
  }

  public OverseerBulletType(float speed, float damage){
    this(speed, damage, "bullet");
  }

  @Override
  public void init(){ 
    super.init(); 
    if(trailWidth < 0f) trailWidth = width * (10f / 52f); //Should match up with normal bullet sprite  yep from meep
  };

  @Override
  public void init(Bullet b){
    if(b.data == null){
      b.data = new OverseerBulletData(true);
    }
    super.init(b);
  }
  
  @Override
  public void update(Bullet b){
    if(!(b.data instanceof OverseerBulletData d)) return;
    updateTrail(b);

    float tx = 0, ty = 0;
    if(b.owner instanceof Unit u){
      tx = u.aimX;
      ty = u.aimY;
    };
    if(b.owner instanceof Turret.TurretBuild t){
      tx = t.targetPos.x;
      ty = t.targetPos.y;
    };

    float ang = Angles.moveToward(b.rotation(), b.angleTo(tx, ty), homingPower * Time.delta * 50f);

    if(b.within(tx, ty, hitSize / 2f)){
      if(!homeStop){
	      d.home = false;
      }
    }

    if(homingPower > 0.0001f && b.time >= homingDelay){
      if(b.timer.get(0, targetTime)){
      	if(d.home){
      	  b.rotation(ang);
      	  b.vel.setAngle(ang);
        }
      }
    }

    if(weaveMag > 0){
      b.vel.rotate(Mathf.sin(b.time + Mathf.PI * weaveScale/2f, weaveScale, weaveMag * (Mathf.randomSeed(b.id, 0, 1) == 1 ? -1 : 1)) * Time.delta);
    }

    if(trailEffect != Fx.none || trailEffect != null){
      if(Mathf.chanceDelta(1)){
        trailEffect.at(b.x, b.y, trailParam, trailColor);
      };
    }
  }

  @Override
  public void drawTrail(Bullet b){
    if(trailLength > 0 && b.trail != null){
      //draw below bullets? TODO
      float z = Draw.z();
      Draw.z(z - 0.0001f);
      b.trail.draw(trailColor, trailWidth);
      Draw.z(z);
    }
  }
  
  public static class OverseerBulletData{
    public boolean home;
    
    public OverseerBulletData(boolean home){
      this.home = home;
    }
  }
}
