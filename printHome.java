import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class printHome extends JFrame {

    public printHome() {

        setTitle("PRINT SPOOLER SYSTEM");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel title = new JLabel("PRINT SPOOLER SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 45));
        title.setForeground(Color.WHITE);

        JButton addBtn = createButton("Add Job");
        JButton removeBtn = createButton("Remove Job");
        JButton viewBtn = createButton("View Job");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridy++;
        panel.add(addBtn, gbc);

        gbc.gridy++;
        panel.add(removeBtn, gbc);

        gbc.gridy++;
        panel.add(viewBtn, gbc);

        add(panel);

        addBtn.addActionListener(e -> {
            new AddJob().setVisible(true);
            dispose();
        });

        removeBtn.addActionListener(e -> {
            new RemoveJob().setVisible(true);
            dispose();
        });

        viewBtn.addActionListener(e -> {
            new ViewJob().setVisible(true);
            dispose();
        });
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(220, 55));
        return btn;
    }

    public static void main(String[] args) {
        new printHome().setVisible(true);
    }
}