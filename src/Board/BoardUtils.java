package Board;

public class BoardUtils {
	
	
	// These boolean arrays will store true values at the corresponding row values;
	public static final boolean[] FIRST_COLOUMN = initColoumn(0); 
	public static final boolean[] SECOND_COLOUMN = initColoumn(1);
	public static final boolean[] SEVEN_COLOUMN = initColoumn(6);
	public static final boolean[] EIGHT_COLOUMN = initColoumn(7);
	
	public static final int NUM_TILES = 64;
	public static final int TILES_IN_A_ROW = 8;
	
	public static final boolean[] ROW_SEVEN = initRow(8);
	public static final boolean[] ROW_TWO = initRow(48);

	public BoardUtils(){
		throw new RuntimeException("Cannot instantiate this class object!");
	}
	
	

	private static boolean[] initRow(int i) {

		boolean[] row = new boolean[NUM_TILES];
		System.out.println("init row ");
		
		do{
			row[i] = true;
			i++;
				
		}while(i % TILES_IN_A_ROW !=0);
		
		return row;
	}

	private static boolean[] initColoumn(int coloumnNumber) { // this will make the first coloumn of that row to true and rest is initialized to false
		
		final boolean[] coloumn = new boolean[NUM_TILES];	
		do{
			System.out.println("initColumn for column "+coloumnNumber);
			
			coloumn[coloumnNumber] = true;
			coloumnNumber+=TILES_IN_A_ROW;
			
			
		}while(coloumnNumber < NUM_TILES);
	
		return coloumn;
	}

	public static  boolean isvalidCoordinate(final int coordinate)
	{
		try{
		System.out.println("Checking validity ... coordinate is "+coordinate);
		
		return (coordinate >=  0) && (coordinate < 64);
		}catch(Exception e ){
			System.out.println(e.getMessage());
		}
		return false;
	}
}
