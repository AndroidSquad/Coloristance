package se.androidsquad.coloristance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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


public class Levels { //Would like to scan the .txt file and insert the information into a array[][]. The current version does not work however.
//protected static File filename = new File("/assets/levels/Level1.txt");
//protected static String[][] mapArray;


public static String[][] mapArray = {	//{"10000","20410","32320","23001","12110","23101"},
										//{"34001","40032","40132","34001","40032","40132"},
										//{"10000","12110","23101","12300","20410","32320"},
									
										{"10000","10410","12320","13001","12110","13101"},
										{"24001","20032","20132","24001","20032","20132"},
										{"30000","32110","33101","32300","30410","32320"},

									};




//public static void initLevel() throws FileNotFoundException, IOException {
//	Scanner sc = new Scanner(filename);
//	for (int i = 0; i < mapArray.length; i++){
//		for(int j= 0; j< mapArray[i].length; j++){
//			mapArray[i][j] = sc.next();
//		}	
//	} 
//}

}



