package sg.edu.rp.c346.id19023702.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    Button btnWrite, btnRead;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        tv = findViewById(R.id.tv);

        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(MainActivity.this, permission, 0);

        String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
        File folder_I = new File(folderLocation_I);
        if (folder_I.exists() == false) {
            boolean result = folder_I.mkdir();
            if (result == true) {
                Log.d("File Read/Write", "Folder created");
            }
        }
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String folderLocation =
                            Environment.getExternalStorageDirectory()
                                    .getAbsolutePath() + "/Folder";
                    File folder = new File(folderLocation);
                    if (folder.exists() == false){
                        boolean result = folder.mkdir();
                        if (result == true){
                            Log.d("File Read/Write", "Folder created");
                        }
                    }
                    String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
                    File targetFile_I = new File(folderLocation_I, "data.txt");
                    FileWriter writer_I = new FileWriter(targetFile_I, true);
                    writer_I.write("test data" + "\n");
                    writer_I.flush();
                    writer_I.close();
                    try {
                        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                        File targetFile = new File(folderLocation, "data.txt");
                        FileWriter writer = new FileWriter(targetFile, true);
                        writer.write("Hello world"+"\n");
                        writer.flush();
                        writer.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                File targetFile = new File(folderLocation, "data.txt");
                if (targetFile.exists() == true){
                    String data ="";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                    tv.setText(data);
                }
                if (targetFile.exists() == true){
                    String data ="";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });
    }
}
