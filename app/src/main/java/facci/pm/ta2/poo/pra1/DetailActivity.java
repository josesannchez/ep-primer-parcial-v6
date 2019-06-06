package facci.pm.ta2.poo.pra1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import facci.pm.ta2.poo.datalevel.DataException;
import facci.pm.ta2.poo.datalevel.DataObject;
import facci.pm.ta2.poo.datalevel.DataQuery;
import facci.pm.ta2.poo.datalevel.GetCallback;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewDescripcion;

    String euro = "\u20ac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewDescripcion = (TextView)findViewById(R.id.TextViewDescripcion);
        textViewDescripcion.setMovementMethod(LinkMovementMethod.getInstance());


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR1 :: Detail");

        String object_id = getIntent().getStringExtra("object_id");

        DataQuery query = DataQuery.get("item");
        query.getInBackground(object_id, new GetCallback<DataObject>() {
            @Override
            public void done(DataObject object, DataException data) {

                    TextView textViewTitle = findViewById(R.id.TextViewTitulo);
                    textViewTitle.setText((String) object.get("titulo"));

                    ImageView  thumbnail= (ImageView) findViewById(R.id.ImageViewImagenDetail);
                    thumbnail.setImageBitmap((Bitmap) object.get("image"));

                    TextView textViewDescripcion = findViewById(R.id.TextViewDescripcion);
                    textViewDescripcion.setText((String) object.get("descripcion"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent;
        intent = new Intent(DetailActivity.this, ResultsActivity.class);
        startActivity(intent);
        finish();

    }
}
