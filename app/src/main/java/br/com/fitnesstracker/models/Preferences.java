package br.com.fitnesstracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Preferences implements Parcelable {

    private String firebaseKey;
    private Boolean neck;
    private Boolean shoulders;
    private Boolean breastplate;
    private Boolean waist;
    private Boolean abdomen;
    private Boolean rightArm;
    private Boolean leftArm;
    private Boolean rightLeg;
    private Boolean leftLeg;
    private Boolean rightCalf;
    private Boolean leftCalf;
    private Boolean weight;

    public Preferences() {
    }

    public Preferences(String firebaseKey,
                       Boolean neck,
                       Boolean shoulders,
                       Boolean breastplate,
                       Boolean waist,
                       Boolean abdomen,
                       Boolean rightArm,
                       Boolean leftArm,
                       Boolean rightLeg,
                       Boolean leftLeg,
                       Boolean rightCalf,
                       Boolean leftCalf,
                       Boolean weight) {
        this.firebaseKey = firebaseKey;
        this.neck = neck;
        this.shoulders = shoulders;
        this.breastplate = breastplate;
        this.waist = waist;
        this.abdomen = abdomen;
        this.rightArm = rightArm;
        this.leftArm = leftArm;
        this.rightLeg = rightLeg;
        this.leftLeg = leftLeg;
        this.rightCalf = rightCalf;
        this.leftCalf = leftCalf;
        this.weight = weight;
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String firebaseKey) {
        this.firebaseKey = firebaseKey;
    }

    public Boolean getNeck() {
        return neck;
    }

    public void setNeck(Boolean neck) {
        this.neck = neck;
    }

    public Boolean getShoulders() {
        return shoulders;
    }

    public void setShoulders(Boolean shoulders) {
        this.shoulders = shoulders;
    }

    public Boolean getBreastplate() {
        return breastplate;
    }

    public void setBreastplate(Boolean breastplate) {
        this.breastplate = breastplate;
    }

    public Boolean getWaist() {
        return waist;
    }

    public void setWaist(Boolean waist) {
        this.waist = waist;
    }

    public Boolean getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Boolean abdomen) {
        this.abdomen = abdomen;
    }

    public Boolean getRightArm() {
        return rightArm;
    }

    public void setRightArm(Boolean rightArm) {
        this.rightArm = rightArm;
    }

    public Boolean getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(Boolean leftArm) {
        this.leftArm = leftArm;
    }

    public Boolean getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(Boolean rightLeg) {
        this.rightLeg = rightLeg;
    }

    public Boolean getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(Boolean leftLeg) {
        this.leftLeg = leftLeg;
    }

    public Boolean getRightCalf() {
        return rightCalf;
    }

    public void setRightCalf(Boolean rightCalf) {
        this.rightCalf = rightCalf;
    }

    public Boolean getLeftCalf() {
        return leftCalf;
    }

    public void setLeftCalf(Boolean leftCalf) {
        this.leftCalf = leftCalf;
    }

    public Boolean getWeight() {
        return weight;
    }

    public void setWeight(Boolean weight) {
        this.weight = weight;
    }

    protected Preferences(Parcel in) {
        firebaseKey = in.readString();
        byte neckVal = in.readByte();
        neck = neckVal == 0x02 ? null : neckVal != 0x00;
        byte shouldersVal = in.readByte();
        shoulders = shouldersVal == 0x02 ? null : shouldersVal != 0x00;
        byte breastplateVal = in.readByte();
        breastplate = breastplateVal == 0x02 ? null : breastplateVal != 0x00;
        byte waistVal = in.readByte();
        waist = waistVal == 0x02 ? null : waistVal != 0x00;
        byte abdomenVal = in.readByte();
        abdomen = abdomenVal == 0x02 ? null : abdomenVal != 0x00;
        byte rightArmVal = in.readByte();
        rightArm = rightArmVal == 0x02 ? null : rightArmVal != 0x00;
        byte leftArmVal = in.readByte();
        leftArm = leftArmVal == 0x02 ? null : leftArmVal != 0x00;
        byte rightLegVal = in.readByte();
        rightLeg = rightLegVal == 0x02 ? null : rightLegVal != 0x00;
        byte leftLegVal = in.readByte();
        leftLeg = leftLegVal == 0x02 ? null : leftLegVal != 0x00;
        byte rightCalfVal = in.readByte();
        rightCalf = rightCalfVal == 0x02 ? null : rightCalfVal != 0x00;
        byte leftCalfVal = in.readByte();
        leftCalf = leftCalfVal == 0x02 ? null : leftCalfVal != 0x00;
        byte weightVal = in.readByte();
        weight = weightVal == 0x02 ? null : weightVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firebaseKey);
        if (neck == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (neck ? 0x01 : 0x00));
        }
        if (shoulders == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (shoulders ? 0x01 : 0x00));
        }
        if (breastplate == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (breastplate ? 0x01 : 0x00));
        }
        if (waist == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (waist ? 0x01 : 0x00));
        }
        if (abdomen == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (abdomen ? 0x01 : 0x00));
        }
        if (rightArm == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (rightArm ? 0x01 : 0x00));
        }
        if (leftArm == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (leftArm ? 0x01 : 0x00));
        }
        if (rightLeg == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (rightLeg ? 0x01 : 0x00));
        }
        if (leftLeg == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (leftLeg ? 0x01 : 0x00));
        }
        if (rightCalf == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (rightCalf ? 0x01 : 0x00));
        }
        if (leftCalf == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (leftCalf ? 0x01 : 0x00));
        }
        if (weight == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (weight ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Preferences> CREATOR = new Parcelable.Creator<Preferences>() {
        @Override
        public Preferences createFromParcel(Parcel in) {
            return new Preferences(in);
        }

        @Override
        public Preferences[] newArray(int size) {
            return new Preferences[size];
        }
    };
}