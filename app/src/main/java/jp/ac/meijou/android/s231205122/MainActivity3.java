package jp.ac.meijou.android.s231205122;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s231205122.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        // OK ボタン
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(ret -> "result:" + ret)
                                .ifPresent(text -> binding.textResult.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        // Cancel ボタン
                        binding.textResult.setText("Result: Canceled");
                    }
                    default -> {
                        // 想定外のこと
                        binding.textResult.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // ----- 呼び出し設定 -----

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ----- 呼び出し終わり -----

        // ----- 初期化 -----


        // 明示的インテント
        binding.buttonMeiji.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            var text = "aa";
            intent.putExtra("text", text);
            startActivity(intent);
        });

        // 暗黙的インテント
        binding.buttonAnmoku.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.meijo-u.ac.jp"));
            startActivity(intent);
        });

        // 送信ボタン
        binding.buttonSend.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            var text = binding.editTextText.getText().toString();
            intent.putExtra("text", text);
            startActivity(intent);
        });

        binding.buttonLaunch.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            // MainActivityから帰って来た時に getActivityResult で処理をする
            getActivityResult.launch(intent);
        });
    }
}