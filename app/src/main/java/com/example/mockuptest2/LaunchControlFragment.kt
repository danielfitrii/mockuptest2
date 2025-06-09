package com.example.mockuptest2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar

class LaunchControlFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_control, container, false)
    }

    interface ControlListener {
        fun onLaunchPressed(thrust: Int, name: String)
    }

    private var listener: ControlListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ControlListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn = view.findViewById<Button>(R.id.btnLaunch)
        val seek = view.findViewById<SeekBar>(R.id.seekThrust)
        val input = view.findViewById<EditText>(R.id.editRocketName)

        btn.setOnClickListener {
            val name = input.text.toString()
            val thrust = seek.progress
            listener?.onLaunchPressed(thrust, name)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        view?.let { view ->
            outState.putString("rocketName", view.findViewById<EditText>(R.id.editRocketName)?.text.toString())
            outState.putInt("thrustLevel", view.findViewById<SeekBar>(R.id.seekThrust)?.progress ?: 0)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            view?.findViewById<EditText>(R.id.editRocketName)?.setText(it.getString("rocketName", ""))
            view?.findViewById<SeekBar>(R.id.seekThrust)?.progress = it.getInt("thrustLevel", 0)
        }
    }

}