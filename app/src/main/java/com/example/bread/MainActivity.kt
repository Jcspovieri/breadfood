package com.example.bread

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bread.ui.feddActivity2
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

private val TAG = "TelaLogin"
private  var email: String?= null
private var Senha: String?= null
lateinit var mProgressBar: ProgressDialog
// banco de dados
lateinit var mAuth: FirebaseAuth
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth =  FirebaseAuth.getInstance()
        mProgressBar = ProgressDialog(this)
        aqui!!.setOnClickListener {
            val Intent: Intent = Intent(this, Cadastro_cliente::class.java)
            startActivity(Intent)
        }
        button.setOnClickListener {
            loginUser()
        }
    }
    private fun loginUser() {
        email = etemail?.text.toString()
        Senha = etsenha?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(Senha)) {
            mProgressBar!!.setMessage("Verificando Usuario")
            mProgressBar!!.show()

            Log.d(TAG, "Login do Usuario ")
            mAuth!!.signInWithEmailAndPassword(email!!, Senha!!)
                .addOnCompleteListener(this) { task ->
                    mProgressBar!!.hide()

                    if (task.isSuccessful) {
                        Log.d(TAG, "Logado com Sucesso")
                        UpdateUi()
                    } else {
                        Log.e(TAG, "Erro ao Logar", task.exception)
                        Toast.makeText(this@MainActivity, "Autenticação Falhou", Toast.LENGTH_SHORT)
                            .show()
                    }


                }
        } else {
            Toast.makeText(this@MainActivity, "Tente Novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUi() {
        val intent = Intent(this@MainActivity, feddActivity2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun openFeedPage(view: View) {
        val intent = Intent(this@MainActivity, PostActivity::class.java)
        startActivity(intent)
    }
}



