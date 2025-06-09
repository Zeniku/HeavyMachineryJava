package heav;

import mindustry.mod.*;
import heav.content.*;

public class HeavyMachineryJava extends Mod{

  public HeavyMachineryJava(){

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
