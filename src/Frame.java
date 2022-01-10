import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private JPanel Panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    public final JButton[][] buttons = {{button1, button2, button3}, {button4, button5, button6},
            {button7, button8, button9}};
    private JMenu menu;
    private JMenuItem menuItem;
    boolean[] game = {true, true};
    private final TicTacToe ticTacToe;


    public Frame() {
        ticTacToe = new TicTacToe(new char[][]{{' ', ' ', ' '}, {' ', ' ', ' ',}, {' ', ' ', ' ',}});
        setTitle("Tic-Tac-Toe");
        setSize(450, 450);
        setContentPane(Panel);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mainMenu());
        menuBar.add(fieldSettings());
        menuBar.setBackground(Color.WHITE);
        setJMenuBar(menuBar);
        addAction(ticTacToe);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addAction(TicTacToe ticTacToe) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                JButton button = buttons[i][j];
                int finalI = i;
                int finalJ = j;
//                button.setText("");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (game[0]) {
                            button.setText("X");
                            game[0] = false;
                            ticTacToe.setPlayingField(button.getText(), finalI, finalJ);
                        } else {
                            button.setText("O");
                            game[0] = true;
                            ticTacToe.setPlayingField(button.getText(), finalI, finalJ);
                        }
                        button.setEnabled(false);
                    }
                });
            }
        }
    }

//    public void getText(TicTacToe ticTacToe) {
//        for (int i = 0; i < buttons.length; i++) {
//            if (!buttons[i].getText().equals("")) {
//                ticTacToe.setPlayingField(buttons[i].getText(), i);
//            }
//        }
//    }
//

    public void createDialog(String winner) {
        JDialog dialog = new JDialog(this, "Победитель", true);
        JPanel panel = new JPanel();
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game[1] = false;
                dialog.dispose();
                System.exit(0);
            }
        });

        dialog.setSize(350, 150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(winner, SwingConstants.CENTER);

        panel.add(label);
        panel.add(okButton);
        dialog.add(panel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 800;
        int sizeHeight = 600;
        int locationX = (screenSize.width - 300) / 2;
        int locationY = (screenSize.height - 150) / 2;

        dialog.setBounds(screenSize.width / 2, screenSize.height / 2, 300, 150);


        dialog.setVisible(true);
    }

    private JMenu mainMenu() {
        JMenu mainMenu = new JMenu("Menu");
        JMenuItem restart = new JMenuItem("Restart");
        mainMenu.add(restart);

        return mainMenu;
    }

    private JMenu fieldSettings() {
        JMenu fieldSettings = new JMenu("Field settings");
        JRadioButtonMenuItem fieldSetting1 = new JRadioButtonMenuItem("3x3", true);
        JRadioButtonMenuItem fieldSetting2 = new JRadioButtonMenuItem("27x27");

        fieldSetting1.setEnabled(false);

        fieldSetting2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("27x27")) {
                    fieldSetting1.setEnabled(true);
                    fieldSetting1.setSelected(false);
                    fieldSetting2.setEnabled(false);
                }
            }
        });

        fieldSettings.add(fieldSetting1);
        fieldSettings.add(fieldSetting2);

        return fieldSettings;
    }

    private void changeField() {

    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        while (frame.game[1]) {
            frame.ticTacToe.whoWin(frame);
        }
    }
}
