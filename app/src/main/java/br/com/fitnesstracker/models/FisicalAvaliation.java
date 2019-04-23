package br.com.fitnesstracker.models;

import com.example.qanda.models.Question;

import java.util.ArrayList;

public class FisicalAvaliation {

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
}
