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
 
	 
 //"5=Connecting path between two rooms, under construction." <- Lägg inte denna här, har position 2-5 en siffra finns en dörr och connection
 
 /*
 * ANTON: Why aren't we using the five figure keys as one number "41200"? 
 * Believe that it's easier to make them as strings. String have way better tools to just look at 
 * what the string contain at a position when we aren't going to use them for counting 
 * anyways, or is there a special need for them being int?  
 */

public class Levels {


public static String[][] Level1 = {{"3230000","2041001"},
									{"34001","4003211"}};

}



