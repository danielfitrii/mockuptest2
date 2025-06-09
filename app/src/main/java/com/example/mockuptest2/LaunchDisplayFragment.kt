package com.example.mockuptest2

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class LaunchDisplayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_display, container, false)
    }

    private lateinit var rocketImage: ImageView
    private lateinit var launchText: TextView
    private lateinit var rocketAnim: AnimationDrawable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rocketImage = view.findViewById(R.id.rocketImage)
        launchText = view.findViewById(R.id.launchText)

        rocketImage.setBackgroundResource(R.drawable.rocket_animation)
        (rocketImage.background as? AnimationDrawable)?.let { anim ->
            rocketAnim = anim
        }
    }

    fun updateDisplay(thrust: Int, name: String) {
        rocketAnim.start()
        launchText.text = "Launching $name at thrust level $thrust!"
        // Scale text size between 12sp and 24sp based on thrust (0-100)
        val minSize = 12f
        val maxSize = 24f
        val scale = (thrust / 100f).coerceIn(0f, 1f)
        launchText.textSize = minSize + (maxSize - minSize) * scale
    }

}
