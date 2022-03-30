package heav.maps;

import arc.struct.ObjectSet;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Tmp;
import mindustry.ai.Astar;
import mindustry.maps.planet.SerpuloPlanetGenerator;

public class CaeruleumPlanetGenerator extends SerpuloPlanetGenerator{
  @Override
  protected void generate() {
    
    class Room {
      int x, y, radius;
      ObjectSet<Room> connected = new ObjectSet<>();

      public Room(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        connected.add(this);
      }

      void connect(Room to){
        if(connected.contains(to)) return;
        connected.add(to);
        float nscl = rand.random(20f, 60f);
        int stroke = rand.random(4, 12);
        brush(pathfind(x, y, to.x, to.y, tile -> (tile.solid() ? 5 : 0) + noise(tile.x, tile.y, 1, 1, 1f / nscl) * 60, Astar.manhattan), stroke);
      }
    }

    cells(4);
    distort(10f, 12f);

    float constraint = 1.3f;
    float radius = width / 2f / Mathf.sqrt3;
    int rooms = rand.random(2, 5);
    Seq<Room> roomseq = new Seq<Room>();

    for(int i = 0; i < rooms; i++){
      Tmp.v1.trns(rand.random(360f), rand.random(radius / constraint));
      float rx = (width/2f + Tmp.v1.x);
      float ry = (height/2f + Tmp.v1.y);
      float maxrad = radius - Tmp.v1.len();
      float rrad = Math.min(rand.random(9f, maxrad / 2f), 30f);
      roomseq.add(new Room((int)rx, (int)ry, (int)rrad));
    }

    Room spawn = null;
    Seq<Room> enemies = new Seq<Room>();
    int enemySpawns = rand.random(1, Math.max((int)(sector.threat * 4), 1));
    int offset = rand.nextInt(360);
    float length = width / 2.55f - rand.random(13, 23);
    int angleStep = 5;
    int waterCheckRad = 5;

    //TODO the rest is nkt finished because im ganna rest
  }
}
