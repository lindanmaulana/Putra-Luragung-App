package com.pab.putraluragungtrans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingPageFragment : Fragment() {
    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_TITLE_SESSION = "Title Session"
        private const val ARG_DESC = "description"
        private const val ARG_IMAGE = "image"

        fun newInstance(title: String, titleSession: String, description: String, imageResId: Int): OnboardingPageFragment {
            val fragment = OnboardingPageFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_TITLE_SESSION, titleSession)
            args.putString(ARG_DESC, description)
            args.putInt(ARG_IMAGE, imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_onboarding_page_fragment, container, false)

        view.findViewById<TextView>(R.id.titleBody).text = arguments?.getString(ARG_TITLE)
        view.findViewById<TextView>(R.id.titleSessionBody).text = arguments?.getString(ARG_TITLE_SESSION)
        view.findViewById<TextView>(R.id.descBody).text = arguments?.getString(ARG_DESC)
        view.findViewById<ImageView>(R.id.imageBody).setImageResource(arguments?.getInt(ARG_IMAGE) ?: 0)

        return view
    }
}