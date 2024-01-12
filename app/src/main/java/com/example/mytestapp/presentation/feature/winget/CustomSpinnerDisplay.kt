package com.example.mytestapp.presentation.feature.winget

import android.content.Context
import android.util.AttributeSet
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import com.example.mytestapp.R

class CustomSpinnerDisplay: AppCompatSpinner {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var isShowPopup = false
    var mOpenInitiated = false
    var setSelected = false


    var onPopupWindowOpened: ((Spinner) -> Unit)? = null
    var onPopupWindowClosed: ((Spinner) -> Unit)? = null
    var onActionError: (() -> Unit)? = null

    override fun performClick(): Boolean {
        isShowPopup = true
        mOpenInitiated = true
        //clearError()
        val result = super.performClick()
        onPopupWindowOpened?.invoke(this)
        return result
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (!hasFocus && !setSelected) {
            if (!isShowPopup) {
                //setError()
            }
        } else if (hasBeenOpened() && hasFocus) {
            performClosedEvent()
        }
    }

    private fun performClosedEvent() {
        isShowPopup = false
        onPopupWindowClosed?.invoke(this)
        if (!setSelected) {
            //setError()
        }
    }

    private fun hasBeenOpened(): Boolean {
        return isShowPopup
    }


    fun setError() {
        onActionError?.invoke()
        setBackgroundResource(R.drawable.bg_achievement)
    }



}
