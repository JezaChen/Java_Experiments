package com.company;

import org.jetbrains.annotations.NotNull;

public class Student {
    private int id;
    private int score;

    Student(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

class StudentStatisticsHandler {
    @NotNull
    public static MaxMinData MaxMin(Student[] students) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (Student curr : students) {
            max = Integer.max(max, curr.getScore());
            min = Integer.min(min, curr.getScore());
        }

        return new MaxMinData(max, min);
    }

    public static double averageScore(Student[] students) {
        double ans = 0;
        for (Student curr : students) {
            ans += curr.getScore();
        }

        return ans / students.length;
    }

    public static CountData ScoreHistogram(Student[] students) {
        CountData ans = new CountData();

        for (Student curr : students) {
            switch (curr.getScore() / 10) {
                case 6:
                    ans.num_60_69_addOne();
                    break;
                case 7:
                    ans.num_70_79_addOne();
                    break;
                case 8:
                    ans.num_80_89_addOne();
                    break;
                case 9:
                case 10:
                    ans.num_90_100_addOne();
                    break;
            }
        }

        return ans;
    }
}

class MaxMinData {
    private int max;
    private int min;

    public MaxMinData(int max, int min) {
        this.max = max;
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}

class CountData {
    private int num_60_69;
    private int num_70_79;
    private int num_80_89;
    private int num_90_100;

    public CountData() {
        num_60_69 = 0;
        num_70_79 = 0;
        num_80_89 = 0;
        num_90_100 = 0;
    }

    public void num_60_69_addOne() {
        num_60_69++;
    }

    public void num_70_79_addOne() {
        num_70_79++;
    }

    public void num_80_89_addOne() {
        num_80_89++;
    }

    public void num_90_100_addOne() {
        num_90_100++;
    }

    public int getNum_60_69() {
        return num_60_69;
    }

    public int getNum_70_79() {
        return num_70_79;
    }

    public int getNum_80_89() {
        return num_80_89;
    }

    public int getNum_90_100() {
        return num_90_100;
    }
}
