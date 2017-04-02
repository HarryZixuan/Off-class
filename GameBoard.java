
import javafx.scene.paint.Color;

public class GameBoard {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    public static final int MAX_GAME_PIECES = 15;

    private GamePiece[]     gamePieces;
    private int             numGamePieces;
    private boolean         completed;

    public GameBoard(int numPieces) {
        gamePieces = new GamePiece[numPieces];
        numGamePieces = 0;
        completed = false;
    }

    public static GameBoard board1() {
        GameBoard b = new GameBoard(2);
        b.add(new VerticalGamePiece(2, Color.YELLOW, 5, 0));
        b.add(new GoalPiece(1, 2));
        return b;
    }

    public static GameBoard board2() {
        GameBoard b = new GameBoard(8);
        b.add(new GoalPiece(1, 2));
        b.add(new HorizontalGamePiece(2, Color.LIGHTGREEN, 0, 0));
        b.add(new HorizontalGamePiece(2, Color.LIGHTBLUE, 4, 4));
        b.add(new HorizontalGamePiece(3, Color.GREEN, 2, 5));
        b.add(new VerticalGamePiece(3, Color.YELLOW, 5, 0));
        b.add(new VerticalGamePiece(3, Color.PURPLE, 0, 1));
        b.add(new VerticalGamePiece(3, Color.BLUE, 3, 1));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 0, 4));
        return b;
    }

    public static GameBoard board3() {
        GameBoard b = new GameBoard(9);
        b.add(new GoalPiece(1, 2));
        b.add(new HorizontalGamePiece(2, Color.PINK, 3, 4));
        b.add(new HorizontalGamePiece(2, Color.PURPLE, 0, 5));
        b.add(new VerticalGamePiece(3, Color.GOLD, 0, 0));
        b.add(new VerticalGamePiece(3, Color.VIOLET, 3, 0));
        b.add(new VerticalGamePiece(3, Color.BLUE, 5, 2));
        b.add(new VerticalGamePiece(2, Color.GREEN, 5, 0));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 4, 2));
        b.add(new VerticalGamePiece(2, Color.LIGHTBLUE, 2, 4));
        return b;
    }

    public static GameBoard board4() {
        GameBoard b = new GameBoard(13);
        b.add(new GoalPiece(2, 2));
        b.add(new HorizontalGamePiece(3, Color.BLUE, 0, 0));
        b.add(new HorizontalGamePiece(2, Color.PINK, 1, 1));
        b.add(new HorizontalGamePiece(2, Color.LIGHTGREEN, 0, 3));
        b.add(new HorizontalGamePiece(2, Color.YELLOW, 2, 5));
        b.add(new HorizontalGamePiece(2, Color.BROWN, 4, 4));
        b.add(new HorizontalGamePiece(2, Color.GRAY, 4, 5));
        b.add(new VerticalGamePiece(3, Color.GOLD, 4, 0));
        b.add(new VerticalGamePiece(2, Color.SADDLEBROWN, 3, 0));
        b.add(new VerticalGamePiece(2, Color.GREEN, 5, 0));
        b.add(new VerticalGamePiece(2, Color.PURPLE, 0, 1));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 2, 3));
        b.add(new VerticalGamePiece(2, Color.LIGHTBLUE, 1, 4));
        return b;
    }


    public static GameBoard board5() {
        GameBoard b = new GameBoard(13);
        b.add(new GoalPiece(3, 2));
        b.add(new HorizontalGamePiece(2, Color.LIGHTGREEN, 1, 0));
        b.add(new HorizontalGamePiece(3, Color.BLUE, 0, 3));
        b.add(new HorizontalGamePiece(2, Color.BLACK, 4, 4));
        b.add(new HorizontalGamePiece(2, Color.BROWN, 0, 5));
        b.add(new HorizontalGamePiece(2, Color.YELLOW, 3, 5));
        b.add(new VerticalGamePiece(3, Color.GOLD, 0, 0));
        b.add(new VerticalGamePiece(2, Color.LIGHTBLUE, 1, 1));
        b.add(new VerticalGamePiece(2, Color.PINK, 2, 1));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 4, 0));
        b.add(new VerticalGamePiece(3, Color.VIOLET, 5, 1));
        b.add(new VerticalGamePiece(2, Color.PURPLE, 3, 3));
        b.add(new VerticalGamePiece(2, Color.GREEN, 2, 4));
        return b;
    }

    public void add(GamePiece gp) {
        if (numGamePieces < MAX_GAME_PIECES)
            gamePieces[numGamePieces++] = gp;
    }

    public int getNumGamePieces() { return numGamePieces; }
    public GamePiece[] getGamePieces() { return gamePieces; }
    public boolean isCompleted() { return completed; }

    // Return the piece at the given position
    public GamePiece pieceAt(int x, int y) {
    	GamePiece result = null;
    	
    	for(GamePiece gp : gamePieces){
    		if(gp instanceof HorizontalGamePiece){
    			if((gp.getTopLeftY() == y) && (((gp.getTopLeftX() <= x) && (gp.getTopLeftX() + gp.getWidth() - 1) >= x))){
    				return gp;
    			}
    		}else if(gp instanceof VerticalGamePiece){
    			if((gp.getTopLeftX() == x) && (((gp.getTopLeftY() <= y) && (gp.getTopLeftY() + gp.getHeight() - 1) >= y))){
    				return gp;
    			}
    		}
    	}
        return result;
    }

    // Check if the board has been completed, and set the boolean accordingly
    public void checkCompletion(GamePiece gp) {
    	if(gp instanceof GoalPiece){
    		if((gp.getTopLeftX() == 5) && (gp.getTopLeftY() == 2)){
    			completed = true;
    		}
    	}
    }
}


