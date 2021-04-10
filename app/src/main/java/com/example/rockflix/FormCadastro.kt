package com.example.rockflix



import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rockflix.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

         //colocando evento de clique no botão cadastrar
        binding.btnCadastrar.setOnClickListener {
            //verificação para ver se o usuario digitou algo no campo de email ou de senha
            //caso estiver vazio os dois vai mostrar uma mensagem

            //setText = para definir um novo texto

            val email = binding.editCadastroEmail.text.toString()
            val senha = binding.editCadastroSenha.text.toString()
            val mensagemErro = binding.MSGERROR


            //caso email ou senha estiver vazio vai aparecer essa mensagem abaixo
            if(email.isEmpty() || senha.isEmpty()){
                mensagemErro.setText("Preencha todos os campos")
            }else {
                //se todos os campos estiverem preenchidos vamos cadastrar o usuario
                CadastrarUsuario()
            }
        }

    }

    //metodo para cadastrar o usuario
    private fun CadastrarUsuario(){
        val email = binding.editCadastroEmail.text.toString()
        val senha = binding.editCadastroSenha.text.toString()
        val mensagemErro = binding.MSGERROR

        //utilizando o firebase
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            //repare que esse it é o it:Task<AuthResult!> que o addOnCompleteListener cria
            if(it.isSuccessful){ //caso o cadastro foi realizado com sucesso vai mostrar uma mensagem
                Toast.makeText(this, "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show()

                //depois de cadastrar com o codigo acima iremos deixar o campo email e senha limpos
                binding.editCadastroEmail.setText("")
                binding.editCadastroSenha.setText("")
                mensagemErro.setText("")
            }
            //addOnFailureListener {} = caso o cadastro der alguma falha, porém com esse metodo podemos especificar o que deu de errado
            //o var it é uma variavel que o proprio metodo cria
        }.addOnFailureListener {

            //FirebaseAuthWeakPasswordException = para dizer ao usuario para ele digiar uma senha com no minimo 6 caracteres
            //FirebaseAuthUserCollisionException = para dizer ao usuario que a conta de email ja existe
            //FirebaseNetworkException = para dizer ao usuario que ele esta sem internet (caso ele tenha a internet muito ruim ou esteja sem internet mesmo)


            //FirebaseNetworkException = precisamos por uma permissão de internet no nosso app, vá na pasta "manifests" e abra o "AndroidManifest.xml"
            //em seguida coloque esse codigo: <uses-permission android:name="android.permission.INTERNET"/>

            var erro = it

            when{
                erro is FirebaseAuthWeakPasswordException -> mensagemErro.setText("Digite uma senha com no mínimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> mensagemErro.setText("Esse email ja foi cadastrado")
                erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet")
                else -> mensagemErro.setText("Erro ao cadastrar o usuário")
            }
        }
    }

    //criando uma function apenas para separar a toolbar, pra nao ficar algo misturado
    private fun Toolbar(){
        //vamos personalizar a toolbar da tela de cadastro por aqui
        //pois pelos themes ja definimos a cor black !! e aqui vamos por White
        //vamos por a imagem rockflix tambem
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_mylogo2))
    }
}