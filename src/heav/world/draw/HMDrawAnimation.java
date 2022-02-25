package heav.world.draw;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawBlock;

public class HMDrawAnimation extends DrawBlock{
  public int frameCount = 3;
  public float frameSpeed = 5f;
  public boolean sine = true;
  public TextureRegion[] frames;

  @Override
  public void draw(GenericCrafter.GenericCrafterBuild build){
    Draw.rect(build.block.region, build.x, build.y);
    Draw.rect(
      sine ?
        frames[(int)Mathf.absin(build.totalProgress, frameSpeed, frameCount - 0.001f)] :
        frames[(int)((build.totalProgress / frameSpeed) % frameCount)],
      build.x, build.y);
  };

  @Override
  public void load(Block block){
    frames = new TextureRegion[frameCount];
    for(int i = 0; i < frameCount; i++){
      frames[i] = Core.atlas.find(block.name + "-frame" + i);
    }
  }

  @Override
  public TextureRegion[] icons(Block block){
    return new TextureRegion[]{block.region, frames[0]};
  } 
}
