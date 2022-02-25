package heav.content;

import heav.type.ShieldStatusEffect;
import mindustry.type.StatusEffect;

public class HMStatusEffects {
	public static StatusEffect 
		armored, shieldBreak;

	public static void load(){
		armored = new ShieldStatusEffect("sheilded"){{
			shieldAmount = 15f;
			maxShield = 240f;
		}};
		shieldBreak = new ShieldStatusEffect("sheildBreak"){{
			shieldAmount = 15f;
			maxShield = 240f;
			remove = true;
		}};
	}
}
