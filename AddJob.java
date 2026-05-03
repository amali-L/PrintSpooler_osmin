import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddJob extends JFrame {

    JTextField nameField, copiesField, typeField;

    public AddJob() {

        setTitle("Add Job");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // 🔥 Title
        JLabel title = new JLabel("ADD JOB");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        // 🔥 Fields
        nameField = createField();
        copiesField = createField();
        typeField = createField();

        // 🔥 Buttons
        JButton addBtn = createButton("Add Job");
        JButton backBtn = createButton("Back");

        // 👉 Layout

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1
        gbc.gridx = 0; gbc.gridy++;
        panel.add(createLabel("Job Name:"), gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy++;
        panel.add(createLabel("Copies:"), gbc);

        gbc.gridx = 1;
        panel.add(copiesField, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy++;
        panel.add(createLabel("File Type:"), gbc);

        gbc.gridx = 1;
        panel.add(typeField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy++;
        panel.add(addBtn, gbc);

        gbc.gridx = 1;
        panel.add(backBtn, gbc);

        add(panel);

        // 🔥 Actions
        addBtn.addActionListener(e -> addJob());

        backBtn.addActionListener(e -> {
            new printHome().setVisible(true);
            dispose();
        });
    }

    // 🔥 Styled Label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        return label;
    }

    // 🔥 Styled TextField
    private JTextField createField() {
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tf.setPreferredSize(new Dimension(260, 40));
        return tf;
    }

    // 🔥 Styled Button
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(160, 45));
        return btn;
    }

    // 🔥 Logic
    private void addJob() {
        try {
            String name = nameField.getText();
            int copies = Integer.parseInt(copiesField.getText());
            String type = typeField.getText();

            if (name.isEmpty() || type.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }

            Job job = new Job(name, copies, type);
            JobQueue.addJob(job);

            // Simulate printing
            new Thread(() -> {
                try {
                    Thread.sleep(copies * 2000);
                    job.setCompleted(true);
                } catch (Exception ignored) {}
            }).start();

            JOptionPane.showMessageDialog(this, "Job Added! ID: " + job.getJobId());

            nameField.setText("");
            copiesField.setText("");
            typeField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    public static void main(String[] args) {
        new AddJob().setVisible(true);
    }
}