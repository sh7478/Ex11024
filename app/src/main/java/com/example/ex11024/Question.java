package com.example.ex11024;

/**
 * מחלקה המייצגת שאלה אחת במשחק הטריוויה.
 * <p>
 * המחלקה שומרת את נוסח השאלה, מערך של תשובות אפשריות ואת התשובה הנכונה.
 *
 * @author Your Name
 * @version 1.0
 * @since 2024
 */
public class Question {
    private String question;
    private String[] answers;
    private String correctAnswer;

    /**
     * בנאי ליצירת אובייקט שאלה חדש.
     *
     * @param question נוסח השאלה.
     * @param answers מערך של 4 תשובות אפשריות.
     * @param correctAnswerIndex האינדקס של התשובה הנכונה במערך (0-3).
     */
    public Question(String question, String[] answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        if (correctAnswerIndex >= 0 && correctAnswerIndex < answers.length) {
            this.correctAnswer = answers[correctAnswerIndex];
        } else {
            this.correctAnswer = "";
        }
    }

    /**
     * מחזירה את נוסח השאלה.
     *
     * @return נוסח השאלה.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * מעדכנת את נוסח השאלה.
     *
     * @param question נוסח השאלה החדש.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * מחזירה את מערך התשובות האפשריות.
     *
     * @return מערך של מחרוזות.
     */
    public String[] getAnswers() {
        return answers;
    }

    /**
     * מעדכנת את מערך התשובות האפשריות.
     *
     * @param answers מערך תשובות חדש.
     */
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    /**
     * מחזירה את התשובה הנכונה.
     *
     * @return התשובה הנכונה (כולל תגית הזיהוי במידה וקיימת).
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * מעדכנת את התשובה הנכונה.
     *
     * @param correctAnswer התשובה הנכונה החדשה.
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
