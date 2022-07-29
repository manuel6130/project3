//**************************************************************************************************
/*
CLASS: View
AUTHOR:
Manuel Sanchez - msanc156 - msanc156@asu.edu
Ilsa Ramirez - iramirez - iramirez@asu.edu
Estevan Perez - eperez56 - eperez56@asu.edu
 */
//**************************************************************************************************
package po3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The View class implements the GUI. It is a subclass of JFrame and implements
 * the ActionListener interface so that we can respond to user-initiated GUI
 * events.
 */
public class View extends JFrame implements ActionListener {

    public static final int FRAME_WIDTH = 525;
    public static final int FRAME_HEIGHT = 225;
    /**
     * When the View() ctor is called from Main.run() to create the View, run()
     * passes a reference to the Main object as the argument to View(). We save
     * that reference into mMain and then later we can use mMain to communicate
     * with the Main class.
     *
     * mMain is made accessible within this class via accessor/mutator methods
     * getMain() and setMain(). It shall not be directly accessed.
     */
    private Main mMain;
    private JButton mClearButton;
    private JTextField[] mExamText;
    private JButton mExitButton;
    private JTextField[] mHomeworkText;
    private JButton mSaveButton;
    private JButton mSearchButton;
    private JTextField mStudentName;

    public View(Main pMain) {

        setMain(pMain);
        // PSEUDOCODE:
        // Create a JPanel named panelSearch which uses the FlowLayout
        // Add a JLabel "Student Name: " to panelSearch
        // Create mStudentName and make the field 25 cols wide
        // Add mStudentName to the panel
        // Create mSearchButton with the label "Search"
        // Make this View the action listener for the button
        // Add the button to the panel
        JPanel panelSearch = new JPanel(new FlowLayout());
        JLabel label1 = new JLabel("Student Name: ");
        panelSearch.add(label1);
        mStudentName = new JTextField(25);
        panelSearch.add(mStudentName);
        mSearchButton = new JButton("Search");
        mSearchButton.addActionListener(this);
        panelSearch.add(mSearchButton);

        // PSEUDOCODE:
        // Create a JPanel named panelHomework which uses the FlowLayout
        // Add a JLabel "Homework: " to the panel
        // Create mHomeworkText which is an array of JTextFields, one for each homework assignment
        // For i = 0 to the number of homework assignments Do
        //     Create a textfield mHomeworkText[i] displaying 5 cols
        //     Add mHomeworkText[i] to the panel
        // End For
        // Note: DO NOT HARDCODE THE NUMBER OF HOMEWORK ASSIGNMENTS
        JPanel panelHomework = new JPanel(new FlowLayout());
        panelHomework.add(new JLabel("Homework:"));
        mHomeworkText = new JTextField[Main.getNumHomeworks()];
        for (int i = 0; i < Main.getNumHomeworks(); i++) {
            mHomeworkText[i] = new JTextField(5);
            panelHomework.add(mHomeworkText[i]);
        }

        // Create the exam panel which contains the "Exam: " label and the two exam text fields.
        // The pseudocode is omitted because this code is very similar to the code that creates the
        // panelHomework panel above.
        // Note: DO NOT HARDCODE THE NUMBER OF EXAMS
        JPanel panelExam = new JPanel(new FlowLayout());
        panelExam.add(new JLabel("Exam:"));
        mExamText = new JTextField[Main.getNumExams()];
        for (int i = 0; i < Main.getNumExams(); i++) {
            mExamText[i] = new JTextField(5);
            panelExam.add(mExamText[i]);
        }

        JPanel panelButtons = new JPanel(new FlowLayout());
        mClearButton = new JButton("Clear");
        mClearButton.addActionListener(this);
        panelButtons.add(mClearButton);

        mSaveButton = new JButton("Save");
        mSaveButton.addActionListener(this);
        panelButtons.add(mSaveButton);

        mExitButton = new JButton("Exit");
        mExitButton.addActionListener(this);
        panelButtons.add(mExitButton);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.add(panelSearch);
        panelMain.add(panelHomework);
        panelMain.add(panelExam);
        panelMain.add(panelButtons);

        setTitle("Gred :: Gradebook Editor");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        // Set the default close operation to JFrame.DO_NOTHING_ON_CLOSE. This disables the X close
        // button in the title bar of the View so now the only way to exit the program is by click-
        // ing the Exit button. This ensures that Main.exit() will be called so it will write the
        // student records back out to the gradebook database.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(panelMain);
        setVisible(true);
    }

