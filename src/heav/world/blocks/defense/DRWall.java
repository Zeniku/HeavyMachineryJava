package heav.world.blocks.defense;

import arc.graphics.*;
import arc.math.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.defense.*;
import heav.content.*;

public class DRWall extends Wall{
  public float dRChance = 10f;
  public float dRPercentage = 30f;
  public Color hitColor = Pal.lancerLaser;
  
  public DRWall(String name){
    super(name);
  };
  
  public class DRWallBuild extends WallBuild{
    
    @Override
    public boolean collision(Bullet bullet){
      if(dRPercentage > 0 && dRChance > 0){
        HMFx.blockSplash.at(x, y, size, hitColor);
      };
      
      return super.collision(bullet);
    }
    
    @Override
    public float handleDamage(float amount){
      float a = amount;
      if(Mathf.chance(dRChance)){
        a = amount - (amount * (dRPercentage / 100));
      };
      return a;
    };
  };
};
