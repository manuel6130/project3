//**************************************************************************************************
/*
CLASS: Student
AUTHOR:
Manuel Sanchez - msanc156 - msanc156@asu.edu
Ilsa Ramirez - iramirez - iramirez@asu.edu
Estevan Perez - eperez56 - eperez56@asu.edu
 */
//**************************************************************************************************
package po3;

import java.util.ArrayList;

/**
 * The Student class stores the gradebook information for one Student.
 *
 * Note that Student implements the java.lang.Comparable<Student> interface
 * because we are going to be sorting the roster of students by last name so we
 * need to be able to compare the last names of two students A and B to
 * determine if A < B, or if A = B, or if A > B. See the compareTo() method.
 */
public class Student implements Comparable<Student> {

    private static Student mCurrStudent;
    private ArrayList<Integer> mExamList = new ArrayList<>();
    private String mFirstName;
    private ArrayList<Integer> mHomeworkList = new ArrayList<>();
    private String mLastName;

    /**
     * Student()
     *
     * PSEUDOCODE: method Student(pFirstName : String, pLastName : String) save
     * parameters pFirstName and pLastName to instance variables by calling
     * mutators -- Note that we only create the exam list here, it will be
     * populated later create an ArrayList<Integer> and pass it off to
     * setExamList() -- Note that we only create the homework list here, it will
     * be populated later create an ArrayList<Integer> and pass it off to
     * setHomeworkList() end Student()
     */
    public Student(String pFirstName, String pLastName) {
        mFirstName = pFirstName;
        mLastName = pLastName;
    }

    public void addExam(int pScore) {
        getExamList().add(pScore);
    }

    public void addHomework(int pScore) {
        getHomeworkList().add(pScore);
    }

    /**
     * compareTo()
     *
     * @param pStudent is a Student
     *
     * This method compares the last name of 'this' Student to the last name of
     * pStudent to determine if the last name of 'this' Student is <, =, or >
     * the last name of pStudent. It is called during the quick sort routine in
     * Sorter.partition().
     *
     * Provide the annotation that prevents accidental overloading since we are
     * overriding the String.compareTo() method.
     *
     * PSEUDOCODE: method compareTo(pStudent : Student) : int return: negative
     * int if the last name of this Student is < the last name of pStudent
     *     return: zero if the last name of this Student is = the last name of pStudent
     *     return: positive int if the last name of this Student is > the last name
     * of pStudent hint: the last names are Strings and String already
     * implements compareTo(). end compareTo
     */
    @Override
    public int compareTo(Student pStudent) {
        int num = 0;
        if (this.getLastName().length() < pStudent.getLastName().length()) {
            num = -1;
        } else if (this.getLastName().length() > pStudent.getLastName().length()) {
            num = 1;
        }
        return num;
    }

    /**
     * Accessor method for mCurrStudent.
     */
    public static Student getCurrStudent() {
        return mCurrStudent;
    }

    /**
     * getExam()
     *
     * Accessor method to retrieve an exam score from the list of exams.
     *
     * @param pNum The exam number for which we wish to retrieve the score.
     *
     * @return The exam score.
     *
     * Hint: Call getExamList() to get the ArrayList<Integer> object storing the
     * exam scores. Since that object is an ArrayList<Integer>, we next call the
     * get(index) method to retrieve the correct exam score.
     */
    //
    public int getExam(int pNum) {
        return getExamList().get(pNum);
    }

    /**
     * getExamList()
     *
     * Accessor method for mExamList.
     */
    private ArrayList<Integer> getExamList() {
        return mExamList;
    }

    /**
     * getFirstName()
     *
     * Accessor method for mFirstName.
     */
    public String getFirstName() {
        return mFirstName;
    }

    public String getFullName() {
        return mLastName + mFirstName;
    }

    public int getHomework(int pNum) {
        return getHomeworkList().get(pNum);
    }

    private ArrayList<Integer> getHomeworkList() {
        return mHomeworkList;
    }

    public String getLastName() {
        return mLastName;
    }

    public static void setCurrStudent(Student pCurrStudent) {
        mCurrStudent = pCurrStudent;
    }

    public void setExam(int pNum, int pScore) {
        getExamList().set(pNum, pScore);
    }

    /**
     * setExamList()
     *
     * Mutator method for mExamList.
     */
    private void setExamList(ArrayList<Integer> pExamList) {
        mExamList = pExamList;
    }

    /**
     * setFirstName()
     *
     * Mutator method for mFirstName.
     */
    public void setFirstName(String pFirstName) {
        mFirstName = pFirstName;
    }

    public void setHomework(int pNum, int pScore) {
        getHomeworkList().set(pNum, pScore);
    }

    private void setHomeworkList(ArrayList<Integer> pHomeworkList) {
        mHomeworkList = pHomeworkList;
    }

    public void setLastName(String pLastName) {
        mLastName = pLastName;
    }

    /**
     * toString()
     *
     * Returns a String representation of this Student. The format of the
     * returned string shall be such that the Student information can be printed
     * to the output file in this format:
     *
     * lastname firstname exam1 exam2 exam2 hw1 hw2 hw3 hw4 hw5
     *
     * where the fields are separated by spaces, except there is not space
     * following hw5.
     *
     * Hint: The String class has a method named trim() that removes leading and
     * trailing white- space from a string.
     *
     * Hint: use enhanced for loops
     */
    @Override
    public String toString() {
        String result = getLastName() + " " + getFirstName() + " ";
        for (Integer exam : getExamList()) {
            result += exam + " ";
        }
        for (Integer hw : getHomeworkList()) {
            result += hw + " ";
        }
        return result.trim();
    }
}
