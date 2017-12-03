package com.example.thevatsal.instagramclone;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 2;
    private Uri uri = null;
    private ImageButton imageButton;
    private EditText editName;
    private EditText editDesc;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        editName = (EditText) findViewById(R.id.editName);
        editDesc = (EditText) findViewById(R.id.editDesc);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getInstance().getReference().child("InstaApp");
    }
    public void imageButtonClicked(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            uri = data.getData();
            imageButton = (ImageButton) findViewById(R.id.imageButton);
            imageButton.setImageURI(uri);



        }

    }
    public void submitButtonClicked(View view){

        final String titleValue = editName.getText().toString().trim();
        final String titleDesc = editDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(titleValue) && !TextUtils.isEmpty(titleDesc)){
            StorageReference filepath = storageReference.child("PostImage").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(PostActivity.this,"Upload Completed", Toast.LENGTH_LONG).show();
                    DatabaseReference newPost = databaseReference.push();
                    newPost.child("title").setValue(titleValue);
                    newPost.child("desc").setValue(titleDesc);
                    newPost.child("imageurl").setValue(downloadurl.toString());

                }
            });
        }

    }


}
