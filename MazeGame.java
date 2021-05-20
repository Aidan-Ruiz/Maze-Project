//Aidan Ruiz
import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.event.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import java.net.*;

public class MazeGame extends Application
{
   //creating variables
   int x = 0;
   int y = 0;

   //creating a border pane
   BorderPane main = new BorderPane();
   //creating a canvas
   MazeGameCanvas canvas = new  MazeGameCanvas();
     
   GraphicsContext gc = canvas.getGraphicsContext2D();
   //creating a 2D array
   int[][] maze = new int[21][21];

   
    
           
   public void start(Stage stage)
   {
    try
     {
     //Reads from the other class with all the 0 and 1 
     Scanner scan = new Scanner(new File("MazeBorder.txt"));
     
     for(int i=0; i< maze.length; i++)
     {
      for(int j=0; j < maze.length; j++)
      {
      //Have to read backwords for it to work
         maze[j][i] = scan.nextInt();
      }


     }
     
     
       //draw from canvas
       canvas.draw();
       //add canvas to the main
       main.getChildren().add(canvas);
       
    //set the scene to 525, 525   
     Scene scene = new Scene(main, 525, 525);
     stage.setTitle("Escape the Maze!");
     stage.setScene(scene);
     stage.show();
     //Whatever you press on your keyboard it will be readed and sent to the main 
      main.setOnKeyPressed(new KeyListener());
     
     //main request focus
     main.requestFocus();

               
     }
    //Catches the exception     
    catch(FileNotFoundException fnfe)
      {
         System.out.println("File does not exist");
      }
      
  }
      //creating the canvas class  
     public class MazeGameCanvas extends Canvas
     {
      public MazeGameCanvas()
      {
         //setting width and height to 525
         setWidth(525);
         setHeight(525);
      }
      //draw method
      public void draw()
      {
      for(int i = 0; i < 21; i++)
      {
         for(int j = 0; j < 21; j++)
         {
            //for every 0 in the txt file make it white
               if (maze[i][j] == 0)
               {
                  gc.setFill(Color.WHITE);
               }
               //for every 1 in the text file make it black
               if (maze[i][j] == 1)
               {
                  gc.setFill(Color.BLACK);
               }
               //creating them as rectangles
               gc.fillRect(i*25, j*25, 25, 25);
         }
      }
               for (int j=0;j<21;j++)
              {
              if(maze[j][0] == 0)
              {
                  x=j*25;               
              }
             }
             //creating the cyan box at its starting location
             gc.setFill(Color.CYAN);
             gc.fillRect(x,y,25,25);
                      
         }
         
       }  
               
          
      //boxDraw is to move you cyan rectangle   
      public void boxDraw()
      {
         gc.clearRect(x,y,25,25);
         gc.fillRect(x,y,25,25);
      }
      
      //keylistener allows me to move the box around the maze
      public class KeyListener implements EventHandler<KeyEvent>
      {
         public void handle(KeyEvent event)
         {
          
            //you clear rect because then it will turn out like a snake
            gc.clearRect(x,y,25,25);
            //when you press up on the keys the box goes up
            if(event.getCode() == KeyCode.UP)
            {
               //makes sure you can leave the maze going up
               if(y>0)
               {
               //makes sure you are only in the white area
               if(maze[x/25][y/25-1]==0)
               {
               //everytime you move up you subtract 25 from y
               y = y-25;
               }
                                    
                }
             }
            //when you press down on the keys the box goes down
             if (event.getCode() == KeyCode.DOWN)
            {
               //makes sure you can leave the maze going down            
               if(y<500)
               {
               //makes sure you are only in the white area             
               if(maze[x/25][y/25+1]==0)
               {
               //everytime you move down you add 25 from y               
                y = y+25;
                }  
               }
             }
             
             if (event.getCode() == KeyCode.LEFT)
            {
               //makes sure you can leave the maze going left            
               if(x>0)
               {
               //makes sure you are only in the white area               
               if(maze[x/25-1][y/25]==0)
               {
               //everytime you move left you subtract 25 from x               
                  x = x-25;
               }
                 
               }
             }
             if (event.getCode() == KeyCode.RIGHT)
            {
               //makes sure you can leave the maze going right                        
               if(x<500)
               {
               //makes sure you are only in the white area
               if(maze[x/25+1][y/25]==0)
               {
               //everytime you move right you add 25 from x               
                  x = x+25;
               }
                }
             }
             
             boxDraw();
             
             if(y/25 == 20)
             {
             //When they square reaches the end, they win the game
             if(maze[x/25][y/25]==0)
             {
               System.out.println("CONGRATS YOU ESCAPED!!!!");
             }
             }
       
      }
      
      
   }
 
}


