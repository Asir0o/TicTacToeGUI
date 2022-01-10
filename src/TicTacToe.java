import javax.swing.*;

public class TicTacToe {

    private char[][] playingField;

    TicTacToe(char[][] playingField) {
        this.playingField = playingField;
    }

    public void setPlayingField(String text, int index1, int index2) {
        this.playingField[index1][index2] = text.charAt(0);
    }

    public int countX(char[][] playingField) {
        int countX = 0;

        for (char[] chars : playingField) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    countX++;
                }
            }
        }
        return countX;
    }

    public int countO(char[][] playingField) {
        int countO = 0;

        for (char[] chars : playingField) {
            for (char aChar : chars) {
                if (aChar == 'O') {
                    countO++;
                }
            }
        }
        return countO;
    }

//    public void game(Frame frame) {
//        whoWin(frame);
//    }

    public void whoWin(Frame frame) {
        int countX = countX(playingField);
        int countO = countO(playingField);
        int sumX = 0;
        int sumO = 0;
        boolean winX = false;
        boolean winO = false;
        boolean draw = countX + countO == 9;
        boolean notImpossible = Math.abs(countX - countO) > 1;
        boolean end = false;

        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField[i].length; j++) {
                if (i == j) {                                  //считает количество X и O по главной диагонали
                    if (playingField[i][j] == 'X') {               //если сумма Х будет равна 264 победили Х
                        sumX += 88;                            //если сумма О будет равна 237 победили О
                    } else if (playingField[i][j] == 'O') {
                        sumO += 79;
                    }
                }
                //ищет победителя по вертикали
                if (playingField[0][j] + playingField[1][j] + playingField[2][j] == 264) {
                    winX = true;
                    end = true;
                }
                if (playingField[0][j] + playingField[1][j] + playingField[2][j] == 237) {
                    winO = true;
                    end = true;
                }
            }
            //ищет победителя по горизонтали
            if (playingField[i][0] + playingField[i][1] + playingField[i][2] == 264 || sumX == 264) {
                winX = true;
                end = true;
            } else if (playingField[i][0] + playingField[i][1] + playingField[i][2] == 237 || sumO == 237) {
                winO = true;
                end = true;
            }
        }
        //ищет победителя по обратной диагонали
        if (playingField[2][0] + playingField[1][1] + playingField[0][2] == 264) {
            winX = true;
            end = true;
        } else if (playingField[2][0] + playingField[1][1] + playingField[0][2] == 237) {
            winO = true;
            end = true;
        }

        if (notImpossible || (winX && winO)) {
            end = true;
        } else if (draw) {
            end = true;
        }


        if (winX && !winO) {
            frame.createDialog("X wins");
        } else if (winO && !winX) {
            frame.createDialog("O wins");
        } else if (notImpossible || (winX && winO)) {
            frame.createDialog("Impossible");
        } else if (draw) {
            frame.createDialog("Draw");
        }
    }
}
