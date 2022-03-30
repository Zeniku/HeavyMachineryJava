package heav.entities.bullet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.game.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.content.*;

//this is basically SapBulletType but Continuous

public class RayBulletType extends BulletType {
  public float length = 100f;
  public float sapStrength = 0.5f;
  public Color[] colors = {Pal.lancerLaser, Color.white.cpy()};
  public float width = 3f;
  public float force = 0.3f;
  public float scaledForce = 4f;
  public float fadeTime = 10f;
  public float growTime = 10f;
  public float[] widthScls = {1.8f, 1f};

  public RayBulletType(float damage, float length){
    super(0, damage);
    this.length = length;
    speed = 0f;
    despawnEffect = Fx.none;
    pierce = true;
    collides = false;
    hitSize = 0f;
    hittable = false;
    hitEffect = Fx.hitLiquid;
    lightColor = Pal.sap;
    lightOpacity = 0.6f;
    impact = true;
  }
  public RayBulletType(){
    this(1f, 1f);
  }
  @Override
  public void draw(Bullet b){
    if(b.data instanceof Position data){
      Tmp.v1.set(data);
        
      float fin = Mathf.curve(b.fin(), 0, growTime / b.lifetime);
      float fout = 1 - Mathf.curve(b.fin(), (b.lifetime - fadeTime) / b.lifetime, 1);
      float lWidth = fin * fout * width;

      for(int i = 0; i < 2; i++){
        Draw.color(colors[i]);
        Lines.stroke(lWidth * widthScls[i]);
        Lines.line(b.x, b.y, Tmp.v1.x, Tmp.v1.y, false);
        Fill.circle(b.x, b.y, Lines.getStroke() / 1.25f);
        Fill.circle(Tmp.v1.x, Tmp.v1.y, Lines.getStroke() / 1.25f);
        Draw.reset();
      }

      Drawf.light(Team.derelict, b.x, b.y, Tmp.v1.x, Tmp.v1.y, 15f * fin * fout + 5f, colors[1], lightOpacity);
    }
  }

  @Override
  public void drawLight(Bullet b){
    //no light
  }

  @Override
  public float range(){
    return Math.max(length, maxRange);
  }

  @Override
  public void update(Bullet b){
    super.init(b);
    Healthc target = Damage.linecast(b, b.x, b.y, b.rotation(), length);
    b.data = target;

    if(target != null){
      float result = Math.max(Math.min(target.health(), damage), 0);

      if(b.owner instanceof Healthc h){
        h.heal(result * sapStrength);
      }
    }

    if(target instanceof Hitboxc hit){
      hit.collision(b, hit.x(), hit.y());
      b.collision(hit, hit.x(), hit.y());

      if(hit instanceof Unitc unit){
        unit.impulse(Tmp.v3.set(hit).sub(b.x, b.y).nor().scl(-1 * (force + ((hit.dst(b) / range())) * scaledForce)));
      }

    }else if(target instanceof Building tile){
      if(tile.collide(b)){
        tile.collision(b);
        hit(b, tile.x, tile.y);
      }
   	}else{
     b.data = new Vec2().trns(b.rotation(), length).add(b.x, b.y);
    }
  }
}
