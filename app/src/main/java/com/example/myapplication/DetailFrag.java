package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */



public class DetailFrag extends Fragment {
    private Button btSave;
    private Button btCamera;
    private ButtonListener buttonListener;
    private static final String TAG = "MainActivity";
    private static final int REQ_CODE_TAKE_PICTURE = 0;

    public DetailFrag() {
        // Required empty public constructor
    }

    public interface ButtonListener{
        void onInputASent(boolean press);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.detail_frag, container, false);
        btSave = v.findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            buttonListener.onInputASent(true);
            }
        });

        btCamera = v.findViewById(R.id.btnCamera);
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(picIntent, REQ_CODE_TAKE_PICTURE);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            buttonListener = (ButtonListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement the interface called buttonListener");
        }
    }
 /*
        btnCamera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            runTextRecognition();
        }
    });


}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_TAKE_PICTURE && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            mSelectedImage = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(mSelectedImage);
            //byte[] bmpArray;
            //ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            //mSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            //bmpArray = bStream.toByteArray();

        }
    }


    private void runTextRecognition() {
        //byte[] getBMP = null;
        //Bitmap bitmap = null;
        //bitmap = BitmapFactory.decodeByteArray(getBMP, 0, getBMP.length);
        //img.setImageBitmap(bitmap);
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mSelectedImage);
        FirebaseVisionTextRecognizer recognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        //mTextButton.setEnabled(false);
        recognizer.processImage(image)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {
                                //mTextButton.setEnabled(true);
                                processTextRecognitionResult(texts);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                //mTextButton.setEnabled(true);
                                e.printStackTrace();
                            }
                        });
    }

    private void processTextRecognitionResult(FirebaseVisionText texts) {
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() == 0) {
            showToast("No text found");
            return;
        }
        labels.clear();
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {
                    labels.add(elements.get(k).getText());
                }
            }
        }
        labelsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, labels);
        ListView listView = findViewById(R.id.lvLabels);
        listView.setAdapter(labelsAdapter);
    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    */
}
