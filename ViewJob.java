import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewJob extends JFrame {

    JTextField idField;
    JTextArea area;

    public ViewJob() {

        setTitle("View Job");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setBackground(new Color(30, 30, 30));

        JLabel label = new JLabel("Enter Job ID:");
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.WHITE);

        idField = new JTextField(10);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JButton viewBtn = new JButton("View");
        JButton backBtn = new JButton("Back");

        viewBtn.setPreferredSize(new Dimension(120, 40));
        backBtn.setPreferredSize(new Dimension(120, 40));

        top.add(label);
        top.add(idField);
        top.add(viewBtn);
        top.add(backBtn);

        area = new JTextArea();
        area.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        area.setBackground(new Color(40, 40, 40));
        area.setForeground(Color.WHITE);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        viewBtn.addActionListener(e -> viewJob());

        backBtn.addActionListener(e -> {
            new printHome().setVisible(true);
            dispose();
        });
    }

    private void viewJob() {
        try {
            int id = Integer.parseInt(idField.getText());
            Job job = JobQueue.findJobById(id);

            if (job != null) {
                area.setText(job.toString());
            } else {
                area.setText("Job Not Found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
    }
}