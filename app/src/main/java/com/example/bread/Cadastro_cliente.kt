package com.example.bread

import android.app.ProgressDialog
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*

class Cadastro_cliente : AppCompatActivity() {


    //interface de usuariario
    private var etNomeCompleto: EditText? = null
    private var etemail: EditText? = null
    private var etsenha: EditText? = null
    private var etcriar: Button? = null
    private var mProgress: ProgressDialog? = null



    private var mDataBaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "Cadastro_cliente"


    private var Nomecompleto: String? = null
    private var email_Cadastro: String? = null
    private var senha: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        initialise()
    }

    private fun initialise() {
        etNomeCompleto = findViewById(R.id.nome) as EditText
        etemail = findViewById(R.id.email_cadastro) as EditText
        etsenha = findViewById(R.id.senha) as EditText
        etcriar = findViewById<Button>(R.id.criarconta) as Button
        mProgress = ProgressDialog(this)

        mDatabase = FirebaseDatabase.getInstance()
        mDataBaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        etcriar!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {
        Nomecompleto = etNomeCompleto?.text.toString()
        email_Cadastro = etemail?.text.toString()
        senha = etsenha?.text.toString()

        if (!TextUtils.isEmpty(Nomecompleto)
            && !TextUtils.isEmpty(email_Cadastro) && !TextUtils.isEmpty(senha)
        ) {
            Toast.makeText(this, "Cadastro Criado Com Sucesso", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
        mProgress!!.setMessage("Registrando Usuario")
        mProgress!!.show()

        mAuth!!
            .createUserWithEmailAndPassword(email_Cadastro!!, senha!!)
            .addOnCompleteListener(this) { task ->
                mProgress!!.hide()
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val userId = mAuth!!.currentUser!!.uid


                    VerifyEmail();
                    val currentUserdb = mDataBaseReference!!.child(userId)
                    currentUserdb.child("nomeCompleto").setValue(Nomecompleto)
                    updateUserInfoAndUI()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@Cadastro_cliente, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }


            }


    }

    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this@Cadastro_cliente, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun VerifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@Cadastro_cliente,
                        "Verification email sent to " + mUser.getEmail(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(
                        this@Cadastro_cliente,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}


