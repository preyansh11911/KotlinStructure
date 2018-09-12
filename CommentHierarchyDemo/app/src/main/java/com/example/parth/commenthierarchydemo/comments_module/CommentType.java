package com.example.parth.commenthierarchydemo.comments_module;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CommentType {
    public static final int COMMENT = 10110;
    public static final int REPLY = 10120;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({COMMENT, REPLY})
    public @interface Type {
    }
}