import javafx.scene.paint.Color;

public abstract class GamePiece {
    protected int      width;
    protected int      height;
    protected Color    color;
    protected int      topLeftX;
    protected int      topLeftY;

    public GamePiece(int w, int h, Color c, int x, int y) {
        width = w;
        height = h;
        color = c;
        topLeftX = x;
        topLeftY = y;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }
    public int getTopLeftX() { return topLeftX; }
    public int getTopLeftY() { return topLeftY; }

    public void moveRight() { topLeftX += 1;}
    public void moveLeft() { topLeftX -= 1;}
    public void moveDown() { topLeftY += 1;}
    public void moveUp() { topLeftY -= 1;}

    public boolean canMoveLeftIn(GameBoard b) { return false; }
    public boolean canMoveRightIn(GameBoard b) { return false; }
    public boolean canMoveDownIn(GameBoard b) { return false; }
    public boolean canMoveUpIn(GameBoard b) { return false; }
}



import javafx.scene.paint.Color;

public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
    }
    public boolean canMoveRightIn(GameBoard b) {
    	int x = this.getTopLeftX() + this.getWidth() - 1;
    	int y = this.getTopLeftY();
    	boolean result = true;
    	
    	if((x + 1) > 6){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if(gp.getTopLeftX() == x + 1 && gp.getTopLeftY() == y){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == x + 1) && (gp.getTopLeftY() <= y && (gp.getTopLeftY() + gp.getHeight() - 1 >= y))){
    					return false;
    				}
    			}
    		}
    	}
        return result;
    }
}


