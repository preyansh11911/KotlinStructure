package com.support.kotlin;

public class test {
    public void showConfirmationDialog(T.OnClickListener listener) {
        listener.onClick(1, 2);
    }

    public interface T {

        interface OnClickListener {

            void onClick(int r, int which);
        }
    }

}
