import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Visual extends JFrame {

    private static final int ARRAY_SIZE = 100;
    public List<Integer> values = new ArrayList<>();

    public Visual() {
        super("Sort Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        for (int i = 1; i <= ARRAY_SIZE; i++) {
            values.add(i);
        }
        Collections.shuffle(values);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSquares(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1200, 800);
            }
        };

        String[] algorithms = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort", "Heap Sort"};
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);
        panel.add(algorithmComboBox);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            Collections.shuffle(values);
            repaint();
        });
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            int idx = algorithmComboBox.getSelectedIndex();
            okButton.setEnabled(false);
            resetButton.setEnabled(false);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    if (idx == 0) {
                        Main.bubbleSort(values);
                    } else if (idx == 1) {
                        Main.insertionSort(values);
                    } else if (idx == 2) {
                        Main.selectionSort(values);
                    } else if (idx == 3) {
                        Main.quickSort(values, 0, values.size() - 1);
                    } else if (idx == 4) {
                        Main.mergeSort(values);
                    } else if (idx == 5) {
                        List<Integer> l = new ArrayList<>(values);
                        values = new ArrayList<>();
                        Main.heapSort(l, values);
                    }
                    okButton.setEnabled(true);
                    resetButton.setEnabled(true);
                    return null;
                }
            };
            worker.execute();
        });

        panel.add(okButton);
        panel.add(resetButton);
        add(panel);
        setVisible(true);
    }

    private void drawSquares(Graphics g) {
        int startX = 30;
        int startY = 50;
        int size = (getWidth() - 2 * startX) / ARRAY_SIZE - 4;

        for (int value : values) {
            int squareSize = (int) ((double) value / ARRAY_SIZE * (getHeight() - 2 * startY));
            g.setColor(new Color(0, 100, 255));
            g.fillRect(startX, startY, size, squareSize);
            startX += size + 4;
        }
    }
}

