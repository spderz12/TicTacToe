import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.io.IOException;

public class TTTGame extends JPanel implements MouseListener,KeyListener
{
    Image title;
    static Image background1;
    Image background2;
    Image exit;
    Image question;
    Image xDefault;
    Image yDefault;
    Image sword;
    Image reverseSword;
    Image gun;
    Image reverseGun;
    Image crewmate;
    Image imposter;
    Image reverseImposter;
    Image xImage;
    Image yImage;
    Image person;
    Image robot;
    Image rightGun;
    Image leftGun;
    Image leftSmoke;
    Image rightSmoke;
    public static int screen = 1;
    static int players;
    static int turn = 1;
    int winner = 0;
    int[][] board = {
        {0,0,0},
        {0,0,0},
        {0,0,0}
    };
    boolean compStarts;
    static char compChar = ' ';
    int [] charsList = {1,0,0};
    public boolean winScreenDrawn = false;
    public static boolean drawSmokeLeft = false;
    public static boolean drawSmokeRight = false;
    
    
    public TTTGame()
    {
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //loading the pngs
        try
        {
            title = ImageIO.read(new File("Title.png"));
            background1 = ImageIO.read(new File("LighterBackground.png"));
            xDefault = ImageIO.read(new File("DefaultX.png"));
            yDefault = ImageIO.read(new File("DefaultO.png"));
            sword = ImageIO.read(new File("sword.png"));
            gun = ImageIO.read(new File ("gun.png"));
            reverseGun = ImageIO.read(new File ("reverseGun.png"));
            crewmate = ImageIO.read(new File ("crewmate.png"));
            imposter = ImageIO.read(new File ("imposter.png"));
            reverseImposter = ImageIO.read(new File ("reverseImposter.png"));
            exit = ImageIO.read(new File ("exit.png"));
            question = ImageIO.read(new File ("question.png"));
            person = ImageIO.read(new File ("person.png"));
            robot = ImageIO.read(new File ("robot.png"));
            background2 = ImageIO.read(new File("duelBackground.png"));
            rightGun =  ImageIO.read(new File("rightGun.png"));
            leftGun = ImageIO.read(new File("leftGun.png"));
            reverseSword = ImageIO.read(new File("reverseSword.png"));
            leftSmoke = ImageIO.read(new File("leftSmoke.png"));
            rightSmoke = ImageIO.read(new File("rightSmoke.png"));
            xImage = xDefault; //REMOVE
            yImage = yDefault;
        }
        catch (IOException e)
        {
        }
        
    }
    
    public void paint(Graphics g)
    {
        if (screen == 1){
            startScreen(g);
        }
        else if (screen == 2){
            drawBoard(g);
        }
        else if (screen == 3){
            winScreen(g, winner);
        }
        else if (screen == 4){
            drawOptions(g);
        }
        else if(screen == 5){
            turnSelect(g);
        }
        else if (screen == 6){
            onePlayerSelect(g);
        }
        else if (screen == 7){
            drawDuel(g, duelVert, duelHorz, board);
        }
        else if (screen == 8)
        {
            drawDuelStart(g);
        }
        else if (screen == 9)
        {
            drawDuelWin(g, compDuelWin, p1Won, p2Won);
        }
    }
   
    public void startScreen (Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,600,600);
        g.drawImage(title, -43,50, null);
        
            //menu button 1
        g.setColor(Color.black);
        g.fillRect(60,350,200,100);
            //menu button 2
        g.fillRect(340,350,200,100);
        //options button
        g.fillRect(225,470, 150, 50);
    
            // button text
          Font font = new Font("Times New Roman", Font.PLAIN, 27);
        g.setFont(font);

