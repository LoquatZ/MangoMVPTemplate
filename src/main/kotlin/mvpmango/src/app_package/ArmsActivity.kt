package mvpmango.src.app_package

import com.android.tools.idea.wizard.template.extractLetters


fun armsActivityJava(
        packageName: String,
        pageName:String,
        activityPackageName:String,
        componentPackageName:String,
        contractPackageName:String,
        presenterPackageName:String,
        activityLayoutName:String
)="""
package ${activityPackageName};

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yuang.commonsdk.common.CommonActivity;
import com.loquat.mango.di.component.AppComponent;

import ${componentPackageName}.Dagger${pageName}Component;
import ${contractPackageName}.${pageName}Contract;
import ${presenterPackageName}.${pageName}Presenter;

import ${packageName}.R;

public class ${pageName}Activity extends CommonActivity<${pageName}Presenter> implements ${pageName}Contract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.${activityLayoutName};
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
"""


fun armsActivityKt(
        packageName: String,
        pageName:String,
        activityPackageName:String,
        componentPackageName:String,
        contractPackageName:String,
        presenterPackageName:String,
        activityLayoutName:String,
        moudlePackageName:String
)="""
package $activityPackageName

import android.os.Bundle
import android.app.Activity
import me.yuang.commonsdk.common.CommonActivity
import com.loquat.mango.di.component.AppComponent

import ${componentPackageName}.Dagger${pageName}Component
import ${moudlePackageName}.${pageName}Module
import ${contractPackageName}.${pageName}Contract
import ${presenterPackageName}.${pageName}Presenter

import ${packageName}.R

class ${pageName}Activity : CommonActivity<${pageName}Presenter>() , ${pageName}Contract.View {

    override fun setupActivityComponent(appComponent:AppComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${extractLetters(pageName[0].toLowerCase().toString())}${pageName.substring(1,pageName.length)}Module(${pageName}Module(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState:Bundle?):Int = R.layout.${activityLayoutName}


    override fun initData(savedInstanceState:Bundle?) {

    }

    override fun setListeners() {

    }

    override val activity: Activity = this
    
}

"""