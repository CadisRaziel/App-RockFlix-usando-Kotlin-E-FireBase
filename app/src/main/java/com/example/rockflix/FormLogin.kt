package com.example.rockflix

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rockflix.databinding.ActivityFormLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        //iniciando o metodo pra ve se tem usuario logado
        VerificarUsuarioLogado()


        //evento de click na mensagem de cadastro
        binding.txtCadastro.setOnClickListener {

            //intent para poder ir para outra tela
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)

            //veja que nao utilizamos o finish()
            //pois o finish é para impedir que voce volte telas com os botoes do celular!!
        }


        //colocando o evento click no botao entrar
        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.msgErro

            if(email.isEmpty() || senha.isEmpty()){ //se login e senha nao tiver preenchido mostra essa mensagem
                mensagemErro.setText("Preencha todos os campos")
            }else { //caso o login e senha estiver preenchido vamos authenticar o usuario
                AuthenticarUsuario()
            }
        }
    }

    private fun AuthenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagemErro = binding.msgErro

        // signInWithEmailAndPassword = logar com o email e senha

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            //lembrando a variavel it é criada automaticamente pelo metodo "addOnCompleteListener"
            if(it.isSuccessful ){//se o usuario foi logado com sucesos
                Toast.makeText(this, "Login efetuado com sucesso",Toast.LENGTH_SHORT).show()

                //quando o login for efetuado com sucesso ele vai para a tela de shows
                IrParaTelaDeShows()
            }
        }.addOnFailureListener {
            //trabalhando com erros

            //FirebaseAuthInvalidCredentialsException = email ou senha estão incorretos
            //FirebaseNetworkException = para informar que o usuario esta sem internet ou se a conexao for lenta demais

            //lembrando esse "it" é uma exception do addOnfailureListnet (ele que cria o it)
            var erro = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> mensagemErro.setText("E-mail ou senha estão incorretos")
                erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet")
                else -> mensagemErro.setText("Erro ao logar usuário")
            }
        }
    }

    //metodo para verificar se o usuario esta logado ou não
    //se ele estiver logado e fechar o app e abrir de novo ele vai entrar direto na tela de shows(vai continua logado mesmo fechando o app)
    //se ele deslogar fechar o app e entrar de novo ele vai para tela de login
    private fun VerificarUsuarioLogado(){
        //currentUser = vai pegar o meu usuario atual que foi logado
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if(usuarioLogado != null){ //se o usuario logado for diferente de nullo(ou seja tem um usuario atual logado) ele vai pra proxima tela que é de show
            IrParaTelaDeShows()
        }
    }

    private fun IrParaTelaDeShows(){
        val intent = Intent(this, ListaDeShows::class.java)
        startActivity(intent)
        finish()
    }
}