import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }
    
    public boolean canMoveLeftIn(GameBoard b) {
    	int x = this.getTopLeftX();
    	int y = this.getTopLeftY();
		boolean result = true;
		//set up new variables for making the program shorter
		//same in vertical and goal classes

    	
    	if((x - 1) < 0){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){ //(for(int i=0; i<numOfGamePieces;i++)//GamePiece gp = new GamePiece
    			if(gp instanceof HorizontalGamePiece){
    				if(((gp.getTopLeftX() + gp.getWidth() - 1) == (x - 1)) && (gp.getTopLeftY() == y)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == (x - 1)) && ((gp.getTopLeftY() <= y) && (gp.getTopLeftY() + gp.getHeight() - 1) >= y)){
    					return false;
    				}
    			}
    		}
    	}
    	return result;
    }
    
    public boolean canMoveRightIn(GameBoard b) {
    	int x = this.getTopLeftX() + this.getWidth() - 1;
    	int y = this.getTopLeftY();
    	boolean result = true;
    	
    	if((x + 1) > 5){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if((gp.getTopLeftX() == x + 1) && (gp.getTopLeftY() == y)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == (x + 1)) && (gp.getTopLeftY() <= y) && ((gp.getTopLeftY() + gp.getHeight() - 1) >= y)){
    					return false;
    				}
    			}
    		}
    	}
    	
    	return result;
    }
    
    
    
    
    
}

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame    model;
    private SliderPuzzleView    view;

    private GamePiece           selectedPiece;
    private boolean             justGrabbed;
    private int                 lastX;
    private int                 lastY;

    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);

        // Add event handlers to the inner game board buttons
        for (int w=1; w<=(GameBoard.WIDTH); w++) {
            for (int h=1; h<=(GameBoard.HEIGHT); h++) {
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionSelection(mouseEvent);
                    }
                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
								handleGridSectionMove(mouseEvent);
							
                    }
                });
            }
        }

        // Plug in the Start button and NeaxtBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.startBoard();
                view.update();
            }
        });
        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });

        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.WIDTH+2),60+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.HEIGHT+2)));
        primaryStage.show();

        // Update the view upon startup
        view.update();
    }


    private void handleGridSectionSelection(MouseEvent mouseEvent) {
    	outerloop:
    	for(int x = 1; x < 7; x++){
    		for(int y = 1; y < 7; y++){
    			if(view.getGridSection(x, y) == mouseEvent.getSource()){
    				selectedPiece = model.getCurrentBoard().pieceAt(x - 1, y - 1);
    				lastX = (int)mouseEvent.getX();
    				lastY = (int)mouseEvent.getY();
    				justGrabbed = true;
    				break outerloop;
    			}
    		}
    	}
    }
    
    private void handleGridSectionMove(MouseEvent mouseEvent){
        int currentGridX = (int)mouseEvent.getX();
        int currentGridY = (int)mouseEvent.getY();
        int deltaX = Math.abs(currentGridX - lastX);
        int deltaY = Math.abs(currentGridY - lastY);
        
        // Slide horizontally
        if(deltaX > deltaY){
        	if(deltaX >= SliderPuzzleView.GRID_UNIT_SIZE){
        		// Slide right 
        		if(currentGridX - lastX > 0){
        			if(selectedPiece.canMoveRightIn(model.getCurrentBoard())){
        				selectedPiece.moveRight();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();
        				if(justGrabbed){
        					model.makeAMove();
        					justGrabbed = false;
        				}
        			}
        		// Slide left	
        		}else{
        			if(selectedPiece.canMoveLeftIn(model.getCurrentBoard())){
        				selectedPiece.moveLeft();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();
        				if(justGrabbed){
            				model.makeAMove();
            				justGrabbed = false;
            				}
        			}
        		}
        	}
        // Slide up	
        }else if(deltaY > deltaX){
        	if(deltaY >= SliderPuzzleView.GRID_UNIT_SIZE){
        		if(currentGridY - lastY > 0){
        			if(selectedPiece.canMoveDownIn(model.getCurrentBoard())){
        				selectedPiece.moveDown();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();
        				if(justGrabbed){
            				model.makeAMove();
            				justGrabbed = false;
            				}
        			}
        		// Slide down	
        		}else{
        			if(selectedPiece.canMoveUpIn(model.getCurrentBoard())){
        				selectedPiece.moveUp();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();	
        				if(justGrabbed){
            				model.makeAMove();
            				justGrabbed = false;
            				}
        			}
        		}
        	}
        }
        
        if(selectedPiece instanceof GoalPiece){
        	model.getCurrentBoard().checkCompletion(selectedPiece);
        	if(model.getCurrentBoard().isCompleted()){
        		model.completeBoard();
        	}
        }
        view.update();
    }
    
    public static void main(String[] args) { launch(args); }
}




public class SliderPuzzleGame {
    private static final int  NUM_BOARDS = 5;
    private static final byte WAITING_TO_START = 0;
    private static final byte BOARD_IN_PROGRESS = 1;
    private static final byte BOARD_JUST_COMPLETED = 2;

    private GameBoard[]     boards;
    private int             boardNumber;
    private int             numMovesMade;
    private byte            gameMode;

