import java.util.*;
class Board{
    public static char[][] board;
    public static boolean[][] viewed;  // Set All as Not Viewed
    public static boolean[][] flag;
    public static int flagcount;
    public static int size = 4;
    public static int bombcount = 2;
    public static boolean GAMEENDED = false;
    public Board() {
        board = new char[size][size];
        viewed = new boolean[size][size];
        flag = new boolean[size][size];
        flagcount = 0;
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = ' '; 
            }
        }
        
    }  // End of Counstructor

    public void SetBombCells(int row,int col) // This Row and Col must contains Spaces 
    {
        Random r = new Random();
        while(flagcount < bombcount){
            int i = r.nextInt(0,size);
            int j = r.nextInt(0,size);
            if(board[i][j] == 'B')
                continue;
            else if((row-1 == i) || (row == i) || (row+1 == i))
                if((col-1 <= j) && (col+1 >= j))
                    continue;
            board[i][j] = 'B';
            flagcount++;
            }
        }
    public void SetNumCells(){  // Set Number Cells for first time
        for (int i = 0; i < size; i++) 
            for(int j = 0; j < size; j++)
                if( board[i][j]!='B' )
                {
                    int number = CountOfBombAround(i, j);
                    if(number > 0)
                        board[i][j] = (char)(number+48);    
                    }
        }

    public void display()  // display Board
    {
        System.out.println();
        System.out.print("   ");  // Space for Row index
        for (int i = 0; i < size; i++) {
            System.out.print(i+ " "); // Col index
        }
        System.out.println();   // New line
        
        for(int i = 0 ; i < size; i++)
        {
            System.out.print(i + "  "); // Row Index
            for(int j = 0; j < size; j++)
            {
                if(GAMEENDED && board[i][j]=='B')
                    System.out.print("B");
                else if(flag[i][j] == true) // This Cell contains flag
                    System.out.print( "F" );
                else if(viewed[i][j]==false) // Hide the Cell
                    System.out.print( "." );
                else
                    System.out.print(board[i][j]); // This Cell contains Space , Bomb, Number
                System.out.print(" ");
                }
            System.out.println();   // New line
            }
        System.out.println();
        // System.out.println("Flag Count = " + flagcount);
        System.out.println();
        }
    
    public void Setflag(int row, int col)  // Set as flag 
    {
        if(viewed[row][col]==true)  //Viewed Cell
        {
            if(board[row][col]==' ')
                System.out.println("--------------Empty Space So can't Set flag");
            else
                System.out.println("--------------Already viewed Cell");
        } 
        else if(flag[row][col] == true){  
            flag[row][col]=false;
            System.out.println("--------------Flag Unset Successfully");
            flagcount++;
        }
        else{
            flag[row][col] = true;
            System.out.println("--------------Flag Set Successfully");
            flagcount--;
        }

    }
    public void Setdigged(int row, int col) // Set as digged
    {
        if(viewed[row][col]==true)  // Viewed Cell
        {
                if(board[row][col]==' ')
                    System.out.println("--------------Empty Space So can't Set flag");
                else
                    System.out.println("--------------Already viewed Cell");
                }
        else
        {
            viewed[row][col] = true;
            if(board[row][col]==' ') // This Cell contains Empty space
                SetSpacesAsViewed(row,col);
            System.out.println("--------------Digged Successfully");
            }
        }
    public void SetSpacesAsViewed(int row, int col) // Set Spaces as Viewed
    {
        viewed[row][col] = true;
        SetNumAroundSpacesAsViewed(row,col);
        if(IsValid(size,row-1,col) && board[row-1][col]==' ' && viewed[row-1][col]==false)
        {
            SetSpacesAsViewed(row-1, col);
            }
        if(IsValid(size,row+1,col) && board[row+1][col]==' ' && viewed[row+1][col]==false)
        {
            SetSpacesAsViewed(row+1, col);
            }
        if(IsValid(size,row,col-1) && board[row][col-1]==' ' && viewed[row][col-1]==false)
        {
            SetSpacesAsViewed(row, col-1);
            }
        if(IsValid(size,row,col+1) && board[row][col+1]==' ' && viewed[row][col+1]==false)
        {
            SetSpacesAsViewed(row, col+1);
            }

        }
    public void  SetNumAroundSpacesAsViewed(int row,int col) //Set Number Cell around Spaces, as Viewed
    {
        if(IsValid(size,row-1,col) && board[row-1][col] > 48 )
        {
            viewed[row-1][col]=true;
            }
        if(IsValid(size,row+1,col) && board[row+1][col] > 48 )
        {
            viewed[row+1][col]=true;
            }
        if(IsValid(size,row,col-1) && board[row][col-1] > 48 )
        {
            viewed[row][col-1]=true;
            }
        if(IsValid(size,row,col+1) && board[row][col+1] > 48 )
        {
            viewed[row][col+1]=true;
            }
        if(IsValid(size,row-1,col-1) && board[row-1][col-1] > 48)
        {
            viewed[row-1][col-1]=true;
            }   
        if(IsValid(size,row-1,col+1) && board[row-1][col+1] > 48)
        {
            viewed[row-1][col+1]=true;
            }   
        if(IsValid(size,row+1,col-1) && board[row+1][col-1] > 48 )
        {
            viewed[row+1][col-1]=true;
            }
        if(IsValid(size,row+1,col+1) && board[row+1][col+1] > 48 )
        {
            viewed[row+1][col+1]=true;
            }   
        }
        
    public int CountOfBombAround(int row, int col)  // Count of Bomb around
    {
        int count = 0;
        if(IsValid(size,row-1,col) && board[row-1][col] == 'B') // North
        { 
            count++;
            }
        if(IsValid(size,row+1,col) && board[row+1][col] == 'B') //South
        {
            count++;
            }
        if(IsValid(size,row,col-1) && board[row][col-1] == 'B') // West
        {
            count++;
            }
        if(IsValid(size,row,col+1) && board[row][col+1] == 'B') //East
        {
            count++;
            }
        if(IsValid(size,row-1,col-1) && board[row-1][col-1] == 'B') // North west
        {
            count++;
            }            
        if(IsValid(size,row-1,col+1) && board[row-1][col+1] == 'B') // North east
        {
            count++;
            }      
        if(IsValid(size,row+1,col-1) && board[row+1][col-1] == 'B') //South west
        {
            count++;
            }
        if(IsValid(size,row+1,col+1) && board[row+1][col+1] == 'B') //South east
        {
            count++;
            }
        return count;
        }
    public boolean checkAllViewed(){    //Check All Cells are Viewed
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] != 'B')
                    if(viewed[i][j]==false)
                        return false;
                }
            }
        return true;
        }
    public boolean IsValid(int size,int row,int col)
    {
        return ((row >= 0 && row < size) && (col >= 0 && col < size));
             
    }
}
