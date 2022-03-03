package heav.world.blocks.defense;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import heav.util.*;
import heav.content.*;


import static mindustry.Vars.*;
import heav.content.HMFx.LightningData;

public class Tesla extends Block{
	public float range = tilesize * 20f;
  public float reloadTime = 40f;
  public float damage = 20f;
	public int lightningCount = 3;
  public int lightningLength = 10;
  public int lightningLengthRand = 16;
  public Color lightningColor = Pal.lancerLaser;
  public Effect hitEffect = Fx.none;
	
	public Tesla(String name){
		super(name);
    solid = true;
    update = true;
    group = BlockGroup.projectors;
    hasPower = true;
	}

  @Override
	public void drawPlace(int x, int y, int rotation, boolean valid){
		super.drawPlace(x, y, rotation, valid);
	  Drawf.dashCircle(x * tilesize, y * tilesize, range, lightningColor);
	};

	@Override
  public void setStats(){
    super.setStats();
    stats.add(Stat.range, range);
    stats.add(Stat.reload, reloadTime / 60f, StatUnit.perSecond);
  };

	public class TeslaBuild extends Building implements Ranged{
		protected float rTime = 0f;
		
		@Override
		public float range(){
			return range;
		}
		
		@Override
		public void updateTile(){
			if(consValid()){
        rTime = Math.min(rTime + edelta(), reloadTime);
        if(rTime >= reloadTime){
          HMFunc.radiusEnemies(team, x, y, range(), u -> {
            u.damage(damage);
            float ang = Angles.angle(x, y, u.x, u.y);
						if(hitEffect != Fx.none) hitEffect.at(u, ang);

            HMFx.fakeLightning.at(x, y, ang, lightningColor, new LightningData(u, 4f)); 
            if(Mathf.chance(25f)){
              for(int i = 0; i < lightningCount; i++){
                Lightning.create(team, lightningColor, damage * 0.5f, x, y, Mathf.random(0f, 359f), Mathf.random(lightningLength, lightningLengthRand));
              }
            }
          });
          rTime = 0f;
        }
      }
		}
		
		@Override
		public void draw(){
			super.draw();
      if(consValid()){
        Draw.z(Layer.bullet + 0.01f);
        Draw.color(lightningColor);
        Fill.circle(x, y, 2f * 1.9f + Mathf.absin(Time.time, 5f, 1f) + Mathf.random(0.1f));
      }
    }
		
		@Override
		public void drawSelect(){
			Drawf.dashCircle(x, y, range(), lightningColor);
		}
	}
}
