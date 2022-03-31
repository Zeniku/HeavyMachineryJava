package heav.content;

import mindustry.type.Planet;
import mindustry.content.Planets;
import mindustry.graphics.g3d.*;
import arc.graphics.Color;
import heav.maps.*;

public class HMPlanets {
	public static Planet caeruleum;

  public static void load() {
		caeruleum = new Planet("caeruleum", Planets.sun, 3f, 3){{
			generator = new CaeruleumPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 6);
			alwaysUnlocked = true;
      cloudMeshLoader = () -> new MultiMesh(
				new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Color.valueOf("363f9a")).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
				new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Color.valueOf("363f9a"), 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
			);
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			atmosphereColor = Color.valueOf("363f9a");
			landCloudColor = atmosphereColor.cpy().a(0.5f);
			startSector = 10;
		}};
	}
}
