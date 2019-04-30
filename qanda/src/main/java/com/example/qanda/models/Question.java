package com.example.qanda.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private String mQuestion;
    private AnswerType mAnswerType;
    private String mAnswer;

    public Question() {
    }

    public Question(String question, AnswerType answerType) {
        this.mQuestion = question;
        this.mAnswerType = answerType;
    }

    public Question(String question, AnswerType answerType, String answer) {
        this.mQuestion = question;
        this.mAnswerType = answerType;
        this.mAnswer = answer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public AnswerType getAnswerType() {
        return mAnswerType;
    }

    public void setAnswerType(AnswerType answerType) {
        mAnswerType = answerType;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }

    public enum AnswerType {
        DATE,
        DOUBLE,
        STRING
    }

    protected Question(Parcel in) {
        mQuestion = in.readString();
        mAnswerType = (AnswerType) in.readValue(AnswerType.class.getClassLoader());
        mAnswer = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestion);
        dest.writeValue(mAnswerType);
        dest.writeString(mAnswer);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