    public SliderPuzzleGame() {
        resetBoards();
    }

    public GameBoard getCurrentBoard() { return boards[boardNumber]; }
    public int getNumberOfMovesMade() { return numMovesMade; }

    public boolean isBoardInProgress() { return gameMode == BOARD_IN_PROGRESS; }
    public boolean areWeWaitingToStartABoard() { return gameMode == WAITING_TO_START; }

    // Set up the boards to the 5 example boards
    private void resetBoards() {
        boards = new GameBoard[NUM_BOARDS + 1];
        boards[0] = new GameBoard(0);
        boards[1] = GameBoard.board1();
        boards[2] = GameBoard.board2();
        boards[3] = GameBoard.board3();
        boards[4] = GameBoard.board4();
        boards[5] = GameBoard.board5();
        boardNumber = 1;
        gameMode = WAITING_TO_START;
        numMovesMade = 0;
    }

    // Record that a move was made
    public void makeAMove() {
        numMovesMade++;
    }

    // Allow the user to start playing the current board
    public void startBoard() {
        gameMode = BOARD_IN_PROGRESS;
    }

    // Simulate the completion of the current board
    public void completeBoard() {
        gameMode = BOARD_JUST_COMPLETED;
    }

    // Go to the next board in the game
    public void moveToNextBoard() {
        boardNumber++;
        gameMode = WAITING_TO_START;
        numMovesMade = 0;

        // Reset all the boards if we completed them all
        if (boardNumber > NUM_BOARDS)
            resetBoards();
    }
}



