package heav.type;

import arc.*;
import arc.func.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.type.*;
import heav.world.meta.*;

import static mindustry.Vars.*;

public class ShieldStatusEffect extends StatusEffect{
  public float shieldAmount = 20f;
  public float maxShield = 250f;

  public ShieldStatusEffect(String name){
    super(name); 
  }

  @Override
  public void setStats(){
    super.setStats();
    stats.add(Stats.shieldHealth, HMStatValues.shieldListVal(this));
  }

  @Override
  public boolean isHidden(){
    return false;
  }

  @Override
  public void update(Unit unit, float time){
    super.update(unit, time);
    if(unit.shield < maxShield){
      if(shieldAmount > 0){
        unit.shield = Math.min(unit.shield + (shieldAmount / 60), maxShield);
      }else if(shieldAmount < 0){
        unit.shield = Math.max(unit.shield - (shieldAmount / 60), 0);
      }
    }
  }
}
