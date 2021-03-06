package com.github.snowdream.hdiffpatch;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.snowdream.diffpatch.IDiffPatch;
import com.github.snowdream.diffpatch.SoLoader;

import java.io.File;

/**
 * Created by snowdream on 16-7-17.
 */
public class HDiffPatch implements IDiffPatch {

    @Override
    public void init(@NonNull Context context) {
        SoLoader.loadLibrary(context, "hdiffpatch");
    }

    public native int hdiff(String oldFilePath, String newFilePath, String diffFilePath);

    public native int hpatch(String oldFilePath, String diffFilePath, String newFilePath);

    @Override
    public int diff(String oldFilePath, String newFilePath, String diffFilePath) {
        File oldFile = new File(oldFilePath);
        if (!oldFile.exists() || oldFile.length() == 0) return IDiffPatch.ERROR;

        File newFile = new File(oldFilePath);
        if (!newFile.exists() || newFile.length() == 0) return IDiffPatch.ERROR;

        return hdiff(oldFilePath,newFilePath,diffFilePath);
    }

    @Override
    public int patch(String oldFilePath, String diffFilePath, String newFilePath) {
        File oldFile = new File(oldFilePath);
        if (!oldFile.exists() || oldFile.length() == 0) return IDiffPatch.ERROR;

        File newFile = new File(oldFilePath);
        if (!newFile.exists() || newFile.length() == 0) return IDiffPatch.ERROR;

        return hpatch(oldFilePath,diffFilePath,newFilePath);
    }
}
