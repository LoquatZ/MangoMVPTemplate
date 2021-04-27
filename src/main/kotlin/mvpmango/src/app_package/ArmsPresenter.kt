package mvpmango.src.app_package

fun armsPresenterJava(
        pageName: String,
        contractPackageName: String,
        presenterPackageName: String,
        needActivity: Boolean,
        needFragment: Boolean
) = """
package ${presenterPackageName};

import android.app.Application;

${scopeStrImportJava(needActivity, needFragment)}
import com.loquat.mango.mvp.BasePresenter;
import com.loquat.mango.http.imageloader.ImageLoader;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import javax.inject.Inject;

import ${contractPackageName}.${pageName}Contract;


${scopeStr(needActivity, needFragment)}
public class ${pageName}Presenter extends BasePresenter<${pageName}Contract.Model, ${pageName}Contract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public ${pageName}Presenter (${pageName}Contract.Model model, ${pageName}Contract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }
}

"""


fun armsPresenterKt(
        pageName: String,
        contractPackageName: String,
        presenterPackageName: String,
        needActivity: Boolean,
        needFragment: Boolean
) = """
package $presenterPackageName

import android.app.Application
${scopeStrImportKt(needActivity, needFragment)}
import com.loquat.mango.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import ${contractPackageName}.${pageName}Contract

${scopeStr(needActivity, needFragment)}
class ${pageName}Presenter
@Inject
constructor(model: ${pageName}Contract.Model, rootView: ${pageName}Contract.View) :
BasePresenter<${pageName}Contract.Model, ${pageName}Contract.View>(model,rootView) {
    @Inject
    lateinit var mErrorHandler:RxErrorHandler
    @Inject
    lateinit var mApplication:Application

    override fun onDestroy() {
          super.onDestroy()
    }
}
"""