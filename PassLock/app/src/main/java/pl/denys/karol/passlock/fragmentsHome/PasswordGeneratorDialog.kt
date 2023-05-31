package pl.denys.karol.passlock.fragmentsHome

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.DialogFragment
import pl.denys.karol.passlock.databinding.GenPassBinding



class PasswordGeneratorDialog() : DialogFragment() {

    private  lateinit var binding : GenPassBinding
    private  var length : Int = 0
    private var pass = ""
    private lateinit var listener : OnCopyListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GenPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generatePassword.setOnClickListener {
            println("click")
            pass = ""
            length = binding.lengthSeekBar.progress

            var list  = ArrayList<Int>()
            if(binding.capitalsSwitch.isChecked)
                list.add(0)
            if(binding.smallLetterSwitch.isChecked)
                list.add(1)
            if(binding.numberSwitch.isChecked)
                list.add(2)
            if(binding.symbolSwitch.isChecked)
                list.add(3)

            for(i in 1..length){
                var choice = list.random()
                when(choice){
                    0-> pass += ('A'..'Z').random().toString()
                    1-> pass += ('a'..'z').random().toString()
                    2-> pass += ('0'..'9').random().toString()
                    3-> pass += listOf('!','@','#','$','%','&','*','+','=','-','~').random().toString()
                }
            }
            binding.generatedPassword.text = pass
            binding.generatePassword.text = "Regenerate"
            println(pass)

        }
        binding.copyPassword.setOnClickListener {
            listener.sendPassword(pass)
            Toast.makeText(requireContext(),"Password Selected",Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.lengthSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.lengthText.text = "Length: $p1"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })




    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as OnCopyListener
    }

    interface OnCopyListener{
        fun sendPassword(password:String)
    }

}