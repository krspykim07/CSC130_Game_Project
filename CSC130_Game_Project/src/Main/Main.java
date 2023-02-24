
// Vigomar Kim Algador
// CSC130-03
// Game Project

package Main;

import java.awt.Color;
import java.util.ArrayList;
import FileIO.EZFileRead;
import java.util.HashMap;
import java.util.StringTokenizer;

import Data.Vector2D;
import Data.spriteInfo;
import Data.boundingBox;

import logic.Control;

public class Main{
	// Fields (Static) below...
	public static Vector2D currentVec = new Vector2D (100, 100);	// hold the current position of the sprite
	public static Vector2D lastVec = new Vector2D (0, 0); 			// hold the last position of the sprite
	
	/* spriteInfo field */
	public static spriteInfo IUSprite = new spriteInfo (currentVec, "IUFront01");				// current sprite info
	public static spriteInfo prevSprite = new spriteInfo (lastVec, IUSprite.getTag());			// previous sprite info (use for bounding)
	public static spriteInfo GoldenKey = new spriteInfo (new Vector2D(-200, -200),"GoldenKey");	// Golden Key sprite
	public static spriteInfo dialogueBox = new spriteInfo (new Vector2D(290, 500),"blank");		// Dialogue Box
	public static spriteInfo gateWall = new spriteInfo (new Vector2D(360,228),"gateClose");
	public static spriteInfo house = new spriteInfo (new Vector2D(650, 100), "house");
	public static spriteInfo houseBack = new spriteInfo (new Vector2D(650, 100), "HouseTest");
	
	/* Bounding fields */
	public static boundingBox IUBound;
	public static boundingBox grassBound1 = new boundingBox(250, 330, 50, 100);
	public static boundingBox grasssearch1 = new boundingBox (200, 350, 20, 120);
	public static boundingBox grassBound2 = new boundingBox(250, 330, 550, 600);
	public static boundingBox grasssearch2 = new boundingBox (200, 350, 530, 620);
	public static boundingBox gateBound = new boundingBox(370, 388, 224, 450);
	public static boundingBox gateInteract = new boundingBox (350, 400, 224, 450);
	//public static boundingBox houseBound = new boundingBox (650, 1010, 100, 360);
	public static boundingBox houseBound = new boundingBox (650, 1010, 250, 360);
	public static boundingBox doorInteract = new boundingBox (890, 970, 240, 370);
	
	public static ArrayList <spriteInfo> sprites = new ArrayList<>();	// holds the sprite
	public static ArrayList <boundingBox> boundBox = new ArrayList<>(); // holds the bounding box
	
	public static EZFileRead ezr = new EZFileRead("Dialogue.txt");
	public static HashMap<String, String> map = new HashMap<>();
	
	public static String trigger = "";
	
	public static int GoldenKeyNum = 0;
	
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		/* Store the bounding boxex*/
		boundBox.add(new boundingBox(-128, 350, 0, 40));  		// Top Tree boundary
		boundBox.add(new boundingBox(-128, 350, 650, 848));		// Bottom boundary
		boundBox.add(new boundingBox(-128, 80, -128, 848));		// Left Tree boundary
		
		boundBox.add(new boundingBox(400, 1408, 0, 40));  		// Top wall boundary
		boundBox.add(new boundingBox(400, 1408, 680, 720));  	// Bottom wall boundary
		boundBox.add(new boundingBox(1240, 1408, -128, 848));	// Right wall boundary
		boundBox.add(new boundingBox(340, 400, 40, 310));		// top-left wall
		boundBox.add(new boundingBox(350, 400, 450, 848));		// bottom-left wall
		boundBox.add(grassBound1);
		boundBox.add(grassBound2);
		boundBox.add(gateBound);
		boundBox.add(houseBound);
		
		
		/* Store the sprites */
		sprites.add(new spriteInfo(new Vector2D(0,0), "Background"));
		
		sprites.add(new spriteInfo(new Vector2D(240, 50), "Grass1"));
		sprites.add(new spriteInfo(new Vector2D(240, 550), "Grass2"));
		sprites.add(gateWall);
		sprites.add(GoldenKey);
		sprites.add(house);
		sprites.add(IUSprite);
		sprites.add(houseBack);
		sprites.add(dialogueBox);
		
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		
		// update the sprite bounding box in certain position
		IUBound = new boundingBox(IUSprite, 20, 108, 108, 120);  	
		
		// checks for collision for the sprite and any objects or walls
		for (int i = 0; i < boundBox.size(); i++)
			if (checkCollision(IUBound, boundBox.get(i)))
				bouncingSprite(); 
		
		// load all the sprites 
		for (int i = 0; i < sprites.size(); i++) {
			ctrl.addSpriteToFrontBuffer(sprites.get(i).getCoords().getX(), sprites.get(i).getCoords().getY(), sprites.get(i).getTag());
		}
		

		ctrl.drawString(400, 550, trigger, Color.BLACK);
		
	}
	
	// Additional Static methods below...(if needed)
	public static boolean checkCollision(boundingBox box1, boundingBox box2){
		if (((box1.getX1() > box2.getX2()) 
			|| (box1.getX2() < box2.getX1()) 
			|| (box1.getY1() > box2.getY2()) 
			|| (box1.getY2() < box2.getY1())))
			return false;
		else 
			return true;
		}
	
	public static void bouncingSprite(){
		if ((IUSprite.getCoords().getX() - prevSprite.getCoords().getX()) != 0){
			if ((IUSprite.getCoords().getX() - prevSprite.getCoords().getX()) > 0)		// West to East
				IUSprite.getCoords().adjustX(-20);								
			if ((IUSprite.getCoords().getX() - prevSprite.getCoords().getX()) < 0)  	// East to West
				IUSprite.getCoords().adjustX(+20);
		}
		if ((IUSprite.getCoords().getY() - prevSprite.getCoords().getY()) != 0){
			if ((IUSprite.getCoords().getY() - prevSprite.getCoords().getY()) > 0)		// North to South
				IUSprite.getCoords().adjustY(-20);
			if ((IUSprite.getCoords().getY() - prevSprite.getCoords().getY()) < 0)		// South to North
				IUSprite.getCoords().adjustY(+20);
		}
	}

}
