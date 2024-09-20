package ui;


import model.Sudoku;
import model.SudokuNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class SudokuBoard extends JPanel implements ActionListener, MouseListener {
    String label;
    static Sudoku sudoku;
    static JLabel message;
    static JTextField[][] cells;
    JButton solve;
    JButton reset;

    public SudokuBoard() {

        setLayout(null);
        setVisible(false);

        Font f1 = new Font("Georgia", Font.BOLD, 25);
        Font f2 = new Font("Georgia", Font.PLAIN,16);
        sudoku = new Sudoku("Your Sudoku");
        cells = new JTextField[9][9];

        generateCells(f1);

        // Solve button
        solve = new JButton("Solve");
        solve.setBounds(50, 0, 150, 60);
        solve.setBackground(Color.BLUE);
        solve.setForeground(Color.BLACK);
        solve.setFont(new Font("Georgia", Font.BOLD, 25));
        solve.setFocusPainted(false);
        solve.addActionListener(this);
        solve.addMouseListener(this);

        // Reset Button
        reset = new JButton("Reset");
        reset.setBounds(225, 0, 150, 60);
        reset.setBackground(Color.BLUE);
        reset.setForeground(Color.BLACK);
        reset.setFont(new Font("Georgia", Font.BOLD, 25));
        reset.setFocusPainted(false);
        reset.addActionListener(this);
        reset.addMouseListener(this);

        // Message
        message = new JLabel();
        message.setText("Input your sudoku");
        message.setBounds(450,0,200,100);
        message.setForeground(Color.BLACK);
        message.setOpaque(false);
        message.setBorder(null);
        message.setLayout(null);
        message.setFont(f2);

        add(solve);
        add(reset);
        add(message);
    }

    private void generateCells(Font f1) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setBounds(50 + (j * 63), 80 + (i * 63), 45, 45);
                cells[i][j].setLayout(null);
                cells[i][j].setFont(f1);
                cells[i][j].setText("");
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setForeground(Color.BLACK);
                cells[i][j].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                    }
                });
                cells[i][j].setBackground(Color.GRAY);
                if (((i < 3 || i >= 6) && j >= 3 && j < 6) || (i >= 3 && i < 6 && (j < 3 || j >= 6))) {
                    cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                } else {
                    cells[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
                }
                add(cells[i][j]);
            }
        }
    }


    public static void resets() {
        message.setText("Input another sudoku");
        message.setForeground(Color.BLACK);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.getGrid()[i][j] = new SudokuNumber(0);
                cells[i][j].setEditable(true);
                cells[i][j].setForeground(Color.black);
                cells[i][j].setText("");
            }
        }
    }

    public static boolean isValueValid() {
        int f = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].getText().length() > 1) {
                    f = 1;
                    break;
                }
                if (cells[i][j].getText().equals("")) {
                    sudoku.getGrid()[i][j] = new SudokuNumber(0);
                } else {
                    sudoku.getGrid()[i][j] = new SudokuNumber(Integer.parseInt(cells[i][j].getText()));
                }
            }
            if (f == 1) {
                break;
            }
        }
        return f != 1;
    }

    public static void printSudokuSuccess() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setForeground(Color.GREEN);
                cells[i][j].setText("" + sudoku.getGrid()[i][j].getNumber());
                cells[i][j].setEditable(false);
            }

        }

    }

    public static void printSudokuFail() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.getGrid()[i][j].getNumber() != 0) {
                    cells[i][j].setForeground(Color.RED);
                    cells[i][j].setText("" + sudoku.getGrid()[i][j].getNumber());
                    cells[i][j].setEditable(false);
                }
            }

        }

    }



    public static boolean isValidBoard() {
        HashSet<SudokuNumber> set;
        set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.getGrid()[i][j].getNumber() != 0) {
                    int s = set.size();
                    set.add(sudoku.getGrid()[i][j]);
                    if (set.size() == s) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void actionPerformed(ActionEvent ae) {

        label = ae.getActionCommand();
        if (label.equals("Solve")) {
            if (isValueValid() && isValidBoard() && sudoku.hasStartingSolution(sudoku.getGrid())) {
                if (sudoku.hasSolution(sudoku.getGrid())) {
                    printSudokuSuccess();
                    message.setForeground(Color.GREEN);
                    message.setText("Solved Successfully!");

                } else {
                    printSudokuFail();
                    message.setForeground(Color.RED);
                    message.setText("No Solution");
                }

            } else {
                printSudokuFail();
                message.setForeground(Color.RED);
                message.setText("No Solution");
            }
        } else if (label.equals("Reset")) {
            resets();
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        AbstractButton event = (AbstractButton) e.getSource();
        if (event.equals(solve)) {
            event.setBackground(Color.CYAN);
            event.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.white));
        } else if (event.equals(reset)) {
            event.setBackground(Color.CYAN);
            event.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.white));
        }
    }

    public void mouseExited(MouseEvent e) {
        AbstractButton event = (AbstractButton) e.getSource();
        if (event.equals(solve)) {
            event.setBackground(Color.BLUE);
            event.setBorder(null);
        } else if (event.equals(reset)) {
            event.setBackground(Color.BLUE);
            event.setBorder(null);
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

}