import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SliderPuzzleView extends Pane {
    public static final int GRID_UNIT_SIZE = 40;

    private Button[][]     gridSections;
    private Button         startButton, nextBoardButton;
    private TextField      numMovesField;

    private SliderPuzzleGame    model;

    public SliderPuzzleView(SliderPuzzleGame m) {
        model = m;

        gridSections = new Button[GameBoard.WIDTH+2][GameBoard.HEIGHT+2];

        // Create the wall (i.e., non-pressable) buttons
        for (int i=0; i<8; i++) {
            gridSections[i][0] = new Button();
            gridSections[i][0].setDisable(true);
            gridSections[i][0].relocate(i * GRID_UNIT_SIZE, 0);
            gridSections[i][0].setPrefSize(GRID_UNIT_SIZE, GRID_UNIT_SIZE);
            gridSections[i][0].setStyle("-fx-base: BLACK");
            gridSections[i][7] = new Button();
            gridSections[i][7].setDisable(true);
            gridSections[i][7].relocate(i * GRID_UNIT_SIZE, 7 * GRID_UNIT_SIZE);
            gridSections[i][7].setPrefSize(GRID_UNIT_SIZE, GRID_UNIT_SIZE);
            gridSections[i][7].setStyle("-fx-base: BLACK");
        }
        for (int i=1; i<7; i++) {
            gridSections[0][i] = new Button();
            gridSections[0][i].setDisable(true);
            gridSections[0][i].relocate(0, i * GRID_UNIT_SIZE);
            gridSections[0][i].setPrefSize(GRID_UNIT_SIZE, GRID_UNIT_SIZE);
            gridSections[0][i].setStyle("-fx-base: BLACK");

            gridSections[7][i] = new Button();
            gridSections[7][i].setDisable(true);
            gridSections[7][i].relocate(7 * GRID_UNIT_SIZE, i * GRID_UNIT_SIZE);
            gridSections[7][i].setPrefSize(GRID_UNIT_SIZE, GRID_UNIT_SIZE);


            if (i != 3) {
                gridSections[7][i].setStyle("-fx-base: BLACK");
            }
            else {
                gridSections[7][i].setStyle("-fx-font:10 arial; -fx-base: WHITE");
                gridSections[7][i].setText("EXIT");
          
            }
        }

        // Create the inner "pressable" Buttons
        for (int w=1; w<7; w++) {
            for (int h=1; h<7; h++) {
                gridSections[w][h] = new Button();
                gridSections[w][h].relocate(w * GRID_UNIT_SIZE, h * GRID_UNIT_SIZE);
                gridSections[w][h].setPrefSize(GRID_UNIT_SIZE, GRID_UNIT_SIZE);
                gridSections[w][h].setStyle("-fx-base: WHITE; -fx-text-fill: RED;");
                gridSections[w][h].setFocusTraversable(false);
            }
        }
        

        // Add all the buttons to the window
        for (int w=0; w<8; w++) {
            for (int h=0; h<8; h++) {
                //if (!((h == 3) && (w == 7)))
                    getChildren().add(gridSections[w][h]);
            }
        }
        // Add the Start and NextBoard buttons
        startButton = new Button("Start");
        startButton.setStyle("-fx-font:15 arial");
        startButton.relocate(10, (20 + 8 * GRID_UNIT_SIZE));
        startButton.setPrefSize(100, 25);

        nextBoardButton = new Button("Next Board");
        nextBoardButton.setStyle("-fx-font:15 arial");
        nextBoardButton.relocate(120, (20 + 8 * GRID_UNIT_SIZE));
        nextBoardButton.setPrefSize(100, 25);

        // Add the Num Moves Field
        numMovesField = new TextField("");
        numMovesField.setStyle("-fx-font:15 arial");
        numMovesField.setEditable(false);
		numMovesField.setAlignment(Pos.BOTTOM_RIGHT);
        numMovesField.relocate(260, (20 + 8 * GRID_UNIT_SIZE));
        numMovesField.setPrefSize(50, 25);
        numMovesField.setDisable(true);
        getChildren().addAll(startButton, nextBoardButton, numMovesField);

        update(); // Update with no board
    }

    public Button getGridSection(int w, int h) {
        return gridSections[w][h];
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getNextBoardButton() {
        return nextBoardButton;
    }

    public void update() {
    	
    	// Reset the colors of the buttons to white
    	for(int x = 1; x < 7; x++){
    		for(int y = 1; y < 7; y++){
                gridSections[x][y].setStyle("-fx-base: WHITE; -fx-text-fill: RED;");
    		}
    	}
    	
        // Update the colors of the buttons based on the GamePieces
    	for(int x = 1; x < 7; x++){
    		for(int y = 1; y < 7; y++){
    			GamePiece gp = model.getCurrentBoard().pieceAt((x - 1), (y - 1));
    			if(gp != null){
    				gridSections[x][y].setStyle("-fx-base: #" + gp.getColor().toString().substring(2, 8));
    			}
    		}
    	}
    	
    	// Update the Start and NextBoard buttons
		startButton.setDisable(! model.areWeWaitingToStartABoard());
		nextBoardButton.setDisable(model.areWeWaitingToStartABoard());
		
    	
        
    	
        // Disable all the board buttons unless we are in progress of playing a board
    	for(int x = 1; x < 7; x++){
    		for(int y = 1; y < 7; y++){
    			gridSections[x][y].setDisable(! model.isBoardInProgress());
    		}
    	}
    		
    		
        				
      
    	
        // Update the number of moves field
    	numMovesField.setText(Integer.toString(model.getNumberOfMovesMade()));
    }
}


import javafx.scene.paint.Color;

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {
    	int x = this.getTopLeftX();
    	int y = this.getTopLeftY() + this.getHeight() - 1;
    	boolean result = true;
    	
    	if((y + 1) > 5){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if(((gp.getTopLeftX() <= x) && ((gp.getTopLeftX() + gp.getWidth() - 1) >= x)) && (gp.getTopLeftY() == y + 1)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == x) && (gp.getTopLeftY() == y + 1)){
    					return false;
    					}
    				}
    			}
    		}
        return result;
    }
    public boolean canMoveUpIn(GameBoard b) {
    	int x = this.getTopLeftX();
    	int y = this.getTopLeftY();
    	boolean result = true;
    	
    	if((y - 1) < 0){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if((gp.getTopLeftX() <= x) && ((gp.getTopLeftX() + gp.getWidth() - 1) >= x) && (gp.getTopLeftY() == y - 1)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == x) && ((gp.getTopLeftY() + gp.getHeight() - 1) == y - 1)){
    					return false;
    				}
    			}
    		}
    	}
        return result;
    }
}