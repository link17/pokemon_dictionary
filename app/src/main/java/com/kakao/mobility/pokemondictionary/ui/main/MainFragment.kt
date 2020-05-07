package com.kakao.mobility.pokemondictionary.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.view.RxView
import com.kakao.mobility.pokemondictionary.R
import com.kakao.mobility.pokemondictionary.databinding.FragmentMainBinding
import com.kakao.mobility.pokemondictionary.ui.detail.DetailFragment
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false).apply {
        this@MainFragment.viewModel.apply {
            viewModel = this
        }

        lifecycleOwner = this@MainFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("Button")

        RxView.clicks(view.findViewById<Button>(R.id.button)).subscribeBy { fragmentManager?.let { it1 ->
            DetailFragment.newInstance().show(
                it1,DetailFragment.javaClass.name)
        } }

//        RxView.clicks(view.findViewById<Button>(R.id.button))
//            .throttleFirst(3000L, TimeUnit.MILLISECONDS).subscribeBy(onComplete = {
//
//            }, onError = {
//                Timber.e("Button")
//            })
//        viewModel.getUser()

//        view.findViewById<Button>(R.id.button).clicks().observeOn(AndroidSchedulers.mainThread()).subscribe {
//
//            Log.e("","Button click")
//
//        }
    }

    fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
    fun isNotDigit(c: Char) = c !in '0'..'9'


}

//class User(val id: Int, val name: String, val address: String).
//
fun String.lastChar(): Char = this[this.length - 1]
//
//
//fun User.validateBeforeSave() { //User class의 확장 함수로 정의
//    fun validate(value: String, fieldName: String) {
//        if (value.isEmpty()) { // Extension function이므로 id값에 바로 접근가능
//            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
//        }
//    }
//
//    validate(name, "Name")
//    validate(address, "Address")
//}
