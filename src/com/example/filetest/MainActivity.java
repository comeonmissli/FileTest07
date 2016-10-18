package com.example.filetest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
private Button read,write;
private EditText readText,writeText;
private String fileName="content.text";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		read=(Button) this.findViewById(R.id.read);
		write=(Button) this.findViewById(R.id.write);
		readText=(EditText) this.findViewById(R.id.readText);
		writeText=(EditText) this.findViewById(R.id.writeText);
		
		read.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				readText.setText(read());
				
			}
		});
		
		write.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				write(writeText.getText().toString());
				
			}
		});
	}

	
	public void write(String content) {
		try{
		FileOutputStream fos=openFileOutput(fileName,Context.MODE_APPEND);
		PrintStream ps=new PrintStream(fos);
		ps.print(content);
		ps.close();
		fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String read() {
		StringBuilder sbBuilder=new StringBuilder("");
		try{
			FileInputStream is=openFileInput(fileName);
			byte[] buffer=new byte[64];
			int hasRead;
			while((hasRead=is.read(buffer))!=-1){
				sbBuilder.append(new String(buffer,0,hasRead));
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return sbBuilder.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
