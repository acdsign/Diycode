package com.adgvcxz.diycode.ui.base

import android.os.Parcelable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

abstract class BaseFragmentViewModel: Parcelable {
    abstract fun contentId(): Int

    open fun restore(model: BaseFragmentViewModel) {

    }
}