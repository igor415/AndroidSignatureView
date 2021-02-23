package com.varivoda.igor.androidsignatureview

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        description.setOnCheckedChangeListener { _, isChecked ->
            if(!isChecked){
                sig.hideDescriptionText()
                sig.invalidate()
            }else{
                sig.showDescriptionText()
                sig.invalidate()
            }
        }
        signatureLine.setOnCheckedChangeListener { _, isChecked ->
            if(!isChecked){
                sig.hideSignatureLine()
                sig.invalidate()
            }else{
                sig.showSignatureLine()
                sig.invalidate()
            }
        }
        button.setOnClickListener {
            sig.clearSignature()

        }

        outlineWidth.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                sig.setOutlineWidth(progress/10f)
                sig.invalidate()
            }
        })
    }
}