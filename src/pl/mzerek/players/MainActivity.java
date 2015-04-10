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

	// { "Robert", "Kamil", "Darek",
	// "Piotrek", "Maciek", "Damian N.", "Arek", "Marek", "Damian M.",
	// "Rafal" };

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

		final TextView textView11 = (TextView) findViewById(R.id.TextView11);
		final TextView textView13 = (TextView) findViewById(R.id.TextView13);

		final TextView textView12 = (TextView) findViewById(R.id.TextView12);
		final TextView textView14 = (TextView) findViewById(R.id.TextView14);

		buttonGenerate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				List<String> membersList = getCurrentTeams();
				int s = membersList.size();

				textView1.setText(checkSize(s, 0) ? membersList.get(0) : "");
				textView2.setText(checkSize(s, 1) ? membersList.get(1) : "");
				textView3.setText(checkSize(s, 2) ? membersList.get(2) : "");
				textView4.setText(checkSize(s, 3) ? membersList.get(3) : "");
				textView5.setText(checkSize(s, 4) ? membersList.get(4) : "");

				textView6.setText(checkSize(s, 5) ? membersList.get(5) : "");
				textView7.setText(checkSize(s, 6) ? membersList.get(6) : "");
				textView8.setText(checkSize(s, 7) ? membersList.get(7) : "");
				textView9.setText(checkSize(s, 8) ? membersList.get(8) : "");
				textView10.setText(checkSize(s, 9) ? membersList.get(9) : "");

				textView11.setText(checkSize(s, 10) ? membersList.get(10) : "");
				textView13.setText(checkSize(s, 11) ? membersList.get(11) : "");

				textView12.setText(checkSize(s, 12) ? membersList.get(12) : "");
				textView14.setText(checkSize(s, 13) ? membersList.get(13) : "");

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
	
	private boolean checkSize(int membersListSize, int index) {
		if (membersListSize > index)
			return true;
		return false;
	}

	private List<String> getCurrentTeams() {
		List<Integer> randomTab = new ArrayList<Integer>();
		List<String> currentTeams = new ArrayList<String>();
		int i = 0;
		while (i < 14) {
			int tmpVal = randomInt();
			if (!randomTab.contains(tmpVal)) {
				randomTab.add(tmpVal);
				i++;
			}
		}

		for (Integer k : randomTab) {
			if (members[k] == null || "".equals(members[k].trim())) {
				continue;
			}
			currentTeams.add(members[k]);
		}
		return currentTeams;
	}

	private int randomInt() {
		return (int) (Math.random() * 14);
	}

	private void reloadPalyersNames() {
		members = readFromFile();
	}

	private static String[] readFromFile() {

		String[] players = new String[14];
		try {
			File heapFile = new File(Environment.getExternalStorageDirectory(),
					"Players.txt");
			if (!heapFile.exists()) {
				heapFile.createNewFile();
			}

			BufferedReader br = new BufferedReader(new FileReader(heapFile));

			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null && i<14) {
				players[i] = line;
				i++;
			}
			br.close();

			// Write to file not delete
			// if (heapFile.canWrite()) {
			// FileWriter heapFileWritter = new FileWriter(
			// heapFile.getAbsoluteFile(), true);
			// BufferedWriter heapBufferWritter = new BufferedWriter(
			// heapFileWritter);
			// for (String s : members) {
			// heapBufferWritter.write(s);
			// heapBufferWritter.newLine();
			// }
			// heapBufferWritter.close();
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}

		return players;
	}
}
