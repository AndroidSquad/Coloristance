package se.androidsquad.coloristance.database;

/**
 * This class is supposed to describe the different levels that are included in the game
 * 				Dark		Light
 * 0=Black
 * 1=Blue 		#0099CC		#33B5E5
 * 2=Green 		#669900		#99CC00
 * 3=Orange 	#FF8800		#FFBB33	
 * 4=Purple 	#9933CC		#AA66CC
 * 5=Red  		#CC0000		#FF4444
 * 7=White		
 * 
 * These colors are predefined. The naming is in format:  color.holo_green_light
 * 
 * The colors have corresponding keys, with the same names and colors. 
 * 
 * A room is defined by a five character long string;
 * forexample "12340", where the first character represent the room (R) and the following four characters 
 * represent the rooms that the R is connected to, and the following characters represent the four connections in order up 
 * (N), right (E), down (S), left (W).
 * An example of the RNESW is: 13500 = A Blue room with a Orange door top and a red door to the right,
 * and no doors under or to the left.
 * The first figure must be a value since it represent the room's color.
 */ 

public class Levels { 
	
public static String[][] map_1 = {	

	{"00000","70100","00000"},
	{"30210","13027","21400"},
	{"20503","00000","40302"},
	{"50212","15430","31104"},
	{"20345","42011","14503"},
	{"30502","00000","50201"},
	{"50043","45720","24005"},
	{"00000","70000","00000"}

};
public static String[][] map_2 = {	

	{"00000","70310","10400"},
	{"20130","32540","43001"},
	{"10252","51003","00000"},
	{"20401","00000","40500"},
	{"40032","34150","53004"},
	{"00000","10503","00000"},
	{"10050","51031","35400"},
	{"00000","70000","47003"}

};
public static String[][] map_3 = {	

	{"00000","70310","17400"},
	{"20130","32547","43001"},
	{"10252","51003","00000"},
	{"20401","00000","40500"},
	{"40032","34150","53004"},
	{"00000","10503","00000"},
	{"10050","51731","35400"},
	{"00000","70000","47003"}

};

public static String[][] keys_1 = {	

	
	{"00000","10000","00000"},
	{"00000","01100","00000"},
	{"01011","01000","01000"},
	{"00100","00100","00100"},
	{"00010","00010","00010"},
	{"00011","00001","00001"},
	{"00000","00000","00000"},
	{"00000","00000","00000"}
};

public static String[][] keys_2 = {	
	// första=blå, Andra= grön, tredje orange, fjärde=lila, femte=röd
	{"00000","10000","00011"},
	{"10000","00100","10100"},
	{"01000","01010","00000"},
	{"00100","00000","10000"},
	{"00010","00001","00010"},
	{"00000","00001","00000"},
	{"10000","00000","00010"},
	{"00000","00000","01000"}
};
public static String[][] keys_3 = {	

	{"00000","10000","00001"},
	{"10000","00100","10000"},
	{"01000","01000","00000"},
	{"00100","00000","00100"},
	{"00010","00010","00010"},
	{"00000","00001","00000"},
	{"10000","00000","00010"},
	{"00000","00000","01000"}
};

}



