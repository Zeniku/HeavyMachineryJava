package heav.content;

import arc.graphics.*;
import mindustry.ctype.*;
import mindustry.type.*;

public class HMItems implements ContentList{
	public static Item
		lonsdaleite;

	@Override
	public void load(){
		lonsdaleite = new Item("lonsdaleite", Color.valueOf("8b95e0")){{
			cost = 2.5f;
		}};
	}
}
