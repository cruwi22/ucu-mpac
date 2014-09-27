package com.ucu.ucu_mpac;

import java.sql.SQLException;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BookSearch extends Activity {

	DatabaseHandler db = new DatabaseHandler(this);

	TextView txtAccessno;
	TextView txtTitle;
	TextView txtAuthor;
	TextView txtPublisher;
	TextView txtEdition;
	TextView txtVolume;
	TextView txtPages;
	TextView txtCyear;
	TextView txtCsection;
	TextView txtCopies;
	TextView txtBabarcode;
	TextView txtCompletecn;
	TextView txtFormat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_search);

		txtAccessno = (TextView) findViewById(R.id.txtAccessno);
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtAuthor = (TextView) findViewById(R.id.txtAuthor);
		txtPublisher = (TextView) findViewById(R.id.txtPublisher);
		txtEdition = (TextView) findViewById(R.id.txtEdition);
		txtVolume = (TextView) findViewById(R.id.txtVolume);
		txtPages = (TextView) findViewById(R.id.txtPages);
		txtCyear = (TextView) findViewById(R.id.txtCyear);
		txtCsection = (TextView) findViewById(R.id.txtCsection);
		txtCopies = (TextView) findViewById(R.id.txtCopies);
		txtBabarcode = (TextView) findViewById(R.id.txtBabarcode);
		txtCompletecn = (TextView) findViewById(R.id.txtCompletecn);
		txtFormat = (TextView) findViewById(R.id.txtFormat);

		getIntentTask();
	}

	public void clickAdd(View v) {

		try {

			Cursor c = db.getAccessno(txtAccessno.getText().toString());

			if (c.moveToFirst()) {
				
				Toast.makeText(getApplicationContext(), "It's already in the favorites", Toast.LENGTH_SHORT).show();
				
			} else {

				db.addFavorites(new Favorites(txtAccessno.getText().toString(),
						txtTitle.getText().toString(), txtAuthor.getText().toString(), txtPublisher.getText().toString(),
						txtEdition.getText().toString(), txtVolume.getText().toString(), txtPages.getText().toString(),
						txtCyear.getText().toString(), txtCsection.getText().toString(), txtCopies.getText().toString(),
						txtBabarcode.getText().toString(), txtCompletecn.getText().toString(), txtFormat.getText().toString()));

				Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void getIntentTask() {

		String accessno = getIntent().getStringExtra("accessno");
		String title = getIntent().getStringExtra("title");
		String author = getIntent().getStringExtra("author");
		String publisher = getIntent().getStringExtra("publisher");
		String edition = getIntent().getStringExtra("edition");
		String volume = getIntent().getStringExtra("volume");
		String pages = getIntent().getStringExtra("pages");
		String cyear = getIntent().getStringExtra("cyear");
		String csection = getIntent().getStringExtra("csection");
		String copies = getIntent().getStringExtra("copies");
		String babarcode = getIntent().getStringExtra("babarcode");
		String completecn = getIntent().getStringExtra("completecn");
		String format = getIntent().getStringExtra("format");

		txtAccessno.setText(accessno);
		txtTitle.setText(title);
		txtAuthor.setText(author);
		txtPublisher.setText(publisher);
		txtEdition.setText(edition);
		txtVolume.setText(volume);
		txtPages.setText(pages);
		txtCyear.setText(cyear);
		txtCsection.setText(csection);
		txtCopies.setText(copies);
		txtBabarcode.setText(babarcode);
		txtCompletecn.setText(completecn);
		txtFormat.setText(format);

	}

}
