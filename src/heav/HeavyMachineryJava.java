package heav;

import arc.*;
import arc.util.*;
import mindustry.ctype.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import heav.content.*;
import static mindustry.Vars.*;

public class HeavyMachineryJava extends Mod{

  public HeavyMachineryJava(){

    //listen for game load event
    Events.on(ClientLoadEvent.class, e -> { 
      //show dialog upon startup
      Time.runTask(10f, () -> {
        
          BaseDialog dialog = new BaseDialog("frog");
            dialog.cont.add("behold").row();
            //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
            dialog.cont.image(Core.atlas.find("heavymachineryjava-frog")).pad(20f).row();
            dialog.cont.button("I see", dialog::hide).size(100f, 50f);
            dialog.show();
      });
    });
  }

  @Override
  public void init(){
    if(!headless){
      enableConsole = true;
    }
  }

  @Override
  public void loadContent(){
    HMBullets.load();
    HMItems.load();
    HMUnits.load();
    HMBlocks.load();
  }
}
