package heav.entities.bullet;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.content.*;

public class RandSpriteBulletType extends BasicBulletType{
	public int variants = 5;
	public float critChance = 0.25f, critMultiplier = 2f;
	public Effect critTrail = Fx.none, spawnFx = Fx.none;

	Seq<TextureRegion> frontRegions = Seq.with(), backRegions = Seq.with();

	public RandSpriteBulletType(float speed, float damage, String sprite){
		super(speed, damage, sprite);
		pierce = true; 
		pierceBuilding = true;
		trailLength = 15;
		trailWidth = 10f;
		ammoMultiplier = 1;  
		frontColor = Color.white;
		backColor = Pal.heal;
		hitColor = Pal.heal;
		shootEffect = Fx.none;
		width = 8f * 3f;
		height = 8f * 3f;
		shrinkX = 0f;
		shrinkY = 0f;
  }

	public RandSpriteBulletType(float speed, float damage){
		this(speed, damage, "bullet");
	}

	@Override
	public void load(){
		super.load();
		if(variants > 0){
			for(int i = 0; i < variants; i++){
				backRegions.add(Core.atlas.find(sprite + "-back-" + i, backRegion));
				frontRegions.add(Core.atlas.find(sprite + "-" + i, frontRegion));
			}
		}else{
			backRegions.add(backRegion);
			frontRegions.add(frontRegion);
		}
	};

	@Override
	public void init(Bullet b){
		if(b.data == null){
			int ind = frontRegions.indexOf(frontRegions.random());
			if(Mathf.chance(critChance)){
				b.data = new RandSpriteBulletData(frontRegions.get(ind), backRegions.get(ind), true);
			}else{
				b.data = new RandSpriteBulletData(frontRegions.get(ind), backRegions.get(ind), false);
			}
			if(((RandSpriteBulletData)b.data).crit) b.damage *= critMultiplier;
		}
		
		if(spawnFx != null || spawnFx != Fx.none){
			spawnFx.at(b);
		}
		super.init(b);
	};

	@Override
	public void update(Bullet b){
		if(!(b.data instanceof RandSpriteBulletData dat)) return;
		super.update(b);
		if(dat.crit && Mathf.chanceDelta(1)){
			if(critTrail != null || critTrail != Fx.none){
				critTrail.at(b);
			}
		}
	};

	@Override
	public void draw(Bullet b){
		if(!(b.data instanceof RandSpriteBulletData dat)) return;
		drawTrail(b);
		float height = this.height * ((1 - shrinkY) + shrinkY * b.fout());
		float width = this.width * ((1 - shrinkX) + shrinkX * b.fout());
		float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360) + b.time * spin : 0);
		
		Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());

		Draw.mixcol(mix, mix.a);

		Draw.color(backColor);
		Draw.rect(dat.spriteBack, b.x, b.y, width, height, b.rotation() + offset);
		Draw.color(frontColor);
		Draw.rect(dat.sprite, b.x, b.y, width, height, b.rotation() + offset);

		Draw.reset();
	};

	public static class RandSpriteBulletData{
		public TextureRegion sprite;
		public TextureRegion spriteBack;
		public boolean crit;

		public RandSpriteBulletData(TextureRegion sprite, TextureRegion spriteBack, boolean crit){
			this.sprite = sprite;
			this.spriteBack = spriteBack;
			this.crit = crit;
		}
	}
}
