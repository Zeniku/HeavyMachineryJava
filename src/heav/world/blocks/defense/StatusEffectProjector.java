package heav.world.blocks.defense;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.annotations.Annotations.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class StatusEffectProjector extends Block{
  public float reloadTime = 60;
  public float range = 160;
  public boolean enableHealing = true;
  public StatusEffect allyStatus = StatusEffects.none;
  
  public StatusEffectProjector(String name){
    super(name);
    solid = true;
    update = true;
    group = BlockGroup.projectors;
    hasPower = true;
  }
  
  public class StatusEffectProjectorBuild extends Building implements Ranged{
    float eTime = 0;
    float aTime = 0;
    
    @Override
    public float range(){
      return range;
    }
    
    @Override
    public void updateTile(){
      if(consValid()){
			  boolean wasHealed = false;
			  boolean appliedEnemies = false;
			  boolean appliedAlly = false;
			  eTime = Math.min(eTime + edelta(), reloadTime * 0.25);
			  aTime = Math.min(aTime + edelta(), reloadTime);
			  if(aTime >= reloadTime){
			    Units.nearby(team, x, y, range, a -> {
			      if(enableHealing){
			        if(a.damaged()) {
			          a.heal(custom.healPercent)
			          Fx.heal.at(a)
			          wasHealed = true
			        }
			      }
			      if(allyStatus != StatusEffects.none) {
			        if (custom.enableAFxAura) {
			          if(custom.statusFxAlly != Fx.none){
			            custom.statusFxAlly.at(a)
			          }
			        }
			        a.apply(custom.allyStatus, 60)
			        appliedAlly = true
			      }
			    })
			    this.atimer = 0
			  }
			  if(this.etimer >= custom.reload * 0.25){
			    flib.radiusEnemies(this.team, this.x, this.y, custom.range, e => {
			      e.apply(custom.enemiesStatus, 60);
			      if(custom.statusFxEnemies != Fx.none){
			        if(custom.enableEFxAura){
			          custom.statusFxEnemies.at(e)
			        }
			      }
			      e.damage(custom.damage)
			      appliedEnemies = true;
			    });
			    this.etimer = 0
			  }
			  flib.checkEffect(custom, this, (appliedEnemies), custom.enableEFxAura, custom.statusFxEnemies, 5)
			  flib.checkEffect(custom, this, (appliedAlly), custom.enableAFxAura, custom.statusFxAlly, 5)
			  
				if(wasHealed){
				  if(custom.healEffect != Fx.none){
				    custom.healEffect.at(this.x, this.y)
				  }
				}
			};
    }
  }
}