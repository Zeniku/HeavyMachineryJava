package heav.world.blocks.defense;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import heav.util.*;

import static mindustry.Vars.*;

public class StatusEffectProjector extends Block{
	
	public Color starColor = Pal.lightPyraFlame;
	public float reloadTime = 60f * 3.5f;
  public float range = 160f;
  public float healPercent = 5f;
  public float damage = 20f;
  public StatusEffect allyStatus = StatusEffects.none;
  public StatusEffect enemiesStatus = StatusEffects.burning;
  public Effect statusFxAlly = Fx.none;
  public Effect statusFxEnemies = Fx.none;
  public Effect healEffect = Fx.none;
  public boolean enableAFxAura = false;
  public boolean enableEFxAura = true;
  public boolean enableHealing = true;
  
  public StatusEffectProjector(String name){
    super(name);
    solid = true;
    update = true;
    group = BlockGroup.projectors;
    hasPower = true;
  };
	
  @Override
	public void drawPlace(int x, int y, int rotation, boolean valid){
		super.drawPlace(x, y, rotation, valid);
	  Drawf.dashCircle(x * tilesize, y * tilesize, range, Pal.accent);
	};
	
	@Override
  public void setStats(){
    super.setStats();
    stats.add(Stat.range, range);
    stats.add(Stat.reload, reloadTime / 60f, StatUnit.perSecond);
  };
	
  public class StatusEffectProjectorBuild extends Building implements Ranged{
    protected float eTime = 0f;
    protected float aTime = 0f;
    protected boolean wasHealed, appliedEnemies, appliedAlly = false;
		
    @Override
    public float range(){
      return range;
    }
    
    @Override
    public void updateTile(){
      if(consumeTriggerValid()){
			  eTime = Math.min(eTime + edelta(), reloadTime * 0.25f);
			  aTime = Math.min(aTime + edelta(), reloadTime);
			  if(aTime >= reloadTime){
					wasHealed = false; 
					appliedAlly = false;

			    Units.nearby(team, x, y, range(), a -> {
			      if(enableHealing){
			        if(a.damaged()){
			          a.heal(healPercent);
			          Fx.heal.at(a);
			          wasHealed = true;
			        };
			      };
			      if(allyStatus != StatusEffects.none) {
			        if(enableAFxAura){
			          if(statusFxAlly != Fx.none){
			            statusFxAlly.at(a);
			          };
			        };
			        a.apply(allyStatus, 60f);
			        appliedAlly = true;
			      };
			    });
			    aTime = 0f;

					if(wasHealed){
						if(healEffect != Fx.none){
							healEffect.at(x, y, range, Pal.heal);
						};
					};
			  }
			  if(eTime >= reloadTime * 0.25f){
					appliedEnemies = false;

			    HMFunc.radiusEnemies(team, x, y, range(), e -> {
			      e.apply(enemiesStatus, 60f);
			      if(statusFxEnemies != Fx.none){
			        if(enableEFxAura){
			          statusFxEnemies.at(e);
			        };
			      };
			      e.damage(damage);
			      appliedEnemies = true;
			    });
			    eTime = 0f;
			  };

			  HMFunc.checkEffect(range(), this, appliedEnemies, enableEFxAura, statusFxEnemies, 5f);
			  HMFunc.checkEffect(range(), this, appliedAlly, enableAFxAura, statusFxAlly, 5f);
			};
    };
		
    @Override
		public void draw(){
			super.draw();
			if(consumeTriggerValid()){
			  Draw.z(Layer.effect - 0.01f);
				HMDraw.spike(x, y, starColor, 2f * 2.9f + Mathf.absin(Time.time, 5f, 1f) + Mathf.random(0.1f),  2f * Time.time);
				HMDraw.spike(x, y, Color.white, 2f * 1.9f + Mathf.absin(Time.time, 5f, 1f) + Mathf.random(0.1f),  2f * Time.time);
			};
		}
		
		@Override
		public void drawSelect(){
			Drawf.dashCircle(x, y, range(), Pal.accent);
		}
  }
}