    /**
     * actionPerformed()
     *
     * Called when one of the JButtons is clicked. Detects which button was
     * clicked and handles it.
     *
     */
    @Override
    public void actionPerformed(ActionEvent pEvent) {
        String search = pEvent.getActionCommand();
        if (pEvent.getSource() == mSearchButton) {
            String lastName = mStudentName.getText();
            if (lastName.equals("")) {
                messageBox("Please enter the student's last name.");
            } else {
                Student Student = getMain().search(lastName);
                if (Student == null) {
                    messageBox("Student not found. Try again.");
                } else {
                    displayStudent(Student.getCurrStudent());
                }
            }
        } else if (pEvent.getSource() == mSaveButton) {
            if (Student.getCurrStudent() != null) {
                saveStudent(Student.getCurrStudent());
            }
        } else if (pEvent.getSource() == mClearButton) {
            clear();
        } else if (pEvent.getSource() == mExitButton) {
            if (Student.getCurrStudent() != null) {
                saveStudent(Student.getCurrStudent());
            }
            getMain().exit();
        }
    }

    /**
     * clear()
     *
     * Called when the Clear button is clicked. Clears all of the text fields by
     * setting the contents of each to the empty string.
     *
     * After clear() returns, no student information is being edited or
     * displayed and mStudent has been set to null.
     *
     * PSEUDOCODE: method clear() : void Set the mStudentName text field to ""
     * Clear the numbers in the homework and exam fields by calling
     * clearNumbers() Set the current Student object in the Student class to
     * null end clear
     */
    private void clear() {
        mStudentName.setText("");
        clearNumbers();
        mStudentName = null;
    }

    /**
     * clearNumbers()
     *
     * Clears the homework and exam fields.
     *
     * DO NOT HARCODE THE NUMBER OF HOMEWORKS AND EXAMS. Call the constant
     * accessor methods in Main.
     */
    private void clearNumbers() {
        for (int i = 0; i < Main.getNumHomeworks(); i++) {
            mHomeworkText[i].setText("");
        }
        for (int i = 0; i < Main.getNumExams(); i++) {
            mExamText[i].setText("");
        }
    }

    /**
     * displayStudent()
     *
     * Displays the homework and exam scores for a student in the mHomeworkText
     * and mExamText text fields.
     */
    private void displayStudent(Student pStudent) {
        for (int i = 0; i < Main.getNumHomeworks() - 1; i++) {
            int hw = pStudent.getHomework(i);
            String hwstr = Integer.toString(hw);
            mHomeworkText[i].setText(hwstr);
        }
        for (int i = 0; i < Main.getNumExams() - 1; i++) {
            int exam = pStudent.getExam(i);
            String examstr = Integer.toString(exam);
            mExamText[i].setText(examstr);
        }
    }

    private Main getMain() {
        return mMain;
    }

    /**
     * messageBox()
     *
     * Displays a message box containing some text.
     */
    public void messageBox(String pMessage) {
        JOptionPane.showMessageDialog(this, pMessage, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * saveStudent()
     *
     * Retrieves the homework and exam scores for pStudent from the text fields
     * and writes the results to the Student record in the Roster.
     */
    private void saveStudent(Student pStudent) {
        for (int i = 0; i < Main.getNumHomeworks() - 1; i++) {
            String hwstr = mHomeworkText[i].getText();
            int hw = Integer.parseInt(hwstr);
            pStudent.setHomework(i, hw);
        }
        for (int i = 0; i < Main.getNumExams() - 1; i++) {
            String examstr = mExamText[i].getText();
            int exam = Integer.parseInt(examstr);
            pStudent.setExam(i, exam);
        }
    }

    /**
     * Mutator method for mMain.
     */
    private void setMain(Main pMain) {
        mMain = pMain;
    }

}
