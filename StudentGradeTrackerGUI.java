import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Student {
    String name;
    int marks;
    char grade;

    Student(String name, int marks, char grade) {
        this.name = name;
        this.marks = marks;
        this.grade = grade;
    }
}

public class StudentGradeTrackerGUI extends JFrame implements ActionListener {

    JTextField nameField, marksField;
    JTextArea displayArea;
    JButton addBtn, reportBtn;

    ArrayList<Student> students = new ArrayList<>();

    StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Student Name:"));
        nameField = new JTextField(15);
        add(nameField);

        add(new JLabel("Marks:"));
        marksField = new JTextField(5);
        add(marksField);

        addBtn = new JButton("Add Student");
        reportBtn = new JButton("Show Report");

        add(addBtn);
        add(reportBtn);

        displayArea = new JTextArea(12, 40);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));

        addBtn.addActionListener(this);
        reportBtn.addActionListener(this);

        setVisible(true);
    }

    char getGrade(int marks) {
        if (marks >= 90) return 'A';
        else if (marks >= 75) return 'B';
        else if (marks >= 60) return 'C';
        else if (marks >= 50) return 'D';
        else return 'F';
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {
            try {
                String name = nameField.getText();
                int marks = Integer.parseInt(marksField.getText());

                if (name.isEmpty() || marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Invalid Input!");
                    return;
                }

                char grade = getGrade(marks);
                students.add(new Student(name, marks, grade));

                displayArea.append(name + "  |  " + marks + "  |  Grade: " + grade + "\n");

                nameField.setText("");
                marksField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid marks!");
            }
        }

        if (e.getSource() == reportBtn) {
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students added!");
                return;
            }

            int total = 0;
            int high = students.get(0).marks;
            int low = students.get(0).marks;

            for (Student s : students) {
                total += s.marks;
                if (s.marks > high) high = s.marks;
                if (s.marks < low) low = s.marks;
            }

            double avg = (double) total / students.size();

            displayArea.append("\n--- SUMMARY REPORT ---\n");
            displayArea.append("Total Students: " + students.size() + "\n");
            displayArea.append("Average Marks: " + avg + "\n");
            displayArea.append("Highest Marks: " + high + "\n");
            displayArea.append("Lowest Marks: " + low + "\n");
        }
    }

    public static void main(String[] args) {
        new StudentGradeTrackerGUI();
    }
}
