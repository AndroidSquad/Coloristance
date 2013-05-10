package se.androidsquad.coloristance;

public class Levels {
 /*
  * This class is supposed to describe the different levels that are included in the game
  * 1=Blue
  * 2=Green
  * 3=Yellow
  * 4=Red
  * 5=Connecting path between two rooms, under construction.
  * 
  */

int[][] Level1 = {	{0,4,3,4,0},
					{1,2,0,1,3},
					{0,1,3,2,0}};

public int length=Level1.length;
public int height=Level1[0].length;		

	
int[][]	Level1ALT = {	{0,0,4,5,3,5,4,0,0},
						{0,0,5,0,0,0,5,0,0},
						{1,5,2,0,0,0,1,5,3}, 
						{0,0,5,0,0,0,5,0,0}, 
						{0,0,1,5,3,5,2,0,0}};


		
};