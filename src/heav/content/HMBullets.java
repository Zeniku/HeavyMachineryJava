package heav.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.io.*;
import mindustry.world.*;
import heav.entities.bullet.*;

import static mindustry.Vars.*;

public class HMBullets implements ContentList{
	public static BulletType
	
	standardOverseer, mediumOverseer, highOverseer;

	@Override
	public void load(){
		standardOverseer = new OverseerBulletType(3f, 18){{
			width = 7f;
      height = 9f;
			homingPower = 0.08f;
      reloadMultiplier = 1.5f;
      ammoMultiplier = 5;
			lifetime = 60f * 2f;
		}};
    mediumOverseer = new OverseerBulletType(3f, 28){{
			width = 9f;
      height = 11f;
			homingPower = 0.08f;
      reloadMultiplier = 2f;
      ammoMultiplier = 5;
			lifetime = 60f * 2f;
		}};
		highOverseer = new OverseerBulletType(3f, 49){{
			width = 11f;
      height = 13f;
			homingPower = 0.1f;
      reloadMultiplier = 4f;
      ammoMultiplier = 7;
			lifetime = 60f * 4f;
		}};
	}
}
