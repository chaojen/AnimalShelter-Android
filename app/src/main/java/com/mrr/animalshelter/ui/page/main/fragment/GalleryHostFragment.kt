package com.mrr.animalshelter.ui.page.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mrr.animalshelter.R
import com.mrr.animalshelter.ktx.switchFragment
import com.mrr.animalshelter.ui.base.BaseFragment
import com.mrr.animalshelter.ui.page.main.MainViewModel
import com.mrr.animalshelter.ui.page.main.fragment.gallery.GalleryDetailFragment
import com.mrr.animalshelter.ui.page.main.fragment.gallery.GalleryFilterFragment
import com.mrr.animalshelter.ui.page.main.fragment.gallery.GalleryFragment

class GalleryHostFragment : BaseFragment() {

    companion object {
        const val TAG = "TAG_FRAGMENT_GALLERY_HOST"
        fun newInstance(): GalleryHostFragment {
            return GalleryHostFragment()
        }
    }

    private val mViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_host_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        mViewModel.updateCollectionAnimalsData()
    }

    private fun initView() {
        switchFragment(
            R.id.layContainer,
            GalleryFragment.TAG,
            onNewInstance = { GalleryFragment.newInstance() }
        )
    }

    private fun observe() {
        mViewModel.onLaunchGalleryAnimalDetailToPositionEvent.observe(viewLifecycleOwner, Observer { position ->
            switchFragment(
                R.id.layContainer,
                GalleryDetailFragment.TAG,
                onNewInstance = { GalleryDetailFragment.newInstance(position ?: 0) }
            )
        })
        mViewModel.onLaunchGalleryFilterEvent.observe(viewLifecycleOwner, Observer {
            switchFragment(
                R.id.layContainer,
                GalleryFilterFragment.TAG,
                onNewInstance = { GalleryFilterFragment.newInstance() }
            )
        })
    }
}