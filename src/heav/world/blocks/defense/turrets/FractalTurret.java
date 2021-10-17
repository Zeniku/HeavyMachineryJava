package heav.world.blocks.defense.turrets;

import arc.*;
import arc.util.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.*;

public class FractalTurret extends DisabledPredictTurret{
  
  public FractalTurret(String name){
    super(name);
  }
  
  public class FractalTurretBuild extends DisabledPredictTurretBuild{
    
    @Override
    protected void bullet(BulletType type, float angle){
      var tp = (Vec2)targetPos;
      
      if(Mathf.dst(x + tr.x, y + tr.y, tp.x, tp.y) > range){
          Tmp.v1.set(tp).sub(x, y).clamp(-range, range).add(x, y);
      }else{
          Tmp.v1.set(tp);
      }
      
      float rx = Tmp.v1.x + Angles.trnsx(Mathf.random(360), Mathf.random(range));
      float ry = Tmp.v1.y + Angles.trnsy(Mathf.random(360), Mathf.random(range));
      
      float ang = Angles.angle(rx, ry, tp.x, tp.y);
      
      float lifeScl = type.scaleVelocity ? Mathf.clamp(Mathf.dst(rx, ry, tp.x, tp.y) / type.range(), minRange / type.range(), range / type.range()) : 1f;

      type.create(this, team, rx, ry, ang, 1f + Mathf.range(velocityInaccuracy), lifeScl);
    }
  }
}
