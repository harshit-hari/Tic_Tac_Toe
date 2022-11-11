import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

    class TicTacToe extends JFrame implements ActionListener {

        int BOARD_SIZE = 3;
    static enum  GameStatus{
        Incomelete,Xwins,Zero_wins,Tie
    }
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crossTurn = true;
    public TicTacToe(){
        super.setTitle("Tic Tac Toe");
        super.setSize(900,800);
        GridLayout grid = new GridLayout(BOARD_SIZE,BOARD_SIZE);
        super.setLayout(grid);
        Font font = new Font("Comic Sans", 1,150);
            for(int row =0; row <BOARD_SIZE ;row++){
            for (int col =0; col <BOARD_SIZE ; col++){
                 JButton button = new JButton("");
                buttons[row][col] = button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }
            }
                super.setResizable(false);
                super.setVisible(true);

            }
            @Override
        public void actionPerformed(ActionEvent e){
             JButton clickedButton=(JButton)e.getSource();
             makeMove(clickedButton);
             GameStatus gs = this.getGameStatus();
             if(gs == GameStatus.Incomelete){
                 return;
             }
             declareWinner(gs);
             int choice = JOptionPane.showConfirmDialog(this,"restart");
             if (choice == JOptionPane.YES_OPTION){
                 for(int row =0; row <BOARD_SIZE ;row++) {
                     for (int col = 0; col < BOARD_SIZE; col++) {
                         buttons[row][col].setText("");
                     }
                 }
                crossTurn = true;}
                 else {
                     super.dispose();
                 }
             }

           private void makeMove(JButton clickedButton){
                String btntext = clickedButton.getText();
                if(btntext.length() >0){
                    JOptionPane.showMessageDialog(this,"INVALID MOVE");
                    return;
                }
                else {
                    if (crossTurn) {
                        clickedButton.setText("X");
                    } else {
                        clickedButton.setText("0");
                    }
                   crossTurn = !crossTurn;
                }
            }
        private GameStatus getGameStatus() {
            String text1 = "", text2 = "";
            int row = 0;
            int col = 0;

            while (row < BOARD_SIZE) {
                col = 0;
                while (col < BOARD_SIZE - 1) {
                    text1 = buttons[row][col].getText();
                    text2 = buttons[row][col + 1].getText();
                    if (!text1.equals(text2) || text1.length() == 0) {
                        break;
                    }
                    col++;
                }
                if (col == BOARD_SIZE - 1) {
                    if (text1.equals("X")) {
                        return GameStatus.Xwins;
                    }
                    else {
                        System.out.println("2");
                        return GameStatus.Zero_wins;
                    }
                }
                row++;
            }
            col = 0;
            while (col < BOARD_SIZE) {
                row = 0;
                while (row < BOARD_SIZE - 1) {
                    text1 = buttons[row][col].getText();
                    text2 = buttons[row+1][col].getText();
                    if (!text1.equals(text2) || text1.length() == 0) {
                        break;
                    }
                    row++;
                }
                if (row == BOARD_SIZE - 1) {
                    if (text1.equals("X")) {
                        return GameStatus.Xwins;
                    }
                    else {
                        System.out.println("1");
                        return GameStatus.Zero_wins;
                    }
                }
                col++;
            }
            // digonal 1
            row =0;
            col =0;
            while (row< BOARD_SIZE-1){
                text1 = buttons[row][col].getText();
                text2 = buttons[row+1][col].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row++;
                col++;
            }
            if (row == BOARD_SIZE - 1) {
                if (text1.equals("X")) {
                    return GameStatus.Xwins;
                }
                else {
                    System.out.println("3");
                    return GameStatus.Zero_wins;
                }
            }

 //-- second down to up digonal
            row = BOARD_SIZE-1;
            col = 0;
            while (row > 0){
                text1 = buttons[row][col].getText();
                text2 = buttons[row-1][col+1].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row--;
                col++;
            }
            if (row == 0 ) {
                if (text1.equals("X")) {
                    return GameStatus.Xwins;
                }
                else {
                    System.out.println("4");
                    return GameStatus.Zero_wins;
                }
            }

         String txt = "";
          for (row =0; row <BOARD_SIZE; row++){
              for(col =0; col <BOARD_SIZE ;col++){
                  txt = buttons[row][col].getText();
                  if (txt.length() == 0){
                      return GameStatus.Incomelete;
                  }
              }
          }
          return GameStatus.Tie;
        }
        private void declareWinner(GameStatus gs){
        if (gs == GameStatus.Xwins){
            JOptionPane.showMessageDialog(this , " X WINS YO HO");}
        else if (gs == GameStatus.Zero_wins){
            JOptionPane.showMessageDialog(this , " 0 IS WINNER");}
        else{
                JOptionPane.showMessageDialog(this, "FIGHT UNTILL WIN -- TIE");
            }
        }

        }



public class main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
}
