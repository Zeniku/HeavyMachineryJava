package heav.util;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
public class HMDraw {
  public static void spike(float x,float y, Color color, float size, float rotation) {
    color(color);
    spike(x, y, 4, size, 2f, rotation);
    color();
  }
  public static void spike(float x,float y, Color colorFrom, Color colorTo, float inOut, float size, float rotation) {
    color(colorFrom, colorTo, inOut);
    spike(x, y, 4, size, 2f, rotation);
    color();
	}
	public static void spike(float x, float y, int spikes, float size, float lengthMultiplier, float rotation){
    float step = 360f / spikes;
    for(int i = 0; i < spikes; i++){
      Drawf.tri(x, y, size, (size * lengthMultiplier), i * step + rotation);
    };
  }
	public static void splashLine(float x, float y, Color colorFrom, Color colorTo, float inOut, float thickness, float length, long id, int amount, float distance, float rotation, float cone){
    color(colorFrom, colorTo, inOut);
    splashLine(x, y, thickness, length, id, amount, distance, rotation, cone);
  }
	public static void splashLine(float x, float y, Color color, float thickness, float length, long id, int amount, float distance, float rotation, float cone){
    color(color);
    splashLine(x, y, thickness, length, id, amount, distance, rotation, cone);
  }
	public static void splashLine(float x, float y, float thickness, float length, long id, int amount, float distance, float rotation, float cone){
    randLenVectors(id, amount, distance, rotation, cone, (a, b) -> {
      float ang = Mathf.angle(a, b);
      stroke(thickness);
      lineAngle(x + a, y + b, ang, length);
      stroke(1);
    });
  }
  public static void lineCircle(float x, float y, Color color, float thickness, float radius){
    color(color);
    lineCircle(x, y, thickness, radius);
  }
  public static void lineCircle(float x, float y, Color colorFrom, Color colorTo, float inOut,float thickness, float radius){
    color(colorFrom, colorTo, inOut);
    lineCircle(x, y, thickness, radius);
  } 
  public static void lineCircle(float x, float y, float thickness, float radius){
    Lines.stroke(thickness);
    Lines.circle(x, y, radius);
    Lines.stroke(1);
  }
  public static void splashCircle(float x, float y, float radius, Color colorFrom, Color colorTo, float inOut, long id, int amount, float distance, float rotation, float cone){
    color(colorFrom, colorTo, inOut);
    splashCircle(x, y, radius, id, amount, distance, rotation, cone);
  }
  public static void splashCircle(float x, float y, float radius, Color color, long id, int amount, float distance, float rotation, float cone){
    color(color);
    splashCircle(x, y, radius, id, amount, distance, rotation, cone);
  }
  public static void splashCircle(float x, float y, float radius, long id, int amount, float distance, float rotation, float cone){
    randLenVectors(id, amount, distance, rotation, cone, (a, b) -> {
      Fill.circle(x + a, y + b, radius);
    });
  }
}
