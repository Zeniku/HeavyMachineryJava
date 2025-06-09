package heav.ai.types;

import mindustry.ai.types.*;

public class DisabledPredictAi extends GroundAI {
  @Override
  public void updateMovement(){
    super.updateMovement();
    boolean shoot = false;
    if(target != null && unit.inRange(target)){
      unit.aimLook(target);
      shoot = true;
    }
    unit.controlWeapons(shoot);
  }
}
