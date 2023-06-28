package es.travelworld.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


import es.travelworld.practica4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AutoCompleteTextView autoCompleteTextView = binding.mainAges;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ages));

        autoCompleteTextView.setAdapter(arrayAdapter);

        EditText nameEditText = binding.mainInputName;
        EditText surnamesEditText = binding.mainInputSurnames;
        AutoCompleteTextView ageAutoCompleteTextView = binding.mainAges;
        Button submitbutton = binding.mainBtn2;
        binding.mainInputName.addTextChangedListener(textWatcher);
        binding.mainInputSurnames.addTextChangedListener(textWatcher);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputTextName = nameEditText.getText().toString();
                String inputTextSurnames = surnamesEditText.getText().toString();
                String ageSelected = ageAutoCompleteTextView.getText().toString();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ages));

                if (inputTextName.contains(".") || inputTextName.contains("@")) {
                    nameEditText.setError("Ups, no creo que sea correcto, revísalo");

                } else if (inputTextSurnames.contains(".") || inputTextSurnames.contains("@")) {
                    surnamesEditText.setError("Ups, no creo que sea correcto, revísalo");

                } else if (!ageSelected.contains("18-99")) {
                    ageAutoCompleteTextView.setError("Esta app no es para ti");

                } else {

                }
            }
        });

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String text1 = binding.mainInputName.getText().toString().trim();
            String text2 = binding.mainInputSurnames.getText().toString().trim();
            binding.mainBtn2.setEnabled(!text1.isEmpty() && !text2.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void abrirPolitica(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/ml-kit/terms"));
        startActivity(i);
    }

    public void abrirCamara(View view) {
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(i);
    }

}




