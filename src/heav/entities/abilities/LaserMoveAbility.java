package heav.entities.abilities;

import arc.math.*;
import mindustry.gen.*;
import mindustry.content.Bullets;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;

public class LaserMoveAbility extends Ability {
  protected Bullet bullet = null;
  public BulletType laser = Bullets.standardCopper;
  public float offsetX = 0f;
  public float offsetY = 0f;
  public float min = 2f;
  public float max = 5f;

  public LaserMoveAbility(BulletType laser, float offsetX, float offsetY, float min, float max){
    this.laser = laser;
    this.offsetX = offsetX;
    this.min = min;
    this.max = max;
  }

  @Override
  public void update(Unit unit){
    float scl = Mathf.clamp((unit.vel.len() - min) / (max - min));
    float bx = unit.x + Angles.trnsx(unit.rotation, offsetX);
    float by = unit.y + Angles.trnsy(unit.rotation, offsetX);
    if(bullet != null){
      bullet.set(bx, by);
      bullet.rotation(unit.rotation);
      bullet.time = 0f;
    }
    if(scl >= 0.5f){
      if(bullet == null){
       bullet = laser.create(unit, bx, by, unit.rotation);
      }
    }else{
      bullet = null;
    }
    super.update(unit);
  }
}
