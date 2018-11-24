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
import java.util.Objects;

import static com.numus.budgee.R.string.default_web_client_id;

public class Login extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {

    private static final int RC_SING_IN = 9001;
    private static final String TAG = "Testing";
    public static final String key = "userStorage";

    public FirebaseAuth mAuth;
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
        this.db = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == RC_SING_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSigmInResult(result);
        }*/
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        try {
            handleSigmInResult(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleSigmInResult(GoogleSignInResult result) throws InterruptedException {

        if (result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct);

            /*Token token = new Token();
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,7);

            Log.i(TAG,String.valueOf(mAuth));

            //QUERY Example
            Wallet wallet = new Wallet(2000.00,100, date.getTime(), cal.getTime().getTime(),"wallet1",token.generate(),0);
            wallet.setContext(context);*/
            // force uid to don't be null object
            //Thread.sleep(3000);
            //String uid = mAuth.getCurrentUser().getUid();

            //this.db.child("Users/"+uid+"/wallet").child(wallet.getToken()).setValue(wallet);


            // QUERY add expense
            /*Expense expense = new Expense("comida",200, date.getTime(),"food",token.generate(),false);
            expense.setContext(context);
            this.db.child("Users/"+uid+"/expense").child(expense.getToken()).setValue(expense);
            expense.updateDataBase(this.context);
            expense.deleteExpense();*/

            //DELETE WALLET OR EXPENSE
            //wallet.deleteWallet();
            //expense.deleteExpense();
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }else{
            Log.i(TAG,"Error no es success");
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
                            //editor.putString("uid",user.getUid());
                            //editor.commit();

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