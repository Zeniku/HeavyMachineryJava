package heav.world.blocks.defense.turrets;

import arc.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;
import mindustry.world.meta.values.*;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.*;

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