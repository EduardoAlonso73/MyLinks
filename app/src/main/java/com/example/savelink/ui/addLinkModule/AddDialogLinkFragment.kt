package com.example.savelink.ui.addLinkModule

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.FragmentDialogAddBinding
import com.example.savelink.ui.addLinkModule.addLinkViewModel.LinkAddViewModel

class AddDialogLinkFragment: DialogFragment(), DialogInterface.OnShowListener  {

    private var binding: FragmentDialogAddBinding?=null
    private var positiveBtn: Button? = null
    private var negativeBtn: Button? = null
    private  lateinit var mAddViewModel: LinkAddViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mAddViewModel= ViewModelProvider(requireActivity())[LinkAddViewModel::class.java]
        activity?.let { activity ->
            binding=FragmentDialogAddBinding.inflate(LayoutInflater.from(context))
            binding?.let {
                val builder = AlertDialog.Builder(activity)
                    .setTitle("Agregar Link")
                    .setPositiveButton("Agregar", null)
                    .setNegativeButton("Cancelar", null)
                    .setView(it.root)

                return builder.create().apply { setOnShowListener(this@AddDialogLinkFragment) }
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }




    override fun onShow(dialog: DialogInterface?) {
        val dialogInstance=dialog as? AlertDialog
        dialogInstance?.let {AlertDialog->
            positiveBtn=AlertDialog.getButton(Dialog.BUTTON_POSITIVE)
            negativeBtn=AlertDialog.getButton(Dialog.BUTTON_NEGATIVE)
            negativeBtn?.setOnClickListener{ dismiss()}
            positiveBtn?.setOnClickListener{ binding?.let {
                val link=LinkEnt(link = binding?.etName?.text.toString().trim())
                mAddViewModel.saveLink(link)
                dismiss()
            }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}