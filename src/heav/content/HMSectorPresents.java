package heav.content;

import mindustry.type.SectorPreset;

public class HMSectorPresents {
	public static SectorPreset generated;
	public static void load() {
		generated = new SectorPreset("generated", HMPlanets.caeruleum, 10){{
			alwaysUnlocked = true;
			difficulty = 2f;
			captureWave = 20;
			addStartingItems = true;
		}};
	}
}
