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

public class RemoveJob extends JFrame {

    JTextField idField, nameField;

    public RemoveJob() {

        setTitle("Remove Job");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("REMOVE JOB");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        idField = createField();
        nameField = createField();
        nameField.setEditable(false);

        JButton findBtn = createButton("Find Job");
        JButton removeBtn = createButton("Remove Job");
        JButton backBtn = createButton("Back");

        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1
        gbc.gridx = 0; gbc.gridy++;
        panel.add(createLabel("Job ID:"), gbc);

        gbc.gridx = 1;
        panel.add(idField, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy++;
        panel.add(createLabel("Job Name:"), gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy++;
        panel.add(findBtn, gbc);

        gbc.gridx = 1;
        panel.add(removeBtn, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(backBtn, gbc);

        add(panel);

        findBtn.addActionListener(e -> findJob());
        removeBtn.addActionListener(e -> removeJob());

        backBtn.addActionListener(e -> {
            new printHome().setVisible(true);
            dispose();
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createField() {
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tf.setPreferredSize(new Dimension(260, 40));
        return tf;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(180, 45));
        return btn;
    }

    private void findJob() {
        try {
            int id = Integer.parseInt(idField.getText());
            Job job = JobQueue.findJobById(id);

            if (job != null) {
                nameField.setText(job.getJobName());
            } else {
                nameField.setText("Not Found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
    }

    private void removeJob() {
        try {
            int id = Integer.parseInt(idField.getText());

            if (JobQueue.removeById(id)) {
                JOptionPane.showMessageDialog(this, "Job Removed!");
                idField.setText("");
                nameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Job Not Found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
    }
}