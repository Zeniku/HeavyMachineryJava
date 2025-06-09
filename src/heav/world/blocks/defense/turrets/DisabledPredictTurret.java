package heav.world.blocks.defense.turrets;

import mindustry.gen.*;
import mindustry.world.blocks.defense.turrets.*;

public class DisabledPredictTurret extends PowerTurret{
  
  public DisabledPredictTurret(String name){
    super(name);
  }
  
  public class DisabledPredictTurretBuild extends PowerTurretBuild{
    
    @Override
    public void targetPosition(Posc pos){
      if(!hasAmmo() || pos == null) return;
      
      targetPos.set(pos);
      if(targetPos.isZero()){
        targetPos.set(pos);
      }
    }
  }
}
