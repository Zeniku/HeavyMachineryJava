package heav.entities.bullet;

import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.graphics.*;
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
  public float orbitRadius = 3f;
  public float orbiterST = 15f;
  public float orbiterRadius = 4f;
  public float orbiterTrailWidth = 10f;
  public Effect orbiterTrailEffect = Fx.none;
  public BulletType orbiter = Bullets.standardCopper;
  
  public OrbiterBulletType(float speed, float damage, String sprite){
    super(speed, damage, sprite);
  };
  
  public OrbiterBulletType(float speed, float damage){
    this(speed, damage, "bullet");
  };
  
  @Override
  public void init(Bullet b){
    if(b.data == null){
      b.data = new OrbiterBulletData(trailLength, orbiters, orbiterTrailLength, b);
    }
    super.init(b);
  };
  
  @Override
  public void update(Bullet b){
    if(!(b.data instanceof OrbiterBulletData d)) return;
    float angle = (360f / orbiters);
    FloatSeq oX = d.oX;
    FloatSeq oY = d.oY;
    for(int i = 0; i < d.trails.size; i++){
      oX.set(i, b.x + Angles.trnsx(angle * i + (orbitSpeed * Time.time), orbitRadius));
      oY.set(i, b.y + Angles.trnsy(angle * i + (orbitSpeed * Time.time), orbitRadius));
      if(orbiterTrailEffect != Fx.none && orbiterTrailEffect != null){
        if(Mathf.chanceDelta(1)){
          orbiterTrailEffect.at(oX.get(i), oY.get(i), trailParam, trailColor);
        };
      }else{
        d.trails.get(i).update(oX.get(i), oY.get(i));
      }
    };
    
    if(b.timer(0, orbiterST)){
      for(int i = 0; i < d.trails.size; i++){
        orbiter.create(b, oX.get(i), oY.get(i), b.rotation());
      };
    };
    super.update(b);
  };
  
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
    public Seq<Trail> trails = new Seq();
    public FloatSeq oX = new FloatSeq(), oY = new FloatSeq();
    
    public OrbiterBulletData(Trail mainTrail, int trails, int trailLength, float x, float y){
      super(mainTrail);
      for(int i = 0; i < trails; i++){
        this.trails.add(new Trail(trailLength));
        this.oX.add(x);
        this.oY.add(y);
      }
    }
    
    public OrbiterBulletData(int mainTrailLength, int trails, int trailLength, Position pos){
      this(new Trail(mainTrailLength), trails, trailLength, pos.getX(), pos.getY());
    }
  }
}