        g.setColor(Color.white);
        g.drawString("Single Player", 85, 405);
        g.drawString("Duel Combatants", 347,405);
        font = new Font("Calibri", Font.PLAIN, 24);
        g.setFont(font);
        g.drawString("Options",262,499);
        
        
        
    }
    
    public void drawOptions(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,600,600);
        g.setColor(Color.black);
        g.fillRect(0,295,600,10);
        g.setColor(Color.red);
        g.fillRect(0,515,50,50);
        g.drawImage(exit,0,515, null);
        
        for (int i = 0; i <=2; i++){
            if (charsList[i] == 1)
            {
                g.setColor(Color.orange);
                g.fillRect((i)*200, 0, 200,294);
            }
        }
        g.drawImage(xDefault,0,0,null);
        g.drawImage(gun,200,0,null);
        g.drawImage(imposter, 400,0,null);
        g.setColor(Color.black);
        Font font = new Font("Arial", Font.PLAIN, 27);
        g.setFont(font);
        g.drawString("Default",53, 220);
        g.drawString("Weapons", 253, 220);
        g.drawString("Sussy", 455, 220);
    }
    
    public void turnSelect(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,600,600);
        Font font = new Font("Times New Roman", Font.PLAIN, 27);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Who Goes First?",200,200);
        g.drawImage(xImage, 40,250, null);
        g.drawImage(yImage, 350, 240, null);
        g.drawImage(question, 200,250, null);
    }
    
    public void onePlayerSelect(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,600,600);
        Font font = new Font("Times New Roman", Font.PLAIN, 27);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Who Goes First?", 195,60);
        g.drawImage(person,100,100, null);
        g.drawImage(robot,350,100, null);
        g.drawString("Select Your Character",170,260);
        g.drawImage(xImage, 40,310, null);
        g.drawImage(yImage, 350, 300, null);
        g.drawImage(question, 200,310, null);
        g.setColor(new Color (50, 205, 50));
        g.fillRect(195, 520, 200, 100);
        g.setColor(Color.white);
        g.drawString("START", 255, 555);
    }
        // These are parameters used in drawBoard to start the duel
        public static int duelVert = 0;
        public static int duelHorz= 0;
        public static boolean duel = false;
    public void drawBoard (Graphics g)
    {
        boolean firstLoop = false;
        g.fillRect(0,0,600,600);
        g.drawImage(background1, 0,0,null);
        g.setColor(Color.black);
        g.fillRect(195,0,10,600);
        g.fillRect(395,0,10,600);
        g.fillRect(0,180,600,10);
        g.fillRect(0,380,600,10);
            
        
        
        if(turn == 1 && compChar == 'x' || turn == 2 && compChar == 'y' && players == 1) {
            computerMove(board);
        }
        
        
        
        
        for(int j = 0; j<=2; j++)
        {
            for (int k = 0; k<=2; k++)
            {
                if(board[j][k] == 1){
                    g.drawImage(xImage, k*200,j*200,null);
                }
                if (board[j][k] == 2)
                {
                    g.drawImage(yImage, k*200,j*200,null);
                }
            }
        }
    
 
        
        
         
        
        if (checkWin(board)){
            screen = 3;
        }
        firstLoop = true;
    }

    public void winScreen (Graphics g, int x)
    {
        
        winScreenDrawn=false;
        /*this boolean prevents the user from skipping the winScreen entirely when clicking
        the same area as a button*/
        
        g.setColor(Color.white);
        g.fillRect(0,0,600,600);
        Font font = new Font("Times New Roman", Font.PLAIN, 27);
        g.setFont(font);
        g.setColor(Color.black);
        if (x == 1)
        g.drawString("PLAYER ONE IS VICTORIOUS", 100, 250);
        else if (x==2)
        g.drawString("PLAYER TWO IS VICTORIOUS", 100, 250);
        
        g.setColor(Color.black);
        g.fillRect(60,350,200,100);
        g.fillRect(340,350,200,100);
        
        g.setColor(Color.white);
        g.drawString("Play Again", 90, 405);
        g.drawString("Main Menu", 380,405);
        winScreenDrawn=true;
    }
    
    public boolean checkWin(int[][] board){
        int g = 0;
        int k = 0;
    
    //checks rows
        for(int j =0; j<= 2; j++)
        {
            if(board[j][k] == 1 && board[j][k+1] == 1 && board[j][k+2] == 1)
            {
                winner = 1;
                return true;
            }
            else if (board[j][k] == 2 && board[j][k+1] == 2 && board[j][k+2] == 2){
                winner = 2;
                return true;
            }
        }
    //checks collums
        for(int i =0; i<= 2; i++)
        {
            if(board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1)
            {
                winner = 1;
                return true;
            }
            else if (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2)
            {
                winner = 2;
                return true;
            }

        }
    //check diagonals
    
        if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1)
        {
            winner = 1;
            return true;
        }
        else if(board[2][0] == 1 && board[1][1] == 1 && board [0][2] == 1)
        {
            winner = 1;
            return true;
        }
        else if(board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2)
        {
            winner = 2;
            return true;
        }
        else if(board[2][0] == 2 && board[1][1] == 2 && board [0][2] == 2)
        {
            winner = 2;
            return true;
        }

    return false;
  }
       public static boolean moveMade = false;


    public static void computerMove(int[][] board) {
        moveMade = false;
        
        twoInRow(board);        
        
        if(!moveMade)
            duelCheck(board);
           
        //check for 2 of the same kind on same plane
        //check if computer can place one more to win
        if (!moveMade)
            randomMove(board);

        if (compChar == 'x')
            turn = 2;
        else
            turn = 1;
    }
    
    public static void duelCheck(int[][] board) {
        int x = 0;
        int y = 0;
        int vertPos = 0;
        int horzPos = 0;
        boolean replace = false;

        //HORIZONTAL
        for (int i = 0; i <= 2; i++) {
            for (int k = 0; k <= 2; k++) {

                if (board[i][k] == 1) {
                    x++;
                    if (compChar == 'y') {
                        vertPos = i;
                        horzPos = k;
                        replace = true;

                    }
                } else if (board[i][k] == 2) {
                    y++;
                    if (compChar == 'x') {
                        vertPos = i;
                        horzPos = k;
                        replace = true;

                    }
                }

            }

            if (x == 2 && compChar == 'x' && replace) {
                duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
                moveMade = true;
                return;
            } else if (y == 2 && compChar == 'y' && replace) {
                duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
                moveMade = true;
                return;
            }

            x = 0;
            y = 0;
            replace = false;
        }

        //VERTICLE

        for (int k = 0; k <= 2; k++) {
            for (int i = 0; i <= 2; i++) {
                if (board[i][k] == 1) {
                    x++;
                    if (compChar == 'y') {
                        vertPos = i;
                        horzPos = k;
                        replace = true;
                    }
                } else if (board[i][k] == 2) {
                    y++;
                    if (compChar == 'x') {
                        vertPos = i;
                        horzPos = k;
                        replace = true;
                    }
                }
            }
            if (x == 2 && compChar == 'x' && replace) {
                duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
                moveMade = true;
                return;
            } else if (y == 2 && compChar == 'y' && replace) {
                duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;                
                moveMade = true;
                return;
            }

            x = 0;
            y = 0;
            replace = false;

        }

        //DIAGONALS
        for (int i = 0; i <= 2; i++) {
            if (board[i][i] == 1) {
                x++;
                if (compChar == 'y') {
                    vertPos = i;
                    horzPos = i;
                    replace = true;
                }
            } else if (board[i][i] == 2) {
                y++;
                if (compChar == 'x') {
                    vertPos = i;
                    horzPos = i;
                    replace = true;
                }
            }
        }
        if (x == 2 && compChar == 'x' && replace) {
            duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
            moveMade = true;
            return;
        } else if (y == 2 && compChar == 'y' && replace) {
            duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
            moveMade = true;
            return;
        }


        x = 0;
        y = 0;
        replace = false;

        int temp = 2;
        for (int i = 0; i <= 2; i++) {

            if (board[temp][i] == 1) {
                x++;
                if (compChar == 'y') {
                    vertPos = temp;
                    horzPos = i;
                    replace = true;
                }
            } else if (board[temp][i] == 2) {
                y++;
                if (compChar == 'x') {
                    vertPos = temp;
                    horzPos = i;
                    replace = true;
                }

            }

            //The change of i is needed to have the positions for the backwards diagonal
            if (temp == 2)
                temp = 1;
            else
                temp = 0;

        }
        if (x == 2 && compChar == 'x' && replace) {
            duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
            moveMade = true;
        } else if (y == 2 && compChar == 'y' && replace){
            duelVert = vertPos;
                duelHorz= horzPos;
                duel = true;
            moveMade = true;
        }
    }



    public static void twoInRow(int[][] board) {
        int x = 0;
        int y = 0;
        int tempX = 0;
        int tempY = 0;
        int vertPos = 0;
        int horzPos = 0;
        int tempVert = 0;
        int tempHorz = 0;

        //HORIZONTAL
      for (int i = 0; i <= 2; i++) {
            for (int k = 0; k <= 2; k++) {
                if (board[i][k] == 0) {
                    horzPos = k;
                    vertPos = i;
                }
                if (board[i][k] == 1) {
                    x++;
                } else if (board[i][k] == 2) {
                    y++;

                }
            }
            if (x + y != 3) {
                if (x == 2 && compChar == 'x') {
                    if(moveMade) {
                        board[tempVert][tempHorz] = 0;

                    }

                    board[vertPos][horzPos]=1;

                    moveMade = true;
                    return;
                } else if (y == 2 && compChar == 'y') {
                    if(moveMade) {
                        board[tempVert][tempHorz] = 0;

                    }

                        board[vertPos][horzPos]=2;

                    moveMade = true;
                    return;

                } else if (y == 2 && compChar == 'x') {
                    if(moveMade && tempX != 2) {
                        board[tempVert][tempHorz] = 0;
                        board[vertPos][horzPos] = 1;
                    }
                    else if (!moveMade)
                    {
                        board[vertPos][horzPos]=1;
                    }
                    moveMade = true;
                } else if (x == 2 && compChar == 'y') {
                    if(moveMade && tempY != 2) {
                        board[tempVert][tempHorz] = 0;
                        board[vertPos][horzPos] = 1;
                    }
                    else if (!moveMade)
                    {
                        board[vertPos][horzPos]=2;
                    }
                    moveMade = true;
                }
            }
          if (i == 2 && moveMade)
              return;

          if(moveMade) {
              if (i == 0) {
                  tempX = x;
                  tempY = y;
              }

              if(((tempX != 2 && compChar == 'x')|| (tempY != 2 && compChar == 'y')) && x + y > 0){
                  tempVert = vertPos;
                  tempHorz = horzPos;
              }
                tempX = x;
              tempY = y;

            }
            x = 0;
            y = 0;
        }
        tempX = 0;
        tempY = 0;
        //VERTICLE
        for (int k = 0; k <= 2; k++) {
            for (int i = 0; i <= 2; i++) {
                if (board[i][k] == 0) {
                    vertPos = i;
                    horzPos = k;
                }
                if (board[i][k] == 1) {
                    x++;
                } else if (board[i][k] == 2) {
                    y++;
                }
               

            }
            if (x + y != 3) {
                if (x == 2 && compChar == 'x') {
                    if(moveMade) {
                        board[tempVert][tempHorz] = 0;

                    }

                    board[vertPos][horzPos]=1;

                    moveMade = true;
                    return;
                } else if (y == 2 && compChar == 'y') {
                    if(moveMade) {
                        board[tempVert][tempHorz] = 0;

                    }

                    board[vertPos][horzPos]=2;

                    moveMade = true;
                    return;

                } else if (y == 2 && compChar == 'x') {
                    if(moveMade && tempX != 2) {
                        board[tempVert][tempHorz] = 0;
                        board[vertPos][horzPos] = 1;
                    }
                    else if (!moveMade)
                    {
                        board[vertPos][horzPos]=1;
                    }
                    moveMade = true;
                } else if (x == 2 && compChar == 'y') {
                    if(moveMade && tempY != 2) {
                        board[tempVert][tempHorz] = 0;
                        board[vertPos][horzPos] = 1;
                    }
                    else if (!moveMade)
                    {
                        board[vertPos][horzPos]=2;
                    }
                    moveMade = true;
                }
            }
            if (k == 2 && moveMade)
                return;

            if(moveMade) {
                if (k == 0) {
                    tempX = x;
                    tempY = y;
                }
                if(((tempX != 2 && compChar == 'x')|| (tempY != 2 && compChar == 'y')) && x + y > 0){
                    tempVert = vertPos;
                    tempHorz = horzPos;
                }
                tempX = x;
                tempY = y;
            }
            x = 0;
            y = 0;
        }
        //DIAGONALS
        for (int i = 0; i <= 2; i++) {
            if (board[i][i] == 0) {
                vertPos = i;
                horzPos = i;
            } else if (board[i][i] == 1) {
                x++;
            } else if (board[i][i] == 2) {
                y++;
            }

        }
        if (x + y != 3) {
            if (x == 2 && compChar == 'x') {
                board[vertPos][horzPos] = 1;
                moveMade = true;
                return;
            } else if (y == 2 && compChar == 'y') {
                board[vertPos][horzPos] = 2;
                moveMade = true;
                return;
            } else if (y == 2 && compChar == 'x') {
                board[vertPos][horzPos] = 1;
                moveMade = true;
                return;
            } else if (x == 2 && compChar == 'y') {
                board[vertPos][horzPos] = 2;
                moveMade = true;
                return;
            }
        }
        x = 0;
        y = 0;

        int temp = 2;
        for (int i = 0; i <= 2; i++) {

            if (board[temp][i] == 0) {
                vertPos = temp;
                horzPos = i;

            } else if (board[temp][i] == 1) {
                x++;
            } else if (board[temp][i] == 2) {
                y++;


            }

            //The change of i is needed to have the positions for the backwards diagonal
            if (temp == 2) temp = 1;
            else temp = 0;

        }
        if (x + y != 3) {
            if (x == 2 && compChar == 'x') {
                board[vertPos][horzPos] = 1;
                moveMade = true;
            } else if (y == 2 && compChar == 'y') {
                board[vertPos][horzPos] = 2;
                moveMade = true;
            } else if (y == 2 && compChar == 'x') {
                board[vertPos][horzPos] = 1;
                moveMade = true;
            } else if (x == 2 && compChar == 'y') {
                board[vertPos][horzPos] = 2;
                moveMade = true;
            }
        }
    }


    public static void randomMove(int[][] board) {
        int moves = 0;
        moves = numberOfMoves(board);

        //Gets available moves and chooses a random one
        int[] xMoves = new int[moves];
        int[] yMoves = new int[moves];

        availableMoves(board, xMoves, yMoves);

        int randomMove = (int) (moves * Math.random() + 1);

        if (compChar == 'x' && moves != 0) {
            board[yMoves[randomMove - 1]][xMoves[randomMove - 1]] = 1;
        } else if (moves != 0) {
            board[yMoves[randomMove - 1]][xMoves[randomMove - 1]] = 2;
        }

    }

    public static int numberOfMoves(int[][] board) {
        int numberOfMoves = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == 0) {
                    numberOfMoves++;
                }
            }
        }
        return numberOfMoves;
    }

    public static void availableMoves(int[][] board, int[] xMoves, int[] yMoves) {
        int num = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == 0) {
                    xMoves[num] = j;
                    yMoves[num] = i;
                    num++;
                }
            }
        }
    }
    
    
    public void mouseClicked(MouseEvent e)
    {
        
    }
    
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        //location of mouse on click
        
        if (screen == 1)
        {
            if (x>=60 && x <= 260 && y >= 340 && y<=540)
            {
                players = 1;
                screen = 6;
            }
        
            else if (x >= 340 && x<=540 && y >= 350 && y <= 450)
            {
            
                players = 2;
                screen = 5;
            
            }
            else if (x >= 225 && x <= 375 && y >=470 && y <= 520)
            {
                screen = 4;
            }
        
        }
        else if (screen == 2)
        {
            if(x > 0){
                if(duel == true && players == 1){
                screen = 8;
            }
            }
            //FIRST ROW
            if(x<=200 && y <= 200)
            {
                if(board[0][0] == 0)
                {
                    if (turn == 1)
                    {
                    board[0][0] = 1;
                    turn = 2;
                    }
                    else
                    {
                    board[0][0] = 2;
                    turn = 1;
                    }
                }
                else if (board[0][0] != turn)
                {
                    duelVert = 0;
                    duelHorz = 0;
                    screen = 8;
                }
                
            }
            else if(x>200 && x <= 400 && y <= 200)
            {
                if (board[0][1] == 0)
                {
                if (turn == 1)
                {
                    board[0][1] = 1;
                    turn = 2;
                }
                else
                {
                    board[0][1] = 2;
                    turn = 1;
                }
                }   
                else if (board[0][1] != turn)
                {
                    duelVert = 0;
                    duelHorz = 1;
                    screen = 8;
                }
            
            }
            else if(x>400 && x <= 600 && y <=  200)
            {
                if (board[0][2] == 0){
                if (turn == 1)
                {
                    board[0][2] = 1;
                    turn = 2;
                }
                else
                {
                    board[0][2] = 2;
                    turn = 1;
                }
                }
                else if (board[0][2] != turn)
                {
                    duelVert = 0;
                    duelHorz = 2;
                    screen = 8;
                }
            }
            //SECOND ROW
            else if(x<= 200 && y > 200 && y <= 400)
            {
                if(board[1][0] == 0)
                {
                if (turn == 1)
                {
                    board[1][0] = 1;
                    turn = 2;
                }
                else
                {
                    board[1][0] = 2;
                    turn = 1;
                }
                }
                else if (board[1][0] != turn)
                {
                    duelVert = 1;
                    duelHorz = 0;
                    screen = 8;
                }
            }
            else if(x>200 && x <= 400 && y > 200 &&  y <= 400)
            {
                if (board[1][1] == 0)
                {
                if (turn == 1)
                {
                    board[1][1] = 1;
                    turn = 2;
                }
                else
                {
                    board[1][1] = 2;
                    turn = 1;
                }
                }
                else if (board[1][1] != turn)
                {
                    duelVert = 1;
                    duelHorz = 1;
                    screen = 8;
                }
            }
            
            else if(x>400 && x <= 600 && y > 200 && y <= 400)
            {
                if (board[1][2] == 0)
                {
                if (turn == 1)
                {
                    board[1][2] = 1;
                    turn = 2;
                }
                else
                {
                    board[1][2] = 2;
                    turn = 1;
                }
                }
                else if (board[1][2] != turn)
                {
                    duelVert = 1;
                    duelHorz = 2;
                    screen = 8;
                }
            }
            //THIRD ROW
            else if(x<= 200 && y > 400 && y <= 600)
            {
                if (board[2][0] == 0){
                if (turn == 1)
                {
                    board[2][0] = 1;
                    turn = 2;
                }
                else
                {
                    board[2][0] = 2;
                    turn = 1;
                }
            }
            else if (board[2][0] != turn){
                duelVert = 2;
                duelHorz = 0;
                screen = 8;
            }
            }
            else if(x>200 && x <= 400 && y > 400 &&  y <= 600)
            {
                if(board[2][1] == 0)
                {
                if (turn == 1)
                {
                    board[2][1] = 1;
                    turn = 2;
                }
                else
                {
                    board[2][1] = 2;
                    turn = 1;
                }
            }
            else if (board[2][1] != turn)
            {
                duelVert = 2;
                duelHorz = 1;
                screen = 8;
            }
            }
            else if(x>400 && x <= 600 && y > 400 && y <= 600 )
            {
                if(board[2][2] == 0)
                {
                if (turn == 1)
                {
                    board[2][2] = 1;
                    turn = 2;
                }
                else
                {
                    board[2][2] = 2;
                    turn = 1;
                }
            }
            else if (board[2][2] != turn)
            {
                duelVert = 2;
                duelHorz = 2;
                screen = 8;
            }
            }
        
        
    
        }
        else if (screen == 3)
        {
            //reset board to zero
            for (int j = 0;  j <= 2; j++){
                    for (int k = 0; k <= 2; k++){
                        board[j][k] = 0;
                    }
                }
            if (x>=60 && x <= 260 && y >= 340 && y<=540 && winScreenDrawn)
            {
                
                screen = 2;
                winScreenDrawn = false;
                
            }
        
            else if (x >= 340 && x<=540 && y >= 350 && y <= 450 && winScreenDrawn)
            {
            
                screen = 1;
                winScreenDrawn = false;
            
            }
        }
        else if (screen == 4)
        {
            
            if(x < 200 && y < 200)
            {
                //resets selection
                for (int i = 0; i <=2; i++)
                {
                    xImage = xDefault;
                    yImage = yDefault;
                    charsList[i] = 0;
                }
                charsList[0] = 1;
            }
            else if (x > 200 && x < 400 && y < 200)
            {
                for (int i = 0; i <=2; i++)
                {
                    charsList[i] = 0;
                    xImage = gun;
                    yImage = sword;
                }
                charsList[1] = 1;
            }
            else if (x> 400 && y < 200)
            {
                for (int i = 0; i <=2; i++)
                {
                    charsList[i] = 0;
                    xImage = imposter;
                    yImage = crewmate;
                }
                charsList[2] = 1;
            }
            else if (x < 60 && y > 515){
                screen = 1;
            }
        }
        else if (screen == 5)
        {
            if (x>=40 && x <= 240 && y >=250 && y <= 450)
            {
                turn = 1;
                screen = 2;
            }
            else if (x>= 350 && x<= 550 && y >= 240 && y <= 440)
            {
                turn = 2;
                screen = 2;
            }
            else if (x>= 200 && x <= 350 && y >=250 && y <=400)
            {
                turn = (int)(2*Math.random()+ 1);
                screen = 2;
            }
        }
        else if (screen == 6)
        {
            //presets outcome if no options are chosen
            if(x > 100 && x < 200 && y > 100 && y < 200)
            {
                compStarts = false;
            }
            else if (x > 350 && x < 450 && y > 100 & y < 200)
            {
                compStarts = true;
            }
        
            else if (x > 40 && x < 240 && y > 300 && y < 500)
            {
                if(compStarts)
                {
                    turn = 2;
                    //y goes first
                    compChar = 'y';
                }
                else
                {
                    turn = 1;
                    // x goes first
                    compChar = 'y';
                }
            }
            else if (x > 350 && x < 550 && y > 300 && y <500)
            {
                if(compStarts)
                {
                    turn = 1;
                    compChar = 'x';
                }
                else
                {
                    turn = 2;
                    compChar = 'x';
                }
            }
            
            else if (x >195 && x < 395 && y > 520)
            {
                screen = 2;
            }
            
        }
        else if (screen == 8)
        {
            if(x>0 && x < 600)
            screen = 7;
        }
        else if (screen == 9 && duelWinDrawn)
        {
            if(x>0 && x < 600)
            screen = 2;
        }
        repaint();
    }
    
    public void mouseReleased (MouseEvent e){
        
    }
    
    public void mouseEntered(MouseEvent e){
        
    }
    
    public void mouseExited(MouseEvent e){
        
    }
    
    public void drawDuelStart(Graphics g)
    {
        g.drawImage(background1, 0, 0, null);
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.BOLD, 27);
        g.setFont(font);
        g.drawString("Preparations for the duel are complete.",40, 200);
        g.drawString("Ready yourselves...", 120, 240);
        font = new Font("Helvetica", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString("Click anywhere to begin", 220, 500);
    }
    
    public static long nanoTime1 = 0;
    public static long nanoTime2 = 0;
    public static boolean p1Won = false;
    public static boolean p2Won = false;
     public static boolean compDuelWin = false;
     public static boolean firstRedraw = true;
    public void drawDuel(Graphics g, int y, int x, int[][] board)
    {
        int count = 3;
        boolean screenDrawn = false;

        g.drawImage(background2,-10,0, null);
        Font font = new Font("Arial", Font.BOLD, 45);
        g.setFont(font);
        if(players == 2){
            g.drawString("P1", 90,80);
            g.drawString("P2", 460, 80);
        }
        else
        {
           if(compChar == 'x')
           {
               g.drawString("CPU", 90, 80);
               g.drawString("P1", 460, 80);
            }
           else if (compChar == 'y')
           {
                g.drawString("P1", 80, 80);
                g.drawString("CPU", 450, 80);
            }
        }
        
        
        if(xImage == xDefault)
        {
            g.drawImage(xImage, -20,175, null);
            g.drawImage(yImage, 415, 158, null);
            if(drawSmokeLeft)
            {
                g.drawImage(leftSmoke, 210, 188, null);
            }
            if(drawSmokeRight)
            {
                g.drawImage(rightSmoke, 299, 188, null);
            }
            g.drawImage(leftGun, 110, 200, null);
            g.drawImage(rightGun, 369, 200, null);
        }
        else if (xImage == gun)
        {
            if(drawSmokeLeft)
            {
                g.drawImage(leftSmoke, 205,228,null);
            }
            if(drawSmokeRight)
            {
                g.drawImage(rightSmoke, 340, 205, null); 
            }
            g.drawImage(sword, 0, 175, null);
            g.drawImage(leftGun, 105, 240, null);
            g.drawImage(reverseGun, 400, 180, null);
        }
        else if(xImage == imposter)
        {
            g.drawImage(crewmate, 0,140, null);
            g.drawImage(imposter, 410, 138, null);
            if(drawSmokeLeft)
            {
                g.drawImage(leftSmoke, 235,190,null);
            }
            if(drawSmokeRight)
            {
                g.drawImage(rightSmoke, 289, 175, null); 
            }
            g.drawImage(leftGun, 140, 200, null);
            g.drawImage(rightGun, 359, 190, null);
        }
        screenDrawn = true;
        if(screenDrawn && firstRedraw)
        countdown(count, g);
        
        //add computer fire (make draw true and add nanotime
        
        if (players == 1){
            
            if((nanoTime1 > 0 && nanoTime2 == 0) || (nanoTime1 < nanoTime2 && nanoTime1 != 0)){
                board[y][x] = 1;
                p1Won = true;
                p2Won = false;
                if (compChar == 'x'){
                    compDuelWin = true;
                    screen = 9;
                }
                else
                {
                    compDuelWin = false;
                    screen = 9;
                }
            }
            else if((nanoTime2 > 0 && nanoTime1 == 0) ||(nanoTime1 < nanoTime2 && nanoTime1 != 0))
            {
                board[y][x] = 2;
                p2Won = true;
                p1Won = false;
                if (compChar == 'x'){
                    compDuelWin = true;
                    screen = 9;
                }
                else
                {
                    compDuelWin = false;
                    screen = 9;
                }
            }
        }
        if (players == 2)
        {
            if((nanoTime1 > 0 && nanoTime2 == 0) || (nanoTime1 < nanoTime2 && nanoTime1 != 0)){
                board[y][x] = 1;
                p1Won = true;
                p2Won = false;
                screen = 9;
            }
            else if((nanoTime2 > 0 && nanoTime1 == 0) ||(nanoTime1 < nanoTime2 && nanoTime1 != 0))
            {
                board[y][x] = 2;
                p2Won = true;
                p1Won = false;
                screen = 9;
            }
        }
    }
    
    public static boolean duelWinDrawn = false;
    public static void drawDuelWin (Graphics g, boolean compWin, boolean p1Won, boolean p2Won){
        duelWinDrawn = false;
        g.drawImage(background1, 0, 0, null);
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.BOLD, 27);
        g.setFont(font);
        if (players == 1){
        if(compWin){
            g.drawString("The emotionless machine has won the duel",40, 200);
        }
        else{
            g.drawString("The human has prevailed over CPU darkness",40, 200);
        }
        }
        else
        {
            if(p1Won)
            {
                g.drawString("Duelist 1 has bested duelist 2",40, 200);
            }
            else if (p2Won)
            {
                g.drawString("Duelist 2 has bested duelist 1",40, 200);
            }
        }
        g.drawString("The board has changed...", 120, 240);
        font = new Font("Helvetica", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString("Click anywhere to return", 220, 500);
        firstRedraw = true; //this resets the countdown for future duels
        drawSmokeLeft = false;
        drawSmokeRight = false;
        nanoTime1 = 0;
        nanoTime2 = 0;
        p1Won = false;
        p2Won = false;
        compDuelWin = false;
        firstRedraw = true;
        if(turn == 1)
        turn = 2;
        else
        turn = 1;
        duelWinDrawn = true;
    }
    
    public static void countdown(int count, Graphics g){
                int x = (int) (3*Math.random() + 1);
            for(int i = 0; i <= 1000000000; i++){
                for(int j = 0; i <= 1000000000; i++){}
            }
            
        
            System.out.println(count);


            if(count == 1){
                for(int i = 0; i <= 10000000; i++){}
                System.out.println("Fire");
            }


            count--;
            if(count >= 1)
                countdown(count, g);
                
                firstRedraw = false;
    }
    
    public void keyPressed(KeyEvent e)
    {
        
        
            //add variations for player cpu and which side they are on
        if(e.getKeyChar() == 'f' && screen == 7){
            drawSmokeLeft = true;
            if(drawSmokeRight)
            {
                drawSmokeLeft = false;
            }
            nanoTime1 = System.nanoTime();
        }
        if(e.getKeyChar() == 'j' && screen == 7){
            drawSmokeRight = true;
            if(drawSmokeLeft = true)
            {
                drawSmokeRight = false;
            }
            nanoTime2 = System.nanoTime();
        }
        
        repaint();
    }
    
    public void keyReleased(KeyEvent e)
    {
       
    }
    public void keyTyped(KeyEvent e)
    {
    
    }
}

