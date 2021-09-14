package heav.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import heav.util.*;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class HMFx {
	private static final Rand rand = new Rand();
  private static final Vec2 v1 = new Vec2(), v2 = new Vec2();

  public static final Effect
	
  fakeLightning = new Effect(10f, 500f, e -> {
    if(!(e.data instanceof LightningData d)) return;
    float tx = d.pos.getX(), ty = d.pos.getY(), dst = Mathf.dst(e.x, e.y, tx, ty);
    v1.set(d.pos).sub(e.x, e.y).nor();

    float normx = v1.x, normy = v1.y;
    float range = 6f;
    int links = Mathf.ceil(dst / range);
    float spacing = dst / links;

    Lines.stroke(d.stroke * e.fout());
    Draw.color(Color.white, e.color, e.fin());

    Lines.beginLine();

    Lines.linePoint(e.x, e.y);

    rand.setSeed(e.id);

    for(int i = 0; i < links; i++){
      float nx, ny;
      if(i == links - 1){
        nx = tx;
        ny = ty;
      }else{
        float len = (i + 1) * spacing;
        v1.setToRandomDirection(rand).scl(range/2f);
        nx = e.x + normx * len + v1.x;
        ny = e.y + normy * len + v1.y;
      }

      Lines.linePoint(nx, ny);
    }

    Lines.endLine();
  }).followParent(false).layer(Layer.bullet + 0.01f),
    
    //[length, width, team]
  fakeLightningFast = new Effect(5f, 500f, e -> {
    Object[] data = (Object[])e.data;

    float length = (float)data[0];
    int tileLength = Mathf.round(length / tilesize);
        
    Lines.stroke((float)data[1] * e.fout());
    Draw.color(e.color, Color.white, e.fin());
        
    for(int i = 0; i < tileLength; i++){
      float offsetXA = i == 0 ? 0f : Mathf.randomSeed(e.id + (i * 6413), -4.5f, 4.5f);
      float offsetYA = (length / tileLength) * i;
            
      int f = i + 1;
            
      float offsetXB = f == tileLength ? 0f : Mathf.randomSeed(e.id + (f * 6413), -4.5f, 4.5f);
      float offsetYB = (length / tileLength) * f;
            
      v1.trns(e.rotation, offsetYA, offsetXA);
      v1.add(e.x, e.y);
            
      v2.trns(e.rotation, offsetYB, offsetXB);
      v2.add(e.x, e.y);
            
      Lines.line(v1.x, v1.y, v2.x, v2.y, false);
      Fill.circle(v1.x, v1.y, Lines.getStroke() / 2f);
      Drawf.light((Team)data[2], v1.x, v1.y, v2.x, v2.y, (float)data[1] * 3f, e.color, 0.4f);
    }
    Fill.circle(v2.x, v2.y, Lines.getStroke() / 2);
  }).layer(Layer.bullet + 0.01f),
	
	flameBurst = new Effect(40, e -> {
	  HMDraw.splashCircle(e.x, e.y, e.fout() * 2.5f, Pal.lightPyraFlame, Pal.darkFlame, e.fin(), e.id, 3, 2f + e.fin() * 9f, e.rotation, 360f);
	});
	
	public static class LightningData{
    Position pos;
    float stroke;

    public LightningData(Position pos, float stroke){
      this.pos = pos;
      this.stroke = stroke;
    };
  };
};