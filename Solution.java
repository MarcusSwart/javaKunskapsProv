package org.launchcode;

import java.util.*;

// Marcus Swart
// Java Kunskapsprov Sogeti

public class Solution {

    public static void main(String[] args) {

        // Data Collection.
        Student[] list = Input();

        // Counting of success/fails.
        int[] counted = counting(list);

        // Determining position of most successes.
        int result = sorting(counted);

        // Print result.
        System.out.println(result);
    }


    public static Student[] Input() {

        Scanner in = new Scanner(System.in);

        // Determine Nr of students.
        int x = in.nextInt();
        Student[] list = new Student[x];

        // Assign extra time for each student.
        for (int i = 0; i < x; i++) {
            list[i] = new Student(i, in.nextInt());
        }

        return list;
    }

    public static int[] counting(Student[] students) {

        int fail;
        int success;

        // Declare an array that will be used for counting at which positions
        // grading will fail or succeed.
        int[] countList = new int[students.length];

        // Iteration to check every student. A set of ranges will be plotted
        // in countList.
        for (Student student : students) {

            // If no extra time requested then the
            // student will always be happy and is irrelevant to the counter.
            if (student.time != 0) {

                // The position where grading the current student will first fail.
                fail = (student.position - student.time) + 1;

                // If the position is a negative number it means
                // it has looped around to the opposite end of the array.
                // Then give that position a -1.
                if (fail < 0) {
                    countList[fail + students.length]--;
                } else
                    countList[fail]--;

                //The position where grading the current student will first succeed.
                success = (student.position + 1) % students.length;

                // Give that position +1.
                countList[success]++;
            }
        }

        return countList;

    }

    public static int sorting(int[] counted) {

        int result = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;

        // Iterate for each student at which position will have the most
        // successful grading and return that position.
        for (int i = 0; i < counted.length; i++) {
            sum += counted[i];
            if (sum > max) {
                max = sum;
                result = i + 1;
            }
        }

        return result;
    }

    // Student class with constructor receiving position and extra time.
    public static class Student {
        int position, time;

        Student(int p, int t) {
            this.position = p;
            this.time = t;
        }
    }
}
