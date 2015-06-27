package io.github.louistsaitszho.erg2.gui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.Comparator;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.gui.activity.HistoryActivity;
import io.github.louistsaitszho.erg2.unit.Record;

/**
 * Created by Louis on 6/27/2015.
 */
public class SortingDialog extends DialogFragment {

    public static final String TAG = SortingDialog.class.getName();
    private Comparator<Record> sortingMode;

    public SortingDialog() {
    }

    public SortingDialog(Comparator<Record> oldSM) {
        sortingMode = oldSM;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_which_sorting_mode)
                .setItems(R.array.sorting_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                sortingMode = Record.StartTimeComparatorDESC;
                                break;
                            case 1:
                                sortingMode = Record.StartTimeComparatorASCE;
                                break;
                            case 2:
                                sortingMode = Record.DistanceComparatorDESC;
                                break;
                            case 3:
                                sortingMode = Record.DistanceComparatorASEC;
                                break;
                            case 4:
                                sortingMode = Record.DurationComparatorDESC;
                                break;
                            case 5:
                                sortingMode = Record.DurationComparatorASEC;
                        }
                        ((HistoryActivity) getActivity()).setSortingMode(sortingMode);
                        ((HistoryActivity) getActivity()).updateView();
                    }
                });
        return builder.create();
    }
}
