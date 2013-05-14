package se.androidsquad.coloristance;

/**
 * This class is supposed to describe the different levels that are included in the game
 * 				Dark		Light
 * 1=Blue 		#0099CC		#33B5E5
 * 2=Green 		#669900		#99CC00
 * 3=Orange 	#FF8800		#FFBB33	
 * 4=Purple 	#9933CC		#AA66CC
 * 5=Red  		#CC0000		#FF4444
 * 0=Nothing -> there is no door and no connection
 * 
 * These colors are predefined. The naming is in format:  color.holo_green_light
 * 
 * The colors have corresponding keys, with the same names and colors. 
 * 
 * A room is defined by five figures that represent; The room (R) and its four connections in order up 
 * (N), right (E), down (S), left (W).
 * An example of the RNESW is: 13500 = A Blue room with a Orange door top and a red door to the right.
 * The first figure must be a value since it represent the room's color.
 */ 
	 
 //"5=Connecting path between two rooms, under construction." <- L�gg inte denna h�r, har position 2-5 en siffra finns en d�rr och connection


public class Levels {


public static String[][] Level1 = {{"12300","20410","32320","12110"},
									{"34001","40032","40132","23101"}};

}



