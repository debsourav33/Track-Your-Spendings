package com.example.trackyourspendings.ui.dialogs;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;

public class DialogManager {
    private FragmentManager fragmentManager;

    public DialogManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showInputDialog(Date dateOfTransaction, String tag){
        DialogFragment dialogFragment= new InputDialogFragment(dateOfTransaction);
        dialogFragment.show(fragmentManager,tag);
    }
}
