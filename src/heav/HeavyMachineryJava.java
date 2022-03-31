package heav;

import mindustry.mod.*;
import heav.content.*;
import static mindustry.Vars.*;

public class HeavyMachineryJava extends Mod{

  public HeavyMachineryJava(){

  }

  @Override
  public void init(){
    if(!headless){
      enableConsole = true;
    }
  }

  @Override
  public void loadContent(){
    HMItems.load();
    HMStatusEffects.load();
    HMBullets.load();
    HMUnits.load();
    HMBlocks.load();
    HMPlanets.load();
    HMSectorPresents.load();
    HMTechTree.load();
  }
}
