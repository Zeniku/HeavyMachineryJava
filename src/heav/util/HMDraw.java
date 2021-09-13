package heav.util;

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

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class HMDraw {
  public void spike(float x, float y, int spikes, float size, float lengthMultiplier, float rotation){
    float step = 360f / spikes;
    for(int i = 0; i < spikes; i++){
      Drawf.tri(x, y, size, (size * lengthMultiplier), i * step + rotation);
    };
  }
  public void spikeii(float x,float y, Color color, float size, float rotation) {
    Draw.color(color);
    spike(x, y, 4, size, 2f, rotation);
    Draw.color();
  }
  public void spikeiii(float x,float y, Color colorFrom, Color colorTo, float inOut, float size, float rotation) {
    Draw.color(colorFrom, colorTo, inOut);
    spike(x, y, 4, size, 2f, rotation);
    Draw.color();
	}
}