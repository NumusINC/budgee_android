package com.numus.budgee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import static com.numus.budgee.R.string.default_web_client_id;

public class Login extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {

    private static final int RC_SING_IN = 9001;
    private static final String TAG = "Testing";
    public static final String key = "userStorage";

    private FirebaseAuth mAuth;
    private GoogleSignInOptions gso;
    private DatabaseReference db;

    Context context = this;
    GoogleApiClient mGoogleApiClient;
    SharedPreferences userStorage;
    SharedPreferences.Editor editor;

    public void login(View view) {
        // Configure Google Sign In
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SING_IN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userStorage = context.getSharedPreferences("com.numus.budgee.UserStorage", Context.MODE_PRIVATE);
        editor = userStorage.edit();

        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();


    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SING_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSigmInResult(result);
        }
    }

    private void handleSigmInResult(GoogleSignInResult result) {

        if (result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct);
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            User dataUser = new User(user.getDisplayName(), user.getEmail());
                            db.child("Users").child(user.getUid() + "/info").setValue(dataUser);

                            //save data in storage file and commit
                            editor.putString("uid",user.getUid());
                            editor.commit();

                            // Generate a Token
                            Token token = new Token();
                            Log.i(TAG,"Token value: " + token.generate());

                            Date date = new Date();
                            // Calendar get Date element
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE,7);

                            Wallet wallet = new Wallet(2000.00,100, date.getTime(), cal.getTime().getTime(),"wallet1",token.generate());
                            wallet.setContext(context);
                            String uid = mAuth.getCurrentUser().getUid();
                            db.child("Users/"+uid+"/wallet").child(wallet.getToken()).setValue(wallet);

                            Expense expense = new Expense("comida",200, date.getTime(),"food",token.generate(),false);
                            //expense.setContext(context);
                            db.child("Users/"+uid+"/expense").child(expense.getToken()).setValue(expense);
                            //expense.updateDataBase();
                            expense.deleteExpense();
                            //wallet.deleteWallet();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG,String.valueOf(connectionResult));
    }
}
