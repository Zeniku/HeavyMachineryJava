package heav.entities.bullet;

import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.content.*;

public class OrbiterBulletType extends OverseerBulletType{
  public int orbiters = 4;
  public int orbiterTrailLength = 15;
  public float orbitSpeed = 1f;
  public float orbitRadius = 16f;
  public float orbiterST = 25f;
  public float orbiterRadius = 4f;
  public float orbiterTrailWidth = 2f;
  public Effect orbiterTrailEffect = Fx.none;
  public BulletType orbiter = Bullets.placeholder;

  public OrbiterBulletType(float speed, float damage, String sprite){
    super(speed, damage, sprite);
    homeStop = true;
  };
  
  public OrbiterBulletType(float speed, float damage){
    this(speed, damage, "bullet");
  };
  
  @Override
  public void init(Bullet b){
    if(b.data == null){
      Seq<Trail> trails = new Seq<Trail>();
      FloatSeq oX = new FloatSeq(), oY = new FloatSeq();
      for(int i = 0; i < orbiters; i++){
        oX.add(b.x);
        oY.add(b.y);
        trails.add(new Trail(orbiterTrailLength));
      };
      b.data = new OrbiterBulletData(true, trails, oX, oY);
    }
    super.init(b);
  };
  
  @Override
  public void update(Bullet b){
    if(!(b.data instanceof OrbiterBulletData d)) return;
    float angle = (360f / orbiters);
    for(int i = 0; i < d.trails.size; i++){
      d.oX.set(i, b.x + Angles.trnsx(angle * i + (orbitSpeed * Time.time), orbitRadius));
      d.oY.set(i, b.y + Angles.trnsy(angle * i + (orbitSpeed * Time.time), orbitRadius));
      if(orbiterTrailEffect != Fx.none && orbiterTrailEffect != null){
        if(Mathf.chanceDelta(1)){
          orbiterTrailEffect.at(d.oX.get(i), d.oY.get(i), trailParam, trailColor);
        };
      }else{
        d.trails.get(i).update(d.oX.get(i), d.oY.get(i));
      }
    };
    
    if(b.timer(0, orbiterST)){
      for(int i = 0; i < d.trails.size; i++){
        orbiter.create(b, d.oX.get(i), d.oY.get(i), b.rotation());
      };
    };
    super.update(b);
  };

  @Override
  public void removed(Bullet b){
    if(trailLength > 0 && b.trail != null && b.trail.size() > 0){
      Fx.trailFade.at(b.x, b.y, trailWidth, trailColor, b.trail.copy());
    }
  }

  @Override
  public void draw(Bullet b){
    if(!(b.data instanceof OrbiterBulletData d)) return;
    for(int i = 0; i < d.trails.size; i++){
      if(!(trailEffect != Fx.none && trailEffect != null)){
        d.trails.get(i).draw(backColor, orbiterTrailWidth * b.fout());
      }
      Draw.color(backColor);
      Fill.circle(d.oX.get(i), d.oY.get(i), orbiterRadius * b.fout());
    }
    super.draw(b);
  }
  
  public static class OrbiterBulletData extends OverseerBulletType.OverseerBulletData{
    public Seq<Trail> trails;
    public FloatSeq oX, oY;
    
    public OrbiterBulletData(boolean home, Seq<Trail> trails, FloatSeq oX, FloatSeq oY){
      super(home);
      this.oX = oX;
      this.oY = oY;
      this.trails = trails;
    }
  }
}
