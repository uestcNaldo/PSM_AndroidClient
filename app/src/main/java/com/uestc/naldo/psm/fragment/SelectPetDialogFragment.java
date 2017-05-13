package com.uestc.naldo.psm.fragment;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;


import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.model.PetDialogItem;

import static android.content.ContentValues.TAG;


/**
 * Created by Naldo on 2017/5/11.
 */

public class SelectPetDialogFragment extends DialogFragment {

    public interface SelectPetDialogListener{
        void onDialogPetItemSelect(PetDialogItem petDialogItem);
    }

    private SelectPetDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectPetDialogListener){
            mListener = (SelectPetDialogListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
//        try{
//            mListener = (SelectPetDialogListener) context;
//        }catch (ClassCastException e){
//            throw new ClassCastException(context.toString()+" must implement SelectPetDialogListener");
//        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_dialog_select_pet);
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setItems(R.array.petlistExample, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), "which : "+which, Toast.LENGTH_SHORT).show();
                mListener.onDialogPetItemSelect(new PetDialogItem(1,"金毛"));

            }
        });

        return builder.create();
    }

}
