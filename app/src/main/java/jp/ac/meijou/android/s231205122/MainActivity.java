package jp.ac.meijou.android.s231205122;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s231205122.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初期設定?
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // クラスと紐づいたレイアウトからインスタンスを作成する
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // クラスのフィールドとしてアクセスできる
        binding.text.setText(R.string.text);
        var green = ContextCompat.getDrawable(this, R.drawable.ic_android_green_50dp);
        binding.androidRed.setImageDrawable(green);

        // idを指定してJavaからViewを操作する
        // TextView text = findViewById(R.id.text);
        // text.setText(R.string.name);
    }
}