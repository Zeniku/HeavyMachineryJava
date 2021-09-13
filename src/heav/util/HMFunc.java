package heav.util;

import arc.math.*;
import arc.func.*;
import mindustry.game.*;
import mindustry.entities.*;
import mindustry.content.*;
import mindustry.gen.*;

import static mindustry.Vars.*;

public class HMFunc{
	public static void radiusEnemies(Team team, float x, float y, float radius, Cons<Unit> cons){
    Units.nearbyEnemies(team, x - radius, y - radius, radius * 2f, radius * 2f, unit -> {
      if(unit.within(x, y, radius + unit.hitSize / 2f)){
        cons.get(unit);
      };
    });
	};
	public static void checkEffect(float range, Position pos, boolean condition, boolean aura, Effect effect, float amount){
		if(condition){
	    if(aura){
		    for(int i = 0; i < amount; i++){
					if(effect != Fx.none){
					  effect.at(pos.getX() + Angles.trnsx(Mathf.random(360), Mathf.random(range)), pos.getY() + Angles.trnsy(Mathf.random(360), Mathf.random(range)));
					};
		    };
		  }else{
		  	if(effect != Fx.none){
		      effect.at(pos.getX(), pos.getY());
		    };
	    };
	  };
	};
};
