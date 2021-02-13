package com.sleekdeveloper.android.securechat.auth

import androidx.databinding.BindingAdapter
import com.hbb20.CountryCodePicker

object AuthBinding {
    @BindingAdapter("country_code")
    @JvmStatic
    fun setCountryCode(countryCodePicker: CountryCodePicker, code: Int) {
        countryCodePicker.setCountryForPhoneCode(code)
    }
}