package br.com.fitnesstracker.models;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.qanda.models.Question;

import java.util.ArrayList;

import br.com.fitnesstracker.R;

public class FisicalAvaliation implements Parcelable {

    private String firebaseKey;
    private String date;
    private Double neck;
    private Double shoulders;
    private Double breastplate;
    private Double waist;
    private Double abdomen;
    private Double rightArm;
    private Double leftArm;
    private Double rightLeg;
    private Double leftLeg;
    private Double rightCalf;
    private Double leftCalf;
    private Double weight;

    public FisicalAvaliation() {
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getNeck() {
        return neck;
    }

    public void setNeck(Double neck) {
        this.neck = neck;
    }

    public Double getShoulders() {
        return shoulders;
    }

    public void setShoulders(Double shoulders) {
        this.shoulders = shoulders;
    }

    public Double getBreastplate() {
        return breastplate;
    }

    public void setBreastplate(Double breastplate) {
        this.breastplate = breastplate;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getRightArm() {
        return rightArm;
    }

    public void setRightArm(Double rightArm) {
        this.rightArm = rightArm;
    }

    public Double getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(Double leftArm) {
        this.leftArm = leftArm;
    }

    public Double getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(Double rightLeg) {
        this.rightLeg = rightLeg;
    }

    public Double getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(Double leftLeg) {
        this.leftLeg = leftLeg;
    }

    public Double getRightCalf() {
        return rightCalf;
    }

    public void setRightCalf(Double rightCalf) {
        this.rightCalf = rightCalf;
    }

    public Double getLeftCalf() {
        return leftCalf;
    }

    public void setLeftCalf(Double leftCalf) {
        this.leftCalf = leftCalf;
    }

    public Double getWeight() {
        return weight;
    }

    public String getWeightString() {
        return String.valueOf(weight);
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void FisicalAvaliation(ArrayList<Question> questions) {

    }

    protected FisicalAvaliation(Parcel in) {
        firebaseKey = in.readString();
        date = in.readString();
        neck = in.readByte() == 0x00 ? null : in.readDouble();
        shoulders = in.readByte() == 0x00 ? null : in.readDouble();
        breastplate = in.readByte() == 0x00 ? null : in.readDouble();
        waist = in.readByte() == 0x00 ? null : in.readDouble();
        abdomen = in.readByte() == 0x00 ? null : in.readDouble();
        rightArm = in.readByte() == 0x00 ? null : in.readDouble();
        leftArm = in.readByte() == 0x00 ? null : in.readDouble();
        rightLeg = in.readByte() == 0x00 ? null : in.readDouble();
        leftLeg = in.readByte() == 0x00 ? null : in.readDouble();
        rightCalf = in.readByte() == 0x00 ? null : in.readDouble();
        leftCalf = in.readByte() == 0x00 ? null : in.readDouble();
        weight = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firebaseKey);
        dest.writeString(date);
        if (neck == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(neck);
        }
        if (shoulders == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(shoulders);
        }
        if (breastplate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(breastplate);
        }
        if (waist == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(waist);
        }
        if (abdomen == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(abdomen);
        }
        if (rightArm == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(rightArm);
        }
        if (leftArm == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(leftArm);
        }
        if (rightLeg == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(rightLeg);
        }
        if (leftLeg == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(leftLeg);
        }
        if (rightCalf == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(rightCalf);
        }
        if (leftCalf == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(leftCalf);
        }
        if (weight == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(weight);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FisicalAvaliation> CREATOR = new Parcelable.Creator<FisicalAvaliation>() {
        @Override
        public FisicalAvaliation createFromParcel(Parcel in) {
            return new FisicalAvaliation(in);
        }

        @Override
        public FisicalAvaliation[] newArray(int size) {
            return new FisicalAvaliation[size];
        }
    };

    public String getValueFromPreferenceKey(String preferenceKey, Resources resources) {

        if (preferenceKey.equals(resources.getString(R.string.preference_date))) {
            return date;
        } else if (preferenceKey.equals(resources.getString(R.string.preference_neck))) {
            return String.valueOf(neck);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_shoulders))) {
            return String.valueOf(shoulders);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_breastplate))) {
            return String.valueOf(breastplate);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_waist))) {
            return String.valueOf(waist);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_abdomen))) {
            return String.valueOf(abdomen);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_rightArm))) {
            return String.valueOf(rightArm);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_leftArm))) {
            return String.valueOf(leftArm);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_rightLeg))) {
            return String.valueOf(rightLeg);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_leftLeg))) {
            return String.valueOf(leftLeg);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_rightCalf))) {
            return String.valueOf(rightCalf);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_leftCalf))) {
            return String.valueOf(leftCalf);
        } else if (preferenceKey.equals(resources.getString(R.string.preference_weight))) {
            return String.valueOf(weight);
        }
        return null;
    }

    public Double getValueByQuestion(Context context, String question) {
        if (question.equals(context.getString(R.string.question_neck))) {
            return neck;
        } else if (question.equals(context.getString(R.string.question_shoulders))) {
            return shoulders;
        } else if (question.equals(context.getString(R.string.question_breastplate))) {
            return breastplate;
        } else if (question.equals(context.getString(R.string.question_waist))) {
            return waist;
        } else if (question.equals(context.getString(R.string.question_abdomen))) {
            return abdomen;
        } else if (question.equals(context.getString(R.string.question_rightArm))) {
            return rightArm;
        } else if (question.equals(context.getString(R.string.question_leftArm))) {
            return leftArm;
        } else if (question.equals(context.getString(R.string.question_rightLeg))) {
            return rightLeg;
        } else if (question.equals(context.getString(R.string.question_leftLeg))) {
            return leftLeg;
        } else if (question.equals(context.getString(R.string.question_rightCalf))) {
            return rightCalf;
        } else if (question.equals(context.getString(R.string.question_leftCalf))) {
            return leftCalf;
        } else if (question.equals(context.getString(R.string.question_weight))) {
            return weight;
        }
        return null;
    }
}
