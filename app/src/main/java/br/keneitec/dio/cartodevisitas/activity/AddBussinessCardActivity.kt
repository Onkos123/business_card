package br.keneitec.dio.cartodevisitas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.keneitec.dio.cartodevisitas.App
import br.keneitec.dio.cartodevisitas.R
import br.keneitec.dio.cartodevisitas.data.BusinessCard
import br.keneitec.dio.cartodevisitas.databinding.ActivityAddBussinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBussinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnSaveBussinessCard.setOnClickListener {
            if(binding.tilNome.editText?.text?.isNotEmpty() == true){
                if(binding.tilTelefone.editText?.text?.isNotEmpty() == true){
                    if(binding.tilEmail.editText?.text?.isNotEmpty() == true){
                        if(binding.tilEmpresa.editText?.text?.isNotEmpty() == true){
                            saveCard()
                        }else binding.tilEmpresa.editText?.error = getString(R.string.obrigatorio)
                    }else binding.tilEmail.editText?.error = getString(R.string.obrigatorio)
                }else binding.tilTelefone.editText?.error = getString(R.string.obrigatorio)
            }else binding.tilNome.editText?.error = getString(R.string.obrigatorio)

        }

    }

    private fun saveCard() {
        var businessCard: BusinessCard? = null
        if (binding.tilCor.editText?.text?.isEmpty() == true){
            businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = getString(R.string.defoutColor)
            )
        }else{
            businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString().uppercase()
            )
        }
        mainViewModel.insert(businessCard)
        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }
}
