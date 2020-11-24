package org.proyek.parkirassistant;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InputParkirDialog extends AppCompatDialogFragment {
    private EditText inputNoParkir;
    private InputParkirDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.input_parkir,null);
        builder.setView(view)
                .setTitle("Input Parkir Pelanggan")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String noParkir = inputNoParkir.getText().toString();
                        listener.ApplyTexts(noParkir);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        inputNoParkir = view.findViewById(R.id.input_nomor_parkir_dialog);

        return  builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (InputParkirDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " InputParkirDialogListener must be implemented");
        }
    }



    public interface InputParkirDialogListener {
        void ApplyTexts(String inputParkir);
    }
}
