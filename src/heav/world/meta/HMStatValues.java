package heav.world.meta;

import arc.*;
import arc.scene.ui.layout.*;
import mindustry.world.meta.*;
import heav.type.*;

public class HMStatValues {
   public static StatValue shieldListVal(ShieldStatusEffect status){
     return table -> {
      table.row();
      table.table(s -> {
        s.left().defaults().padRight(3).left();
        sepC(status.maxShield > 0, s, "[lightgray]" + Core.bundle.get("status.maxShield") + ":[] " + status.maxShield);
        sepC(status.shieldAmount > 0, s, "[lightgray]" + Core.bundle.get("status.shieldAmount") + ":[] " + status.shieldAmount + " " + StatUnit.perSecond.localized());
        sepC(status.shieldAmount < 0, s, "[lightgray]" + Core.bundle.get("status.shieldRemoved") + ":[] " + status.shieldAmount + " " + StatUnit.perSecond.localized());
      });
    };
  }
  private static void sepC(boolean condition, Table table, String text){
    if(condition){
      table.row();
      table.add(text);
    };
  }
}
