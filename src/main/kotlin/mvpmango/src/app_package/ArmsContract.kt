package mvpmango.src.app_package

fun armsContractJava(
        pageName: String,
        contractPackageName: String,
) = """
package ${contractPackageName};

import android.app.Activity;
import com.loquat.mango.mvp.IView;
import com.loquat.mango.mvp.IModel;

public interface ${pageName}Contract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        Activity getActivity();
    }
    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel{

    }
}
"""


fun armsContractKt(
        pageName: String,
        contractPackageName: String,
) = """
package $contractPackageName

import android.app.Activity
import com.loquat.mango.mvp.IView
import com.loquat.mango.mvp.IModel

interface ${pageName}Contract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{
        val activity: Activity
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel

}
"""