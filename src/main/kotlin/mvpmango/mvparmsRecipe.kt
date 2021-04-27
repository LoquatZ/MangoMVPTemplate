package mvpmango

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.underscoreToCamelCase
import mvpmango.androidManifest.manifestTemplateXml
import mvpmango.src.app_package.*
import mvpmango.res.layout.templateXml

/**
 * @CreateDate:     2021/4/1
 * @Author:         Creator
 * @Description:    菜单
 */
fun RecipeExecutor.mvparmsRecipe(
        moduleData: ModuleTemplateData,
        pageName: String,
        packageName: String,
        needActivity: Boolean,
        activityLayoutName: String,
        generateActivityLayout: Boolean,
        activityPackageName: String,
        needFragment: Boolean,
        fragmentLayoutName: String,
        generateFragmentLayout: Boolean,
        fragmentPackageName: String,
        needContract: Boolean,
        contractPackageName: String,
        needPresenter: Boolean,
        presenterPackageName: String,
        needModel: Boolean,
        modelPackageName: String,
        needDagger: Boolean,
        componentPackageName: String,
        moudlePackageName: String
) {

    val (projectData, srcOut, resOut,manifestOut) = moduleData

    val ktOrJavaExt = projectData.language.extension

    if (needActivity && needFragment){
        throw IllegalArgumentException("activity和fragment不可同时选择")
    }


    if (needActivity) {
        mergeXml(manifestTemplateXml(packageName,activityPackageName,"${pageName}Activity"), manifestOut.resolve("AndroidManifest.xml"))
    }

    if (needActivity && generateActivityLayout) {
        save(templateXml(), resOut.resolve("layout/${activityLayoutName}.xml"))
    }

    if (needFragment && generateFragmentLayout) {
        save(templateXml(), resOut.resolve("layout/${fragmentLayoutName}.xml"))
    }


    if (needActivity) {
        if (ktOrJavaExt == "java")
            save(armsActivityJava(packageName, pageName, activityPackageName, componentPackageName, contractPackageName, presenterPackageName, activityLayoutName), srcOut.resolve("feature/ui/activity/${pageName}Activity.${ktOrJavaExt}"))
        else
            save(armsActivityKt(packageName, pageName, activityPackageName, componentPackageName, contractPackageName, presenterPackageName, activityLayoutName, moudlePackageName), srcOut.resolve("feature/ui/activity/${pageName}Activity.${ktOrJavaExt}"))
    }

    if (needFragment) {
        if (ktOrJavaExt == "java")
            save(armsFragmentJava(pageName, contractPackageName, fragmentPackageName, componentPackageName, presenterPackageName, packageName, fragmentLayoutName), srcOut.resolve("feature/ui/fragment/${pageName}Fragment.${ktOrJavaExt}"))
        else
            save(armsFragmentKt(pageName, contractPackageName, fragmentPackageName, componentPackageName, presenterPackageName, packageName, fragmentLayoutName, moudlePackageName), srcOut.resolve("feature/ui/fragment/${pageName}Fragment.${ktOrJavaExt}"))
    }

    if (needContract) {
        if (ktOrJavaExt == "java")
            save(armsContractJava(pageName, contractPackageName), srcOut.resolve("feature/contract/${pageName}Contract.${ktOrJavaExt}"))
        else
            save(armsContractKt(pageName, contractPackageName), srcOut.resolve("feature/contract/${pageName}Contract.${ktOrJavaExt}"))
    }


    if (needPresenter) {
        if (ktOrJavaExt == "java")
            save(armsPresenterJava(pageName, contractPackageName, presenterPackageName, needActivity, needFragment), srcOut.resolve("feature/presenter/${pageName}Presenter.${ktOrJavaExt}"))
        else
            save(armsPresenterKt(pageName, contractPackageName, presenterPackageName, needActivity, needFragment), srcOut.resolve("feature/presenter/${pageName}Presenter.${ktOrJavaExt}"))
    }


    if (needModel) {
        if (ktOrJavaExt == "java")
            save(armsModelJava(pageName, contractPackageName, modelPackageName, needActivity, needFragment), srcOut.resolve("feature/model/${pageName}Model.${ktOrJavaExt}"))
        else
            save(armsModelKt(pageName, contractPackageName, modelPackageName, needActivity, needFragment), srcOut.resolve("feature/model/${pageName}Model.${ktOrJavaExt}"))
    }


    if (needDagger) {
        if (ktOrJavaExt == "java") {
            save(armsComponentJava(pageName, activityPackageName, componentPackageName, contractPackageName, moudlePackageName, fragmentPackageName, needActivity, needFragment), srcOut.resolve("di/component/${pageName}Component.${ktOrJavaExt}"))
            save(armsModuleJava(pageName, contractPackageName, modelPackageName, moudlePackageName), srcOut.resolve("di/module/${pageName}Module.${ktOrJavaExt}"))
        } else {
            save(armsComponentKt(pageName, activityPackageName, componentPackageName, fragmentPackageName, moudlePackageName, needActivity, needFragment), srcOut.resolve("di/component/${pageName}Component.${ktOrJavaExt}"))
            save(armsModulelKt(pageName, contractPackageName, modelPackageName, moudlePackageName, needActivity, needFragment), srcOut.resolve("di/module/${pageName}Module.${ktOrJavaExt}"))
        }
    }


}