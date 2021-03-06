package com.howshea.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.transition.*
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.howshea.home.R
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.item_image_adapter.view.*

class ImageActivity : AppCompatActivity() {
    private val imageList by lazy(LazyThreadSafetyMode.NONE) {
        intent.getStringArrayListExtra(EXTRA_IMG_LIST)
    }

    private val position by lazy(LazyThreadSafetyMode.NONE) {
        intent.getIntExtra(EXTRA_POS, 0)
    }

    companion object {
        private const val EXTRA_IMG_LIST = "imageList"
        private const val EXTRA_POS = "position"
        fun newIntent(context: Context, imageList: List<String>, position: Int): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putStringArrayListExtra(EXTRA_IMG_LIST, ArrayList(imageList))
            intent.putExtra(EXTRA_POS, position)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        view_pager.adapter = ImagePagerAdapter(imageList)
        view_pager.currentItem = position
    }

    private inner class ImagePagerAdapter(private val imageList: List<String>) : PagerAdapter() {
        override fun isViewFromObject(view: View, any: Any) = view == any

        override fun getCount() = imageList.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(R.layout.item_image_adapter, null)
            Glide.with(this@ImageActivity)
                .load(imageList[position])
                .into(view.pv)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.pv.transitionName = "Image $position"
                window.enterTransition = Fade()
                window.exitTransition = Fade()
            }
            view.pv.setOnClickListener { onBackPressed() }
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            container.removeView(any as View)
        }
    }
}
