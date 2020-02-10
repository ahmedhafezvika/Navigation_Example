package pub.war.navigationexample.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Money implements Parcelable {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public Money(double amount) {
        this.amount = amount;
    }

    private Money(Parcel in) {
        this.amount = in.readDouble();
    }

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(amount);
    }
}
