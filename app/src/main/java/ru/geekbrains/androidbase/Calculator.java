package ru.geekbrains.androidbase;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.zip.Inflater;

import static ru.geekbrains.androidbase.Operation.ADDITION;

public class Calculator implements Parcelable {

    public static final CharSequence ZERO = "ZERO";

    public Calculator() {
    }

    protected Calculator(Parcel in) {
    }


    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

//    protected CharSequence addition(CharSequence value1, CharSequence value2)
//    {
//        Double result = Double.parseDouble(value1.toString())+Double.parseDouble(value2.toString());
//        CharSequence answer = result+"";
    //return answer;
//}

    protected CharSequence equation(CharSequence value1, CharSequence value2,char operation) {
        Double result;
        Double buffer1 = Double.parseDouble(value1.toString());
        Double buffer2 = Double.parseDouble(value2.toString());
        switch (operation) {
            case ('+'):
                result = buffer1 + buffer2;
                return result + "";
            case ('-'):
                result = buffer1 - buffer2;
                return result + "";
            case ('*'):
                result = buffer1 * buffer2;

                return result + "";
            case ('/'):
                if (buffer2 != 0) {
                    result = buffer1 / buffer2;
                    return result + "";
                } else {

                    return ZERO;
                }


                //return result+"";

            default:
                return "";

        }

    }
}
