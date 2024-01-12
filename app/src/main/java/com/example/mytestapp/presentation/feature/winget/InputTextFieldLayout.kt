package com.example.mytestapp.presentation.feature.winget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.mytestapp.R
import com.example.mytestapp.databinding.LayoutInputTextfieldBinding
import com.google.android.material.textfield.TextInputEditText

class InputTextFieldLayout (context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val binding: LayoutInputTextfieldBinding by lazy {
        LayoutInputTextfieldBinding.inflate(LayoutInflater.from(context))
    }
    private lateinit var onTextChanged: (String) -> Unit
    private lateinit var typeFace: Typeface

    private var textTitle: String?
    private var textHint: String?
    private var textType: String?
    private var textError: String?
    private var textOptional: String?
    private var icon: Drawable?
    private var inputType: Int?
    private var maxLength: Int?
    private var minHeight: Float?
    private var isLongClickAble: Boolean? // for can copy and paste?
    private var isOutOfFocusError: Boolean?
    private var fontSize: Float?
    private var isClearError = true
    private var dynamicHeight:Boolean = false

    init {
        initInflate()
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.InputTextFieldLayout, 0, 0
        ).apply {
            try {
                textTitle = getString(R.styleable.InputTextFieldLayout_textTitle)
                textHint = getString(R.styleable.InputTextFieldLayout_textHint)
                textError = getString(R.styleable.InputTextFieldLayout_textError)
                textType = getString(R.styleable.InputTextFieldLayout_textType)

                icon = getDrawable(R.styleable.InputTextFieldLayout_iconAction)
                textOptional = getString(R.styleable.InputTextFieldLayout_textOptional)
                inputType = getInt(
                    R.styleable.InputTextFieldLayout_android_inputType, InputType.TYPE_CLASS_TEXT
                )
                maxLength = getInt(R.styleable.InputTextFieldLayout_android_maxLength, -1)
                isLongClickAble =
                    getBoolean(R.styleable.InputTextFieldLayout_android_longClickable, true)
                isOutOfFocusError =
                    getBoolean(R.styleable.InputTextFieldLayout_outOfFocusError, true)
                fontSize = getDimension(R.styleable.InputTextFieldLayout_fontSize, 0f)
                dynamicHeight = getBoolean(R.styleable.InputTextFieldLayout_dynamicHeight, false)
                minHeight = getDimension(R.styleable.InputTextFieldLayout_editTextMinHeight, 0f)
                setTextHint()
                setTextType()
                setInputType()
                setMaxLength()
                setLongClickAble()
                setFontSize()
                setDynamicHeight()
                setMinimumHeight()

                initView()
            } finally {
                recycle()
            }
        }

    }

    private fun initView() {
        setFocusChange()
    }

    private fun initInflate() {
//        inflate(context, R.layout.layout_input_text_field,this)
        addView(binding.root)
    }

    fun setOnTextChange(listener: (String) -> Unit) {
        onTextChanged = listener
    }

    private fun setTextType() {
        binding.tvType.text = textType
    }



    fun setFocusChange(onOutOfFocus: () -> Unit = {}, isInvokeErrorEmpty: Boolean = false) {
        if (isOutOfFocusError!!) {
            binding.editText.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    if (getEdittext().text.isNullOrEmpty()) {
                        if (isInvokeErrorEmpty) {
                            onOutOfFocus.invoke()
                        } else {
                            //setError(context.getString(R.string.error_fill))
                            setError(textError.orEmpty())
                        }
                    } else {
                        onOutOfFocus.invoke()
                    }
                } else {
                    if (isClearError) {
                        clearError()
                    }
                    isClearError = true
                }
            }
        }
    }

    fun setRequestFocus(isClearError: Boolean) {
        this.isClearError = isClearError
        binding.editText.requestFocus()
    }

    fun setTextCenterVertical() {
        binding.editText.gravity = Gravity.CENTER_VERTICAL
    }

    private fun setTextHint() {
        binding.editText.hint = textHint
    }

    fun setText(text: String) {
        binding.editText.setText(text)
    }

    fun setEnable(enable: Boolean) {
        binding.editText.isEnabled = enable
        if (enable) {
            binding.editText.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    fun setEnableWithoutBackground(value: Boolean) {
        binding.editText.isEnabled = value
        if (value) {
            binding.editText.setBackgroundColor(Color.TRANSPARENT)
        } else {
            // binding.editText.setBackgroundColor(ContextCompat.getColor(context,R.color.color_light_gray))
        }
    }

    fun setDisableWithoutBackground() {
        binding.editText.isEnabled = false
        binding.editText.setBackgroundColor(Color.TRANSPARENT)
    }

    fun setHideUnderLineEditText() {
        //binding.editText.setTextColor(ContextCompat.getColor(context, R.color.color_text_edit_input_disable))
        binding.textInputLayout.boxStrokeWidth = 0
    }

    fun setDisableWithBackground() {
        binding.apply {
            clearText()
            clearError()
            binding.editText.isEnabled = false
            //editText.setBackgroundColor(ContextCompat.getColor(context,R.color.color_light_gray))
        }
    }



    private fun setInputType() {
        //save type face
        typeFace = binding.editText.typeface

        binding.editText.inputType = inputType!!

        //set type face
        binding.editText.typeface = typeFace
    }

    private fun setMaxLength() {
        if (maxLength != -1) {
            binding.editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength!!))
        }
    }

    private fun setDynamicHeight() {
        if (dynamicHeight) {
            val layoutParams = binding.textInputLayout.layoutParams
            layoutParams.height = LayoutParams.WRAP_CONTENT
            binding.textInputLayout.layoutParams = layoutParams
        }
    }

    private fun setLongClickAble() {
        binding.editText.isLongClickable = isLongClickable
    }

    private fun setFontSize() {
        if (fontSize != 0f) {
            binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize!!)
        }
    }

    private fun setMinimumHeight() {
        if (minHeight != 0f) {
            val layoutParams = binding.textInputLayout.layoutParams
            layoutParams.height = LayoutParams.WRAP_CONTENT
            binding.textInputLayout.layoutParams = layoutParams
            binding.editText.minHeight = minHeight?.toInt() ?: 0
        }
    }

    fun setError(message: String) {
        binding.textInputLayout.error = message
        binding.textInputLayout.isErrorEnabled = true
    }

    fun clearError() {
        binding.textInputLayout.error = null
        binding.textInputLayout.isErrorEnabled = false
    }

    fun clearText() {
        binding.editText.setText("")
    }


    fun setHint(hint: String) {
        binding.editText.hint = hint
    }

    fun setEdittextFocusable(isFocus: Boolean) {
        binding.editText.isFocusable = isFocus
    }

    fun getText(): String {
        return binding.editText.text.toString()
    }

    fun getTextOrEmpty(): String {
        return (binding.editText.text ?: "").toString()
    }

    fun getEdittext(): TextInputEditText {
        return binding.editText
    }

    fun setCursorPositionEndText() {
        getEdittext().setSelection(getLengthEdittext())
    }

    fun getLengthEdittext(): Int {
        return binding.editText.length()
    }

    fun afterTextChanged(afterTextChanged: (String) -> Unit) {
        getEdittext().addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //do nothing
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }

}
