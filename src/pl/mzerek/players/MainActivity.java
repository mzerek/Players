package pl.mzerek.players;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.players.R;

public class MainActivity extends Activity {

	public static String[] members = readFromFile();
		
//		{ "Robert", "Kamil", "Darek",
//			"Piotrek", "Maciek", "Damian N.", "Arek", "Marek", "Damian M.",
//			"Rafal" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonGenerate = (Button) findViewById(R.id.button1);
		Button buttonReload = (Button) findViewById(R.id.button2);
		final TextView textView1 = (TextView) findViewById(R.id.TextView1);
		final TextView textView2 = (TextView) findViewById(R.id.TextView2);
		final TextView textView3 = (TextView) findViewById(R.id.TextView3);
		final TextView textView4 = (TextView) findViewById(R.id.TextView4);
		final TextView textView5 = (TextView) findViewById(R.id.TextView5);

		final TextView textView6 = (TextView) findViewById(R.id.TextView6);
		final TextView textView7 = (TextView) findViewById(R.id.TextView7);
		final TextView textView8 = (TextView) findViewById(R.id.TextView8);
		final TextView textView9 = (TextView) findViewById(R.id.TextView9);
		final TextView textView10 = (TextView) findViewById(R.id.TextView10);

		buttonGenerate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				List<String> membersList = getCurrentTeams();
				if (membersList.size() >= 10) {
					textView1.setText(membersList.get(0));
					textView2.setText(membersList.get(1));
					textView3.setText(membersList.get(2));
					textView4.setText(membersList.get(3));
					textView5.setText(membersList.get(4));

					textView6.setText(membersList.get(5));
					textView7.setText(membersList.get(6));
					textView8.setText(membersList.get(7));
					textView9.setText(membersList.get(8));
					textView10.setText(membersList.get(9));
				}

			}
		});
		
		buttonReload.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				reloadPalyersNames();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private List<String> getCurrentTeams() {
		List<Integer> randomTab = new ArrayList<Integer>();
		List<String> currentTeams = new ArrayList<String>();
		int i = 0;
		while (i < 10) {
			int tmpVal = randomInt();
			if (!randomTab.contains(tmpVal)) {
				randomTab.add(tmpVal);
				i++;
			}
		}

		for (Integer k : randomTab) {
			System.out.print(k + " | ");
		}
		System.out.println(" ");

		int count = 0;

		for (Integer k : randomTab) {
			// if (count == 5) {
			// currentTeams.add("========");
			// }
			currentTeams.add(members[k - 1]);
			count++;
		}

		System.out.println(" ");

		for (String member : currentTeams) {
			System.out.println(member);
		}

		readFromFile();

		return currentTeams;
	}

	private int randomInt() {
		return (int) (Math.random() * 10 + 1);
	}
	
	private void reloadPalyersNames(){
		members = readFromFile();
	}

	private static String[] readFromFile() {

		String[] players = new String[10];
		try {
			File heapFile = new File(Environment.getExternalStorageDirectory(),
					"Players.txt");
			if (!heapFile.exists()) {
				heapFile.createNewFile();
			}
			
			BufferedReader br = new BufferedReader(new FileReader(heapFile));

			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null && i < 10) {
				players[i] = line;
				i++;
				System.out.println(line);
			}
			br.close();
			
//			if (heapFile.canWrite()) {
//				FileWriter heapFileWritter = new FileWriter(
//						heapFile.getAbsoluteFile(), true);
//				BufferedWriter heapBufferWritter = new BufferedWriter(
//						heapFileWritter);
//				for (String s : members) {
//					heapBufferWritter.write(s);
//					heapBufferWritter.newLine();
//				}
//				heapBufferWritter.close();
//			}

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}


		return players;
	}
}
