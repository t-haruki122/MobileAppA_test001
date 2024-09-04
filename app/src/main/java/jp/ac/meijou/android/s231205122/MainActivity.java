package jp.ac.meijou.android.s231205122;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.prefs.Preferences;

import jp.ac.meijou.android.s231205122.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    private String fileKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初期設定?
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        prefDataStore = PrefDataStore.getInstance(this);
        fileKey = "test";

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // クラスと紐づいたレイアウトからインスタンスを作成する
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // this is textに
        binding.text.setText(R.string.text);

        // データ読み込み
        prefDataStore.getString(fileKey)
                .ifPresent(name -> binding.text.setText(name));

        // idを指定してJavaからViewを操作する
        // TextView text = findViewById(R.id.text);
        // text.setText(R.string.name);

        // ボタンが押された時に実行する動作
        /*
        int buttonCount = 0;
        binding.button.setOnClickListener(view -> {
            if (binding.text.getText().equals("this is text")) {
                binding.text.setText(R.string.name);
                binding.androidGreen.setVisibility(View.VISIBLE);
                binding.androidRed.setVisibility(View.INVISIBLE);
            }
            else {
                binding.text.setText(R.string.text);
                binding.androidGreen.setVisibility(View.INVISIBLE);
                binding.androidRed.setVisibility(View.VISIBLE);
            }
        });
        */

        // ????????? テキストチェンジリスナー
        /*
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // テキストが更新される直前に呼ばれる
                ;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // 文字を1つ入力された時に呼ばれる
                ;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // テキストが更新された後に呼ばれる
                binding.text.setText(editable.toString());
            }
        });
         */

        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString(fileKey, text);
            binding.text.setText(text);
        });

        // ???????? クリックリスナー
        binding.changeButton.setOnClickListener(view -> {
            // データ読み込み
            prefDataStore.getString(fileKey)
                    .ifPresent(name -> binding.text.setText(name));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.text.setText("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.text.setText("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        var text = binding.editTextText.getText().toString();
        prefDataStore.setString(fileKey, text);
    }
}

