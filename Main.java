import java.util.*;
class Main{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Board b=new Board();
        b.display();
        System.out.println("Game Started......");
        System.out.println("Select Element to break the Spaces");
        System.out.print("Row and Column : ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        if( b.IsValid(b.size,row,col) )
        {
            b.SetBombCells(row, col);  
            b.SetNumCells();
            b.SetSpacesAsViewed(row, col);
            boolean loop = true;
            while (loop) { 
                b.display();
                System.out.println("Flag remains : "+ b.flagcount );
                System.out.println("Select Element : ");
                System.out.print("Enter Row and Column : ");
                row = sc.nextInt();
                col = sc.nextInt();
                if(b.IsValid(b.size,row,col) == false)
                {
                    System.out.println("----------------------Wrong value entered....");
                    continue;
                    }
                System.out.print(" Enter Your choice :\n  1.Flag " );
                System.out.println("\n  2.Digging " + " \n  3.Cancel");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                            b.Setflag(row,col);
                        break;
                    case 2:
                        if(b.flag[row][col]==true)
                            System.out.println("--------------Already this Cell contains flag, So you can't digg Cell");
                        else if(b.board[row][col]=='B')
                        {
                            loop = false;
                            b.GAMEENDED = true;
                            b.display();
                            System.out.println("----------------------OOPs!! You lose the Game");
                        }
                        else
                            b.Setdigged(row,col);
                        break;
                    case 3:
                        break;
                    default:
                        throw new AssertionError();
                }
                if(b.checkAllViewed()){
                    b.GAMEENDED = true;
                    loop = false;
                    b.display();
                    System.out.println("----------------------You are Win");
                }
            }
        }
        else
            System.out.println("----------------------Wrong value entered..");
     
    }
}