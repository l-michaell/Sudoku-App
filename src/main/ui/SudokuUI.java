package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class SudokuUI extends JFrame implements ActionListener, MouseListener {
    private static final int HEIGHT = 900;
    private static final int WIDTH = 700;
    JButton close;

    public SudokuUI() {
        setLayout(null);
        setBounds(200, 50, WIDTH, HEIGHT);
        setUndecorated(true);
        setVisible(true);

        setTitle("Sudoku Solver");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        // Close button
        close = new JButton("X");
        close.setFont(new Font("Arial", Font.BOLD, 16));
        close.setForeground(Color.WHITE);
        close.setBackground(Color.RED);
        close.setFocusPainted(false);
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.addActionListener(this);
        close.addMouseListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(close);

        // Sudoku board
        SudokuBoard board = new SudokuBoard();
        board.setBounds(0, 0, WIDTH, HEIGHT);
        board.setBackground(Color.LIGHT_GRAY);

        // Add components to the frame
        add(board);
        add(buttonPanel, BorderLayout.NORTH);

        // Make the frame visible
        board.setVisible(true);
        buttonPanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == close) {
            this.dispose();
            Iterator<Event> iterator = EventLog.getInstance().iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
                System.out.println();
            }
            System.exit(0);
        }
    }

    // Mouse listener methods (optional, can be left empty)
    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}