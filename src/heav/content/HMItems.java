package heav.content;

import arc.graphics.*;
import mindustry.type.*;

public class HMItems {
	public static Item
		lonsdaleite;

	public static void load(){
		lonsdaleite = new Item("lonsdaleite", Color.valueOf("8b95e0")){{
			cost = 2.5f;
		}};
	}
}
